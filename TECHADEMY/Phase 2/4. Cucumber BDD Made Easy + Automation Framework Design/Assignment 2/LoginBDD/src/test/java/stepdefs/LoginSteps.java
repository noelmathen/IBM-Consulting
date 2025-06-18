package stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
  // reuse the driver from Hooks
  private final WebDriver driver = Hooks.driver;

  @Given("I launch the login page")
  public void launchLoginPage() {
    driver.get("https://the-internet.herokuapp.com/login");
  }

  @When("I enter valid credentials")
  public void validCredentials() {
    driver.findElement(By.id("username")).sendKeys("tomsmith");
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
  }

  @When("I enter invalid credentials")
  public void invalidCredentials() {
    driver.findElement(By.id("username")).sendKeys("baduser");
    driver.findElement(By.id("password")).sendKeys("badpass");
  }

  @And("I click login")
  public void clickLogin() {
    driver.findElement(By.cssSelector("button[type='submit']")).click();
  }

  @Then("I should see a secure area message")
  public void secureAreaMessage() {
    String text = driver.findElement(By.id("flash")).getText();
    Assert.assertTrue(text.contains("You logged into a secure area!"));
  }

  @Then("I should see an error message")
  public void errorMessage() {
    String text = driver.findElement(By.id("flash")).getText();
    Assert.assertTrue(text.contains("Your username is invalid!"));
  }
}
