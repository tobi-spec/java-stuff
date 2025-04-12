import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

class Scratch {
    public static void main(String[] args) {
        new Scratch().demo();
    }


    public void demo() {
        ThrottleWithPromise<String, Boolean> throttle = new ThrottleWithPromise<>(this::test, Duration.ofSeconds(1));


        boolean b = test("World"); // Ohne throttle

        CompletableFuture<Boolean> result = throttle.execute("World");  // Mit throttle

        result.thenAccept(System.out::println);

    }


    public boolean test(String name) {
        System.out.printf("Hello %s", name);
        return true;
    }

    class ThrottleWithPromise<T, R> {

        private boolean isThrottling = false;
        private final Function<T, R> function;
        private final Duration timout;
        private final ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(30);

        private CompletableFuture<R> promise;


        public ThrottleWithPromise(Function<T, R> function, Duration timout) {
            this.function = function;
            this.timout = timout;
        }

        public CompletableFuture<R> execute(T arg) {
            if (!isThrottling) {
                isThrottling = true;
                scheduler.schedule(() -> {
                    isThrottling = false;
                    promise = null;
                }, timout.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);

                this.promise = CompletableFuture.completedFuture(function.apply(arg));

                return promise;
            }
            return promise;
        }

    }
}