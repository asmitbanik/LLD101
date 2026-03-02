import java.util.ArrayList;
import java.util.List;

public class StudentValidator {
    public List<String> validate(StudentData data) {
        List<String> errors = new ArrayList<>();
        if (data.name == null || data.name.isBlank()) {
            errors.add("name is required");
        }
        if (data.email == null || data.email.isBlank() || !data.email.contains("@")) {
            errors.add("email is invalid");
        }
        if (data.phone == null || data.phone.isBlank() ||
            !data.phone.chars().allMatch(Character::isDigit)) {
            errors.add("phone is invalid");
        }
        if (!("CSE".equals(data.program) || "AI".equals(data.program) || "SWE".equals(data.program))) {
            errors.add("program is invalid");
        }
        return errors;
    }
}