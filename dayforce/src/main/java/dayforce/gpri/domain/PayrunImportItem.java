package dayforce.gpri.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayrunImportItem {
    @JsonProperty("NoOverride")
    private String noOverride;

    @JsonProperty("ContributeToNetPay")
    private String contributeToNetPay;

    @JsonProperty("PayPeriodSuffix")
    private String payPeriodSuffix;

    @JsonProperty("PayGroupXrefCode")
    private String payGroupXrefCode;

    @JsonProperty("IsEmployerDeduction")
    private String isEmployerDeduction;

    @JsonProperty("ItemType")
    private String itemType;

    @JsonProperty("PayPeriodStart")
    private String payPeriodStart;

    @JsonProperty("PayDate")
    private String payDate;

    @JsonProperty("PayPeriodEnd")
    private String payPeriodEnd;

    @JsonProperty("CommitDate")
    private String commitDate;

    @JsonProperty("Rate")
    private int rate;

    @JsonProperty("OffCyclePayRunDefXrefCode")
    private String offCyclePayRunDefXrefCode;

    @JsonProperty("OffCyclePayRunTypeXrefCode")
    private String offCyclePayRunTypeXrefCode;

    @JsonProperty("OffCyclePayRunXrefCode")
    private String offCyclePayRunXrefCode;

    @JsonProperty("IsOffCycle")
    private boolean isOffCycle;

    @JsonProperty("EmployeeXrefCode")
    private String employeeXrefCode;

    @JsonProperty("LegalEntityXrefCode")
    private String legalEntityXrefCode;

    @JsonProperty("IsEmployerTax")
    private boolean isEmployerTax;

    @JsonProperty("Units")
    private int units;

    @JsonProperty("PayPeriod")
    private String payPeriod;

    @JsonProperty("IsPreTax")
    private boolean isPreTax;

    @JsonProperty("ItemCode")
    private int itemCode;

    @JsonProperty("CheckOrder")
    private int checkOrder;

    @JsonProperty("ItemAmount")
    private int itemAmount;
}
