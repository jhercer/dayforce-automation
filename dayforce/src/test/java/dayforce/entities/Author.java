package dayforce.entities;

import lombok.Data;

@Data
public class Author {
    private String firstName;
    private String lastName;
    private String famousBook;

    public Author(String firstName, String lastName, String famousBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.famousBook = famousBook;
    }
}
