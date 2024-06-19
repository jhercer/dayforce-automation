package dayforce.steps;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBSteps {
    @Autowired
    @Qualifier("devms")
    private JdbcTemplate jdbcTemplateDevms;

    @Given("a quick db test")
    public void quick_db_test() {
        jdbcTemplateDevms.execute("SELECT 1");
        System.out.println("Connection to dev was successful");
    }
}
