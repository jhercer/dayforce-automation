package dayforce.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import dayforce.entities.BookEntity;


public class ParameterTypeSteps {
    @ParameterType(".*")
    public BookEntity book(String bookName) {
        return new BookEntity(bookName);
    }

    @Given("{book} is my favorite book")
    public void this_is_my_favorite_book(BookEntity book) {
        System.out.printf("My favorite book is %s", book.getName());
    }
}
