package dayforce.entities;

import lombok.Data;

@Data
public class BookEntity {
    private String name;
    public BookEntity(String name) {
        this.name = name;
    }
}
