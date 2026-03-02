import java.util.*;

// service now handles business logic only; it works with abstractions and returns
// a result object. printing and persistence are managed elsewhere.
public class OnboardingService {
    private final StudentRepository repo;
    private final InputParser parser;
    private final StudentValidator validator;

    public OnboardingService(StudentRepository repo,
                             InputParser parser,
                             StudentValidator validator) {
        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
    }

    public RegistrationResult register(String raw) {
        StudentData data = parser.parse(raw);
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            return RegistrationResult.failure(errors);
        }

        String id = StudentIdGenerator.nextId(repo.count());
        StudentRecord rec = new StudentRecord(id,
                                              data.name,
                                              data.email,
                                              data.phone,
                                              data.program);
        repo.save(rec);
        return RegistrationResult.success(rec, repo.count());
    }
}
