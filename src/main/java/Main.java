import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        BoxOffice boxOffice1 = new BoxOffice();
        BoxOffice boxOffice2 = new BoxOffice();
        BoxOffice boxOffice3 = new BoxOffice();

        // Создание массивов положительных чисел
        int[] arrayReceipt1 = threadPool.submit(boxOffice1).get();
        int[] arrayReceipt2 = threadPool.submit(boxOffice2).get();
        int[] arrayReceipt3 = threadPool.submit(boxOffice3).get();

        // Вывод созданных массивов
        System.out.println(Arrays.toString(arrayReceipt1));
        System.out.println(Arrays.toString(arrayReceipt2));
        System.out.println(Arrays.toString(arrayReceipt3));

        // Добавление значений для суммирования
        LongAdder adder = new LongAdder();
        Arrays.stream(arrayReceipt1).forEach(i -> threadPool.submit(()-> adder.add(i)));
        Arrays.stream(arrayReceipt2).forEach(i -> threadPool.submit(()-> adder.add(i)));
        Arrays.stream(arrayReceipt3).forEach(i -> threadPool.submit(()-> adder.add(i)));


        threadPool.awaitTermination(1, TimeUnit.SECONDS);
        // Суммироване полученных значений
        System.out.println("Результат - " + adder.sum());
        threadPool.shutdown();
    }
}
