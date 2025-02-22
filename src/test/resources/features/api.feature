Feature: Test API

  Scenario: Get list of users
    Given I send a GET request to list users
    Then the response status code should be 200
    And the response should contain "page" with value 2
    And the response should contain "per_page" with value 6

  Scenario: Create a new user
    Given I set name to "ghofur" and job to "QA"
    And I send a POST request to create a user
    Then the response status code should be 201
    And the response should contain "name" with value "ghofur"