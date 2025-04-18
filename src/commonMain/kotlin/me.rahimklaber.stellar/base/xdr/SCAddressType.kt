// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCAddressType's original definition in the XDR file is:
 * ```
 * enum SCAddressType
{
SC_ADDRESS_TYPE_ACCOUNT = 0,
SC_ADDRESS_TYPE_CONTRACT = 1,
SC_ADDRESS_TYPE_MUXED_ACCOUNT = 2,
SC_ADDRESS_TYPE_CLAIMABLE_BALANCE = 3,
SC_ADDRESS_TYPE_LIQUIDITY_POOL = 4
};
 * ```
 */
enum class SCAddressType(val value: Int) : XdrElement {
    SC_ADDRESS_TYPE_ACCOUNT(0),
    SC_ADDRESS_TYPE_CONTRACT(1),
    SC_ADDRESS_TYPE_MUXED_ACCOUNT(2),
    SC_ADDRESS_TYPE_CLAIMABLE_BALANCE(3),
    SC_ADDRESS_TYPE_LIQUIDITY_POOL(4);

    companion object : XdrElementDecoder<SCAddressType> {
        override fun decode(stream: XdrInputStream): SCAddressType {
            return when (val value = stream.readInt()) {
                0 -> SC_ADDRESS_TYPE_ACCOUNT
                1 -> SC_ADDRESS_TYPE_CONTRACT
                2 -> SC_ADDRESS_TYPE_MUXED_ACCOUNT
                3 -> SC_ADDRESS_TYPE_CLAIMABLE_BALANCE
                4 -> SC_ADDRESS_TYPE_LIQUIDITY_POOL
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
