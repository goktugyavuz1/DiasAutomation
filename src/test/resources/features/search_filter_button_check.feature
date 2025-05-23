Feature: Product search, filter and button validation

  Scenario: Search for Laptop, apply filters and validate button
    Given user launches the Akakce app
    When user continues without login if prompted
    Then user searches for "Laptop"
    And taps the filter button
    And selects resolution format "4K"
    And taps on the See Products button
    And selects "En Yüksek Fiyat" from sorting options
    And taps on the 10th product
    And user taps on the Go to Product button
    Then verifies the Seller button is visible
