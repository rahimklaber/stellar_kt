import com.github.michaelbull.result.unwrap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import me.rahimklaber.stellar.horizon.Order
import me.rahimklaber.stellar.horizon.operations.OperationResponse
import me.rahimklaber.stellar.horizon.operations.PaymentResponse
import kotlin.test.Test

class ServerTest{
    @Test
    fun test(){
        runTest {
            val server = Server("https://horizon.stellar.org")
//
            val operations = server.operations()
                .forAccount("GAAUMMCT5PVLB5SP7FJYDXKZYDFJLXLJ34EXFREMDWOZLKYVE2PNVZWO")
                .limit(200)
                .order(Order.DESC)
                .callAsync().unwrap()

            println("hi")
            operations.records.filterIsInstance<PaymentResponse>()
                .forEach(::println)
        }
    }
}