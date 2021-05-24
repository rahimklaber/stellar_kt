import org.stellar.sdk.*

fun transaction(
    sourceAccount: Account,
    network: Network,
    block: Transaction.Builder.() -> Unit
): Transaction {
    val builder = Transaction.Builder(sourceAccount, network)
    builder.setTimeout(0)
    builder.setBaseFee(1000)
    builder.block()
    return builder.build()
}

fun Transaction.Builder.addPaymentOperation(
    destination: String,
    amount: String,
    asset: Asset = AssetTypeNative(),
    source: String? = null
) {
    addOperation(
        PaymentOperation.Builder(destination, asset, amount).setSourceAccount(source).build()
    )
}

fun Transaction.Builder.addData(
    name: String,
    value: String,
    source: String? = null
) {
    addOperation(
        if (source != null) {
            ManageDataOperation.Builder(name, value.encodeToByteArray()).setSourceAccount(source)
                .build()
        } else {
            ManageDataOperation.Builder(name, value.encodeToByteArray()).build()
        }
    )
}

fun Transaction.Builder.addData(
    name: String,
    value: ByteArray,
    source: String? = null
) {
    addOperation(
        if (source != null) {
            ManageDataOperation.Builder(name, value).setSourceAccount(source).build()

        } else {
            ManageDataOperation.Builder(name, value).build()

        }
    )
}

fun Transaction.Builder.setOptions(block: SetOptionsOperation.Builder.() -> Unit) {
    val builder = SetOptionsOperation.Builder()
    builder.block()
    addOperation(
        builder.build()
    )
}

fun SetOptionsOperation.Builder.setSetFlags(vararg flags: AccountFlag){
    //xor the flags
    val flags = flags.map { it.value }.reduce { acc, i ->
        acc.xor(i)
    }
    setSetFlags(flags)
}