package com.example.reports;

/**
 * Proxy handles authorization, defers creation of the RealReport until
 * first access, and reuses the loaded subject for further display calls.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // lazily-initialized real subject
    private RealReport real;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public synchronized void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: user=" + user.getName() + " role=" + user.getRole() + " cannot open " + classification + " report");
            return;
        }
        if (real == null) {
            // lazy create the real report only when an authorized user asks for it
            real = new RealReport(reportId, title, classification);
        }
        real.display(user);
    }
}
