package test.strategy.impl;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
class Strategy1Test {

  @Inject Strategy1 strategy1;

  @Test
  void calc() {
    strategy1.feature1.calc();
    strategy1.feature2.calc();
    System.out.println(strategy1.calc().getScore());
  }
}
