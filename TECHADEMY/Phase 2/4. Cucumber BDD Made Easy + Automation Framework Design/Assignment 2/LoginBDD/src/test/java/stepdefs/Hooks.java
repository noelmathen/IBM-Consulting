package stepdefs;

import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
  public static WebDriver driver;

  @Before(order = 0)
  public void logStart() {
    System.out.println("script start execution when execution start");
  }

  @Before(order = 1)
  public void setup() {
    System.setProperty(
      "webdriver.chrome.driver",
      "C:/tools/chromedriver/chromedriver.exe"
    );
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @After(order = 1)
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @After(order = 0)
  public void logEnd() {
    System.out.println("script End execution when execution End");
  }
}
