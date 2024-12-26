import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> platform1 = CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return "Відгуки з платформи 1";
        });

        CompletableFuture<String> platform2 = CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return "Відгуки з платформи 2";
        });

        CompletableFuture<String> platform3 = CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return "Відгуки з платформи 3";
        });

        CompletableFuture<Void> allReviews = CompletableFuture.allOf(platform1, platform2, platform3);

        allReviews.thenRun(() -> {
            try {
                String result1 = platform1.get();
                String result2 = platform2.get();
                String result3 = platform3.get();

                System.out.println("Отримані всі відгуки:");
                System.out.println(result1);
                System.out.println(result2);
                System.out.println(result3);


                CompletableFuture<String> combinedResults = platform1.thenCombine(platform2, (res1, res2) -> res1 + " + " + res2)
                        .thenCombine(platform3, (partialResult, res3) -> partialResult + " + " + res3);

                System.out.println("Об'єднані результати: " + combinedResults.get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });


        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(platform1, platform2, platform3);

        firstCompleted.thenAccept(result -> {
            System.out.println("Перше завершене завдання: " + result);
        });


        simulateDelay();
    }


    private static void simulateDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}