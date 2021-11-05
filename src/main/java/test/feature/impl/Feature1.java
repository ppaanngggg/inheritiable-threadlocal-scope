package test.feature.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import test.feature.Feature;
import test.scope.InheritableThreadLocal;

@Slf4j
@Getter
@InheritableThreadLocal
public class Feature1 implements Feature {
  long value;

  @Override
  public void calc() {
    value = Math.round(Math.random() * 100);
    log.info("feature1: {}", value);
  }

  @Override
  public void init() {}
}
