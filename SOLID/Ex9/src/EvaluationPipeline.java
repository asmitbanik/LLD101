public class EvaluationPipeline {
    private final PlagiarismCheckerInterface pc;
    private final CodeGraderInterface grader;
    private final ReportWriterInterface writer;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismCheckerInterface pc,
                              CodeGraderInterface grader,
                              ReportWriterInterface writer,
                              Rubric rubric) {
        this.pc = pc;
        this.grader = grader;
        this.writer = writer;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = pc.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
