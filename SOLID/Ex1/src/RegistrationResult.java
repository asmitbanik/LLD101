import java.util.Collections;
import java.util.List;

public class RegistrationResult {
    private final boolean success;
    private final StudentRecord record;
    private final int totalAfter;
    private final List<String> errors;

    private RegistrationResult(boolean success,
                               StudentRecord record,
                               int totalAfter,
                               List<String> errors) {
        this.success = success;
        this.record = record;
        this.totalAfter = totalAfter;
        this.errors = errors;
    }

    public static RegistrationResult success(StudentRecord rec, int total) {
        return new RegistrationResult(true, rec, total, Collections.emptyList());
    }
    public static RegistrationResult failure(List<String> errors) {
        return new RegistrationResult(false, null, 0, errors);
    }

    public boolean isSuccess() { return success; }
    public StudentRecord getRecord() { return record; }
    public int getTotal() { return totalAfter; }
    public List<String> getErrors() { return errors; }
}