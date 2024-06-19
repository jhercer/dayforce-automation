package dayforce.gpri.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProcessResultsWrapper {

    @JsonProperty("ProcessResults")
    private List<ProcessResult> ProcessResults;

    @Data
    public static class ProcessResult {
        @JsonProperty("Code")
        private String Code;

        @JsonProperty("Level")
        private String Level;

        @JsonProperty("Message")
        private String Message;
    }
}
