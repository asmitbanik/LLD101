// simple DTO used between parsing and validation steps
public class StudentData {
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public StudentData(String name, String email, String phone, String program) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.program = program;
    }
}