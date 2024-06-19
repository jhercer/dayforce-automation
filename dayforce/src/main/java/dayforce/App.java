package dayforce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import dayforce.gpri.domain.PayrunImportWrapper;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String json = " {\n" +
            "    \"PayrunImport\": {\n" +
            "        \"Items\": [\n" +
            "            {\n" +
            "                \"NoOverride\": \"false\",\n" +
            "                \"ContributeToNetPay\": \"true\",\n" +
            "                \"PayPeriodSuffix\": \"\",\n" +
            "                \"PayGroupXrefCode\": \"MX_SALARIED_WEEKLY\",\n" +
            "                \"IsEmployerDeduction\": \"false\",\n" +
            "                \"ItemType\": \"Earning\",\n" +
            "                \"PayPeriodStart\": \"2024-05-25T00:00:00\",\n" +
            "                \"PayDate\": \"2024-05-31T00:00:00\",\n" +
            "                \"PayPeriodEnd\": \"2024-06-01T00:00:00\",\n" +
            "                \"CommitDate\": \"2024-07-17T00:00:00\",\n" +
            "                \"Rate\": 0,\n" +
            "                \"OffCyclePayRunDefXrefCode\": \"OFF_CYCLE\",\n" +
            "                \"OffCyclePayRunTypeXrefCode\": \"NORMAL\",\n" +
            "                \"OffCyclePayRunXrefCode\": \"Bonus_2201\",\n" +
            "                \"IsOffCycle\": \"true\",\n" +
            "                \"EmployeeXrefCode\": \"88702\",\n" +
            "                \"LegalEntityXrefCode\": \"81\",\n" +
            "                \"IsEmployerTax\": \"false\",\n" +
            "                \"Units\": 0,\n" +
            "                \"PayPeriod\": \"22\",\n" +
            "                \"IsPreTax\": \"false\",\n" +
            "                \"ItemCode\": 8,\n" +
            "                \"CheckOrder\": 1,\n" +
            "                \"ItemAmount\": 3200\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"clientnamespace\": \"qaextx2_1615501\"\n" +
            "    }";

    public static void main( String[] args ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        PayrunImportWrapper wrapper = mapper.readValue(json, PayrunImportWrapper.class);
    }
}
