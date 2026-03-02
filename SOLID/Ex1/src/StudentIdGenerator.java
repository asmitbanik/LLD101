// simple id generator; kept separate so rules can change without touching other code
public class StudentIdGenerator {
    public static String nextId(int currentCount) {
        int next = currentCount + 1;
        String num = String.format("%04d", next);
        return "SST-2026-" + num;
    }
}