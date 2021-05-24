import kotlin.Unit;
import org.stellar.sdk.Network;

public class test {
    public static void main(String[] args) {
        TransactionBuilter_extKt.transaction(MainKt.getAccount(), Network.TESTNET,(tx)->{
            return Unit.INSTANCE;
        });
    }
}
