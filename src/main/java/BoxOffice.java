import java.util.Random;
import java.util.concurrent.Callable;

public class BoxOffice implements Callable<int[]> {
   private final int countSales = 4;
   private final int[] arrayReceipt = new int[countSales];

    @Override
    public int[] call() {
        int maxSumSales = 5;
        for (int i = 0; i < arrayReceipt.length; i++) {
            Random random = new Random();
            arrayReceipt[i] = random.nextInt(maxSumSales);
        }
        return arrayReceipt;
    }
}
