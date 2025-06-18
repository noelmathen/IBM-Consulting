package testrunner;

import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/features",
  glue     = "stepdefs",
  plugin   = {"pretty","html:target/cucumber-report.html"}
)
public class TestRunner { }
