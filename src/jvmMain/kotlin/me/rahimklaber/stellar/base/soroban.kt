package me.rahimklaber.stellar.base

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import me.rahimklaber.stellar.base.xdr.soroban.*
import me.rahimklaber.stellar.base.xdr.sorobanspec.SCSpecEntry
import me.rahimklaber.stellar.base.xdr.sorobanspec.SCSpecTypeDef
import space.kscience.kmath.operations.BigInt
import kotlin.reflect.KClass

annotation class SorobanType(val spec: String)

fun Any.toScVal(spec: SCSpecTypeDef? = null): SCVal {
    return when (this) {
        is SCVal -> this
        is Int -> SCVal.I32(this)
        is Long -> SCVal.I64(this)
        is String -> if (spec is SCSpecTypeDef.String) SCVal.String(SCString(this)) else SCVal.Symbol(SCSymbol(this))
        is List<*> -> SCVal.Vec(SCVec(map { it!!.toScVal() }))
        else -> throw IllegalArgumentException("Conversion to SCVal not supported for $this")
    }
}

fun sorobanToKotlinValue(value: SCVal): Any{
    return when (value) {
        is SCVal.Address -> value//TODO
        is SCVal.Bool -> value.b
        is SCVal.Bytes -> value.bytes.bytes.toByteArray()
        is SCVal.ContractInstance -> value //todo
        is SCVal.Error -> value //todo
        is SCVal.I128 -> TODO()
        is SCVal.I256 -> TODO()
        is SCVal.I32 -> value.value
        is SCVal.I64 -> value.value
        SCVal.LedgerKeyContractInstance -> TODO()
        is SCVal.LedgerKeyNonce -> TODO()
        is SCVal.Map -> TODO()
        is SCVal.String -> value.str.string
        is SCVal.Symbol -> value.symbol.string
        is SCVal.Timepoint -> TODO()
        is SCVal.U128 -> TODO()
        is SCVal.U256 -> TODO()
        is SCVal.U32 -> value.value
        is SCVal.U64 -> value.value
        is SCVal.Vec -> value.vec?.vals?.map(::sorobanToKotlinValue) ?: listOf<Any>()
        SCVal.Void -> Unit
    }
}

fun mapFromSorobanType(type: SCSpecTypeDef): TypeName {
    return when (type) {
        SCSpecTypeDef.Address -> SCVal.Address::class.asTypeName()
        SCSpecTypeDef.Bool -> Boolean::class.asTypeName()
        SCSpecTypeDef.Bytes -> ByteArray::class.asTypeName()
        is SCSpecTypeDef.BytesN -> ByteArray::class.asTypeName()
        SCSpecTypeDef.Duration -> error("TODO")
        SCSpecTypeDef.Error -> error("TODO")
        SCSpecTypeDef.I128 -> BigInt::class.asTypeName()
        SCSpecTypeDef.I256 -> BigInt::class.asTypeName()
        SCSpecTypeDef.I32 -> Int::class.asTypeName()
        SCSpecTypeDef.I64 -> Long::class.asTypeName()
        is SCSpecTypeDef.Map -> Map::class.asTypeName()
        is SCSpecTypeDef.Option -> error("TODO")
        is SCSpecTypeDef.Result -> error("TODO")
        SCSpecTypeDef.String -> String::class.asTypeName()
        SCSpecTypeDef.Symbol -> String::class.asTypeName()
        SCSpecTypeDef.Timepoint -> error("TODO")
        is SCSpecTypeDef.Tuple -> List::class.asTypeName().parameterizedBy(Any::class.asTypeName())
        SCSpecTypeDef.U128 -> BigInt::class.asTypeName()
        SCSpecTypeDef.U256 -> BigInt::class.asTypeName()
        SCSpecTypeDef.U32 -> UInt::class.asTypeName()
        SCSpecTypeDef.U64 -> ULong::class.asTypeName()
        is SCSpecTypeDef.Udt -> error("todo")
        SCSpecTypeDef.Val -> Any::class.asTypeName()
        is SCSpecTypeDef.Vec -> List::class.asTypeName().parameterizedBy(mapFromSorobanType(type.vec.elementType))
        SCSpecTypeDef.Void -> Unit::class.asTypeName()
    }
}

fun wrapperForStructEntry(entry: SCSpecEntry.UdtStructV0): TypeSpec {
    val spec = entry.udtStructV0

    val builder = TypeSpec
        .classBuilder(spec.name)
        .addModifiers(KModifier.DATA)
        .addKdoc(CodeBlock.of(spec.doc))

    val constructor = FunSpec.constructorBuilder()

    val toScValBuilder = FunSpec.builder("toScVal")
    val fromScValBuilder = FunSpec.builder("fromScVal")

    val toScValBody = StringBuilder()
    val fromScValBody = StringBuilder()

    for (field in spec.fields) {

        val type = mapFromSorobanType(field.type)

        constructor
            .addParameter(
                ParameterSpec.builder(field.name, type)
                    .addAnnotation(
                        AnnotationSpec
                            .builder(SorobanType::class)
                            .addMember(field.type.toString())
                            .build()
                    )
                    .build()
            )

        val specialTypes = listOf(
            SCSpecTypeDef.String,
            SCSpecTypeDef.Symbol,
        )

        val def = if (field.type in specialTypes) field.type::class.java.asTypeName().toString() else ""

        toScValBody
            .appendLine("SCMapEntry(SCVal.Symbol(SCSymbol(\"${field.name}\")), ${field.name}.toScVal($def)),")

        fromScValBody
            .appendLine("")

        builder.addProperty(PropertySpec.builder(field.name, type).initializer(field.name).build())
    }

    fromScValBuilder
        .addCode(
            CodeBlock.of(
                """
                    return ${spec.name}(
                        $fromScValBody
                    )
                """.trimIndent()
            )
        )


    toScValBuilder
        .addCode(
            CodeBlock.of(
                """
                    return SCVal.Map(
                        SCMap(
                            listOf(
                               $toScValBody                                 
                            )
                        )
                    )
                """.trimIndent()
            )
        )

    builder.primaryConstructor(constructor.build())

    builder.addFunction(
        toScValBuilder
            .returns(SCVal::class.asTypeName())
            .build()
    )

    builder.addFunction(
        fromScValBuilder
            .returns(ClassName.bestGuess(entry.udtStructV0.name))
            .build()
    )


    return builder.build()
}

fun wrapperForSpecEntry(entry: SCSpecEntry): TypeSpec {
    return when (entry) {
        is SCSpecEntry.ErrorEnumV0 -> TODO()
        is SCSpecEntry.FunctionV0 -> TODO()
        is SCSpecEntry.UdtEnumV0 -> TODO()
        is SCSpecEntry.UdtStructV0 -> wrapperForStructEntry(entry)
        is SCSpecEntry.UdtUnionV0 -> TODO()
    }
}