package test.strategy;

public interface Strategy {
  void init();

  String name();

  StrategyResult calc();
}
