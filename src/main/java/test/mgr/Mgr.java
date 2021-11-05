package test.mgr;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import test.feature.Feature;
import test.strategy.Strategy;
import test.strategy.StrategyResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public class Mgr {

  @Inject List<Feature> features;
  @Inject List<Strategy> strategies;

  public Map<String, StrategyResult> getResult() {
    log.info("enter mgr");
    features.forEach(Feature::init);
    strategies.forEach(Strategy::init);

    Scheduler scheduler = Schedulers.newParallel(Thread.currentThread().getName(), 10);

    Flux.merge(features.stream().map(Mono::just).collect(Collectors.toList()))
        .parallel()
        .runOn(scheduler)
        .doOnNext(Feature::calc)
        .sequential()
        .blockLast();

    Map<String, StrategyResult> resultMap = new ConcurrentHashMap<>();
    Flux.merge(strategies.stream().map(Mono::just).collect(Collectors.toList()))
        .parallel()
        .runOn(scheduler)
        .doOnNext(strategy -> resultMap.put(strategy.name(), strategy.calc()))
        .sequential()
        .blockLast();

    return resultMap;
  }
}
