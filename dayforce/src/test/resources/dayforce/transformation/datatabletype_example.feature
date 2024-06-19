@smoke
Feature: Author information

  Scenario: Display favorite authors
    Given There are my favorite authors
      | firstName | lastName | famousBook       |
      | J.K.      | Rowling  | Harry Potter     |
      | J.R.R.    | Tolkien  | Lord of the Rings|
      | George    | Orwell   | 1984             |
