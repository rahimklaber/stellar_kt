# stellar_kt
Multiplatform Stellar SDK

I am currently aiming to support JS, Native and JVM. 

## What about Soroban?
I am planning to work on Soroban as soon as I finish basic support for creating, signing and submitting transactions.

## Why not use the Java library?
The Java library works fine if you are doing server side or android programming. However I would like to share my Kotlin code between server and a web front-end, as Kotlin also targets the Browser.

## JVM and JS demo
https://www.youtube.com/watch?v=8z0WXVe3GLI

## Examples

### create and submit payment transaction

```kotlin
import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.operations.Payment
import me.rahimklaber.stellar.horizon.Server
import me.rahimklaber.stellar.horizon.toAccount

suspend fun main() {
    val horizon = Server("https://horizon-testnet.stellar.org")

    val keypair = KeyPair.fromSecretSeed("SBVLLHTZ5ZV3KZWBBBUNMESYIMK7AEPQJY6FYNCG2PL5LZ5IFCWWSUH2")

    val account = horizon.accounts().account(keypair.accountId).toAccount()

    val tx = transactionBuilder(source = account, network = Network.TESTNET){
        addOperation(
            Payment(destination = account.accountId, asset = Asset.Native, amount = tokenAmount("1"))
        )
    }

    tx.sign(keypair)

    val result = horizon.submitTransaction(tx)

    println(result)
}
```
prints
```
SubmitTransactionResponse(
    id = 35dcdf202d2ce23e50b3cccf5f933f1253a1df5d6d870b2e93618b995b493b7b,
    pagingToken = 4289134730424320,
    successful = true,
    hash = 35dcdf202d2ce23e50b3cccf5f933f1253a1df5d6d870b2e93618b995b493b7b,
    ledger = 998642,
    createdAt = 2024 - 04 - 07T11 : 26 : 56Z,
    sourceAccount = GDYK7S5OPTSRVIP3ZO7VOXKVO3OIOHBY5HC327QQDDHX5AF3YSIJMAOP,
    sourceAccountSequence = 149649545494537,
    feeAccount = GDYK7S5OPTSRVIP3ZO7VOXKVO3OIOHBY5HC327QQDDHX5AF3YSIJMAOP,
    feeAccountSequence = null,
    feeCharged = 100,
    maxFee = 100,
    operationCount = 1,
    envelopeXdr = AAAAAgAAAADwr8uufOUaofvLv1ddVXbchxw46cW9fhAYz36Au8SQlgAAAGQAAIgbAAAACQAAAAAAAAAAAAAAAQAAAAAAAAABAAAAAPCvy6585Rqh+8u/V11VdtyHHDjpxb1+EBjPfoC7xJCWAAAAAAAAAAAAmJaAAAAAAAAAAAG7xJCWAAAAQLPEVfsZrL0PjetDZqNfk20Sp1rSwtyZn9KMnCeB/31LAXOceaDtcbm3CqHPMzsV4lUMNdSxp9hokjgBqUe9Zgw=,
    resultXdr = AAAAAAAAAGQAAAAAAAAAAQAAAAAAAAABAAAAAAAAAAA =,
    resultMetaXdr = AAAAAwAAAAAAAAACAAAAAwAPPPIAAAAAAAAAAPCvy6585Rqh8u/V11VdtyHHDjpxb1EBjPfoC7xJCWAAAAFXjMoK0AAIgbAAAACAAAAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAAMAAAAAAAF/7wAAAABlyk9/AAAAAAAAAAEADzzyAAAAAAAAAADwr8uufOUaofvLv1ddVXbchxw46cW9fhAYz36Au8SQlgAAABV4zKCtAACIGwAAAAkAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAAAAAAAAAAAAAAAAAADAAAAAAAPPPIAAAAAZhKDAAAAAAAAAAABAAAAAAAAAAAAAAAA,
    feeMetaXdr = AAAAAgAAAAMAAX /vAAAAAAAAAADwr8uufOUaofvLv1ddVXbchxw46cW9fhAYz36Au8SQlgAAABV4zKERAACIGwAAAAgAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAAAAAAAAAAAAAAAAAADAAAAAAABf+8AAAAAZcpPfwAAAAAAAAABAA888gAAAAAAAAAA8K/LrnzlGqH7y79XXVV23IccOOnFvX4QGM9+gLvEkJYAAAAVeMygrQAAiBsAAAAIAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAwAAAAAAAX/vAAAAAGXKT38AAAAA,
    memo_type = none,
    signatures = [s8RV+xmsvQ+N60Nmo1+TbRKnWtLC3Jmf0oycJ4H/fUsBc5x5oO1xubcKoc8zOxXiVQw11LGn2GiSOAGpR71mDA==],
    validAfter = null,
    validBefore = null,
    memo = null,
    memoBytes = null)
```