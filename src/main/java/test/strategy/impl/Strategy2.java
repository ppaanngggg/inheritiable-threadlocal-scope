package test.strategy.impl;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import test.feature.impl.Feature1;
import test.feature.impl.Feature2;
import test.scope.InheritableThreadLocal;
import test.strategy.Strategy;
import test.strategy.StrategyResult;

@Slf4j
@InheritableThreadLocal
public class Strategy2 implements Strategy {
  @Inject Feature1 feature1;
  @Inject Feature2 feature2;

  @Override
  public StrategyResult calc() {
    long score = feature1.getValue() - feature2.getValue();
    log.info("strategy2: {}", score);
    return new StrategyResult(score);
  }

  @Override
  public void init() {}

  @Override
  public String name() {
    return "strategy2";
  }
}
