@smoke
Feature: Book information

  Scenario: Display books defined by JSON
    Given Books are defined by a simple json
    """
    {
      "title": "Harry Potter and the Philosopher's Stone",
      "author": "J.K. Rowling",
      "year": 1997
    }
    """
    When Convert the parameter string to a JSON object
    Then Verify the JSON object
