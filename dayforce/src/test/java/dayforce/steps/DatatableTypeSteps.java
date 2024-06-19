package dayforce.steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import dayforce.entities.Author;

import java.util.List;
import java.util.Map;

public class DatatableTypeSteps {

    @DataTableType
    public Author authorEntry(Map<String, String> entry) {
        return new Author(
                entry.get("firstName"),
                entry.get("lastName"),
                entry.get("famousBook"));
    }

    @Given("There are my favorite authors")
    public void there_are_my_favorite_authors(List<Author> authors) {
        authors.stream().forEach(System.out::println);
    }

}
