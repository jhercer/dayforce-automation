package dayforce.hooks;

import dayforce.config.Properties;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CertificateHooks {

    @Autowired
    private Properties properties;
    @Before
    public void setCerts() {
        String javaHome = System.getenv("JAVA_HOME");
        String cacertsPath = javaHome + properties.getCacertsPath();
        System.setProperty("javax.net.ssl.trustStore", cacertsPath);
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }
}
