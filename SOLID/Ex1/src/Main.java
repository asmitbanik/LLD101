public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        StudentRepository db = new FakeDb();
        OnboardingService svc = new OnboardingService(db,
                                                    new InputParser(),
                                                    new StudentValidator());

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        System.out.println("INPUT: " + raw);
        RegistrationResult result = svc.register(raw);

        if (result.isSuccess()) {
            System.out.println("OK: created student " + result.getRecord().id);
            System.out.println("Saved. Total students: " + result.getTotal());
            System.out.println("CONFIRMATION:");
            System.out.println(result.getRecord());
        } else {
            System.out.println("ERROR: cannot register");
            for (String e : result.getErrors()) {
                System.out.println("- " + e);
            }
        }

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
