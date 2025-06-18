package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationSteps {
  WebDriver driver;

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver","C:/tools/chromedriver/chromedriver.exe");
    driver = new ChromeDriver();
  }

  @Given("I open the registration page")
  public void openPage() {
    driver.get("http://demo.guru99.com/test/newtours/register.php");
  }

  @When("I enter valid user details")
  public void enterDetails() {
    driver.findElement(By.name("firstName")).sendKeys("John");
    driver.findElement(By.name("lastName")).sendKeys("Doe");
    driver.findElement(By.name("phone")).sendKeys("1234567890");
    driver.findElement(By.name("userName")).sendKeys("john.doe@example.com");
    driver.findElement(By.name("email")).sendKeys("johnuser");
    driver.findElement(By.name("password")).sendKeys("Password123");
    driver.findElement(By.name("confirmPassword")).sendKeys("Password123");
  }

  @And("I submit the form")
  public void submitForm() {
    driver.findElement(By.name("submit")).click();
  }

  @Then("I should see a confirmation message")
  public void checkConfirmation() {
    String pageText = driver.getPageSource();
    Assert.assertTrue(pageText.contains("Thank you for registering"));
  }

  @After
  public void teardown() {
    driver.quit();
  }
}
