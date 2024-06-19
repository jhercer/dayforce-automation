package dayforce.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TestExecution {
    private String testExecutionKey;
    private Info info;
    private List<Test> tests;


    @Data
    @NoArgsConstructor
    public static class Info {
        private String summary;
        private String description;
        private String startDate;
        private String finishDate;
    }

    @Data
    @NoArgsConstructor
    public static class Test {
        private String testKey;
        private String start;
        private String finish;
        private String comment;
        private String status;

        public enum Status {
            TODO,
            PASSED,
            BLOCKED,
            EXECUTING,
            FAILED
        }
    }
}