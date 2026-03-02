public interface EligibilityRule {
    /**
     * return a reason string if the profile fails this rule, or null if it passes
     */
    String check(StudentProfile s);
}