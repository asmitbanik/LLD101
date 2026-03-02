public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        PlagiarismCheckerInterface pc = new PlagiarismChecker();
        CodeGraderInterface grader = new CodeGrader();
        ReportWriterInterface writer = new ReportWriter();
        Rubric rubric = new Rubric();

        EvaluationPipeline pipeline = new EvaluationPipeline(pc, grader, writer, rubric);
        pipeline.evaluate(sub);
    }
}
