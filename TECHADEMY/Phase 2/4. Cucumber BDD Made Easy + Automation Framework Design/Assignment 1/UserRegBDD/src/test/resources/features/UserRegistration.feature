Feature: User Registration

  Scenario: Successful registration with valid data
    Given I open the registration page
    When I enter valid user details
    And I submit the form
    Then I should see a confirmation message
