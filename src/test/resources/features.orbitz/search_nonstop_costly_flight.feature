Feature: Orbitz flight search
  As a user
  I should be able to navigate, search and book an expensive flight in orbtiz website from SFO to NYC round trip

  Scenario: Search for a costly flight from sfo to nyc and verify the flight details

    Given I navigate to orbitz website
    When I search for roundtrip flights to "San Francisco" and "New York"
    And I depart 2 weeks from today and arrival 3 weeks from today
    And I click on search
    Then I Assert that the search results are rendered correctly

    When I search for Nonstop and Most expensive flights for round trip
    And I skip the ad
    Then Assert the flight details & price on the flight review page.

