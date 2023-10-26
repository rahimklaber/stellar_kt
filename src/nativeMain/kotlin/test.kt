import com.github.michaelbull.result.orElseThrow
import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.operations.Payment
import me.rahimklaber.stellar.horizon.Server
import me.rahimklaber.stellar.horizon.toAccount


suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")
    val account = "GAPXFBCUZVX4YJ6D5JDUSAVHPZVAX4PPDM3V7HE5YH4Z7PSACDNYEXOS"
    val keypair = KeyPair.fromSecretSeed("SDCIQUQKNIIDWSX4E46GQCO7ZR6PC4X7EA7D2LRQYMIFSZ6BGZV4I3YN")
    println(keypair.accountId)

    val source = server.accounts().account(account).orElseThrow().value.toAccount()
    val transaction = transactionBuilder(source, Network.TESTNET) {
        addOperation(
            Payment(
                destination = source.accountId,
                amount = tokenAmount(1_000_000_0),
                asset = Asset.Native
            )
        )
    }

    transaction.sign(keypair)

    println(server.submitTransaction(transaction))
}