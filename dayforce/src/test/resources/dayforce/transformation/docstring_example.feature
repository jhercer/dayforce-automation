@smoke
Feature: Book information

  Scenario: Display books defined by JSON
    Given Books are defined by json as node
    """
    [
      {
        "title": "Harry Potter and the Philosopher's Stone",
        "author": "J.K. Rowling",
        "year": 1997
      },
      {
        "title": "The Hobbit",
        "author": "J.R.R. Tolkien",
        "year": 1937
      },
      {
        "title": "1984",
        "author": "George Orwell",
        "year": 1949
      }
    ]
    """
