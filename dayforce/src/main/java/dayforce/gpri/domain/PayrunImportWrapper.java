package dayforce.gpri.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayrunImportWrapper {
    @JsonProperty("PayrunImport")
    private PayrunImport PayrunImport;
    @JsonProperty("clientnamespace")
    private String clientnamespace;

    @JsonProperty("PayrunImport")
    public PayrunImport getPayRunImport() {
        return PayrunImport;
    }

    @JsonProperty("PayrunImport")
    public void setPayRunImport(PayrunImport PayrunImport) {
        this.PayrunImport = PayrunImport;
    }
}
