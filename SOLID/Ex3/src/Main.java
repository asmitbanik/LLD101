import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        List<EligibilityRule> rules = List.of(
            new DisciplinaryRule(),
            new CgrRule(8.0),
            new AttendanceRule(75),
            new CreditsRule(20)
        );
        EligibilityEngine engine = new EligibilityEngine(rules);
        EligibilityEngineResult r = engine.evaluate(s);

        ReportPrinter p = new ReportPrinter();
        p.print(s, r);
        FakeEligibilityStore store = new FakeEligibilityStore();
        store.save(s.rollNo, r.status);
    }
}
