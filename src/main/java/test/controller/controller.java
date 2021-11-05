package test.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import test.mgr.Mgr;

import java.util.Map;

@Controller("/test")
public class controller {

  @Inject Mgr mgr;

  @Get
  public Map test() {
    return mgr.getResult();
  }
}
