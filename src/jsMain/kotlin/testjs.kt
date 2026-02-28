import me.rahimklaber.stellar.horizon.Server

suspend fun main() {
    val server = Server("https://horizon.stellar.org")
//
    server.operations()
        .forAccount("GAAUMMCT5PVLB5SP7FJYDXKZYDFJLXLJ34EXFREMDWOZLKYVE2PNVZWO")
        .limit(200)
        .call()
}