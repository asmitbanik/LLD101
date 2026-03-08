package com.example.reports;

/**
 * RealReport is the heavyweight subject: it performs the expensive disk load
 * and caches the result for subsequent displays.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private String cachedContent;
    private boolean loaded;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public synchronized void display(User user) {
        if (!loaded) {
            cachedContent = loadFromDisk();
            loaded = true;
        }
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + cachedContent);
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }

    public String getClassification() {
        return classification;
    }
}
