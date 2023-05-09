Feature: validLogin
  Scenario: Successful login with valid credentials
    Given User launches chrome browser
    When User opens URL "https://www.saucedemo.com/"
    And User enters username as "standard_user" and password as "secret_sauce"
    And Click on login button
    Then Page should contain text "Products"
    And User closes the application
