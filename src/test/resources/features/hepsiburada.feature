Feature: Hepsiburada Tablet Purchase Test

  Scenario: User adds the most expensive Apple tablet to the cart
    Given The user navigates to the Hepsiburada homepage
    When The user goes to the Tablet category
    And The user filters by Apple brand and 13.2-inch screen size
    And The user selects the highest-priced product
    And The user adds the product to the cart
    Then The user verifies that the product price matches the cart price