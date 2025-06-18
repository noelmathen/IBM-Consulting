Feature: User Login

  Scenario: Successful login with valid credentials
    Given I launch the login page
    When I enter valid credentials
    And I click login
    Then I should see a secure area message

  Scenario: Unsuccessful login with invalid credentials
    Given I launch the login page
    When I enter invalid credentials
    And I click login
    Then I should see an error message
