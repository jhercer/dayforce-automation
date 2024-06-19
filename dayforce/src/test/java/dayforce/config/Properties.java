package dayforce.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Properties {
    @Value("${browser}")
    private String browser;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${dayforce.base.url}")
    private String dayforceLoginPageUrl;


    @Value("${bing.base.url}")
    private String bingBaseUrl;

    @Value("${json.placeholder.url}")
    private String jsonPlaceHolderUrl;

    @Value("${cacerts.path}")
    private String cacertsPath;

    @Value("${kafka.test.base.url}")
    private String kafkaBaseUrl;

    @Value("${kafka.user}")
    private String kafkaUser;

    @Value("${kafka.psw}")
    private String kafkaPsw;

    @Value("${dayforce.company}")
    private String dayforceCompany;

    @Value("${dayforce.username}")
    private String dayforceUser;

    @Value("${dayforce.password}")
    private String dayforcePassword;

    @Value("${devmsdbserver1.datasource.url}")
    private String datasourceUrlDevms;

    @Value("${devmsdbserver1.username}")
    private String datasourceUsernameDevms;

    @Value("${devmsdbserver1.password}")
    private String datasourcePasswordDevms;

    @Value("${devmsdbserver1.datasource.driver-class-name}")
    private String dataSourceDriverClassName;
}
