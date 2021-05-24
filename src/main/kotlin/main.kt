import org.stellar.sdk.*
import org.stellar.sdk.xdr.AccountFlags

val server = Server("https://horizon-testnet.stellar.org")
val keyPair = KeyPair.fromSecretSeed("SDCLEUCJMFH5DXNJEFKZA74EDJ454OECHIEF7J3ZBKS252NTFFZM3HM3")
val account =
    Account(keyPair.accountId, server.accounts().account(keyPair.accountId).sequenceNumber)

fun main(args: Array<String>) {

    val transaction = transaction(account, Network.TESTNET) {
        setBaseFee(1000)
        addData("mexcewl","cool")
        setOptions {
            setSetFlags(AccountFlag.AUTH_REQUIRED_FLAG,AccountFlag.AUTH_REVOCABLE_FLAG)
        }
    }
    transaction.sign(keyPair)
    val txres = server.submitTransaction(transaction)
    println("success : ${txres.isSuccess}")
}