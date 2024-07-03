package dayforce.config;

import dayforce.pages.Bing;
import dayforce.pages.DayforceLoginPage;
import dayforce.pages.Google;
import dayforce.rest.Client;
import dayforce.webdriver.LocalDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws Exception {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        String env = System.getProperty("env");
        Resource resource;
        if (env == null || env.isEmpty() || !new ClassPathResource("aut-" + env + ".properties").exists()) {
            resource = new ClassPathResource("aut.properties");
        } else {
            resource = new ClassPathResource("aut-" + env + ".properties");
        }

        propertySourcesPlaceholderConfigurer.setLocation(resource);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public Properties properties() {
        return new Properties();
    }

    @Bean
    public LocalDriver localDriver() {
        return new LocalDriver(properties().getBrowser(), false, null);
    }

    @Bean
    public Google googlePage() {
        return new Google(properties().getBaseUrl(), localDriver(), 10);
    }

    @Bean
    public Bing bingPage() { return new Bing(properties().getBingBaseUrl(), localDriver(), 10); }

    @Bean
    public DayforceLoginPage loginPage() { return new DayforceLoginPage(properties().getDayforceLoginPageUrl(), localDriver(), 120); }

    @Bean
    public Client client() { return new Client(); }

    @Bean
    public DataSource devmsDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties().getDataSourceDriverClassName());
        dataSource.setUrl(properties().getDatasourceUrlDevms());
        dataSource.setUsername(properties().getDatasourceUsernameDevms());
        dataSource.setPassword(properties().getDatasourcePasswordDevms());
        return dataSource;
    }

    @Bean
    @Qualifier("devms")
    public JdbcTemplate jdbcTemplateDevms(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
