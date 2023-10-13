import com.github.michaelbull.result.unwrap

suspend fun main() {
    val server = Server("https://horizon.stellar.org")
//
     val operations = server.operations()
         .forAccount("GAAUMMCT5PVLB5SP7FJYDXKZYDFJLXLJ34EXFREMDWOZLKYVE2PNVZWO")
         .limit(200)
         .callAsync().unwrap()
    operations.records.forEach {
        println(it)
    }
}