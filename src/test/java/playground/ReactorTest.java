package playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {
  @Test
  void name() {
    ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    threadLocal.set(1);

    System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

    Flux.range(0, 10)
        .parallel()
        .runOn(Schedulers.newParallel("1", 4))
        .doOnNext(
            integer ->
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get()))
        .sequential()
        .blockLast();

    threadLocal.remove();
    System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

    Flux.range(0, 10)
        .parallel()
        .runOn(Schedulers.newParallel("2", 4))
        .doOnNext(
            integer ->
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get()))
        .sequential()
        .blockLast();
  }
}
