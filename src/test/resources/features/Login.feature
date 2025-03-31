Feature: Login functionality for SauceDemo

  Scenario Outline: Successful login with valid credentials
    Given I am on the SauceDemo login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see the dashboard with title "Swag Labs"

    Examples:
      | username               | password     |
      | standard_user          | secret_sauce |
      | problem_user           | secret_sauce |
      | performance_glitch_user| secret_sauce |
      | error_user             | secret_sauce |
      | visual_user            | secret_sauce |

  Scenario: Login fails for locked-out user
    Given I am on the SauceDemo login page
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click the login button
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."

  Scenario: Login fails when username is missing
    Given I am on the SauceDemo login page
    When I enter username "" and password "testpassword"
    And I clear the username field
    And I click the login button
    Then I should see an error message "Epic sadface: Username is required"

  Scenario: Login fails when password is missing
    Given I am on the SauceDemo login page
    When I enter username "testuser" and password ""
    And I clear the password field
    And I click the login button
    Then I should see an error message "Epic sadface: Password is required"