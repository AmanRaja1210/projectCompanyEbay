Feature:Send GET Request on endpoint



  @api
  Scenario: Verify the get api of the products
    Given I hit the URL of get product endpoint
    When I pass the URL of the product in the request
    Then I receive the response as 200
    And I verify the response contains  BPIs
    And I verify that GBP description equals British Pound Sterling

