Feature:Open Ebay Site and Add to Cart


  Scenario: Login Into Ebay Site and Add Book to Cart
    Given I navigate the URL of the website
    When I search for the book
    And  I click on the first book on the list
    And I click on Add to cart
    And I verify the cart is updated
    Then I display the number of items added in the cart

