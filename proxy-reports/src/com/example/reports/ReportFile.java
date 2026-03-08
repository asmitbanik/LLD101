package com.example.reports;

/**
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Concrete class used directly by clients
 * - Expensive load happens every time display() is called
 * - No access control
 *
 *
 * After refactor the proxy handles access control and lazy loading; this class
 * is no longer used directly by clients.
 */
public class ReportFile {

    private final String reportId;
    private final String title;
    private final String classification; // PUBLIC / FACULTY / ADMIN

    public ReportFile(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    public void display(User user) {
        String content = loadFromDisk();
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }
}
