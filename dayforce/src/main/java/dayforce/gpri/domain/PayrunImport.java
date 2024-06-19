package dayforce.gpri.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayrunImport {
    @JsonProperty("Items")
    private PayrunImportItem[] Items;
}
