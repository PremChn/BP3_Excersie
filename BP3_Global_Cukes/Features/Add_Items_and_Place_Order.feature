Feature: This feature validates the following 1) Add items to cart from home page
  2) Validate that the items are added to cart and their price info is correct
  3) User Navigates to checkout page and enters user information
  3) User Navigates to checkout overview page and validate the order details
  4) Validate that the user is able to place the order and complete the transaction

  Scenario: Launch sauce demo app and login
    Given User launches browser
    When User navigates to URL "https://www.saucedemo.com/"
    Then User enters login credentials username as "standard_user" and password as "secret_sauce"
    And User clicks on login button

  Scenario: Add items to cart and validate the items in bag
    Given User in product page and page should contain text "Products"
    When User adds product back pack and bike light to cart
    And User clicks on the cart button
    Then Page should contain added items back pack and bike light
    And Price of the items back pack and bike light should be same as product page

  Scenario: Add user information and navigate to order details page
    Given User is in bag page and click on checkout button
    When User lands on checkout page and page should contain text "Checkout: Your Information"
    Then User enters "Premanand", "Panneerselvam" and "94538"
    And User clicks on the Continue button

  Scenario: Validate item details, product price,  tax, order total and place order
    Given User is in checkout overview page and page should contain text "Checkout: Overview"
    When User validates item details in checkout overview page
    And User validates product price, tax and order total
    And User clicks on the finish button
    Then User should see order confirmation text "Thank you for your order!"