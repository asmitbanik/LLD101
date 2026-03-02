package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * Implementation uses a builder and exposes no setters.  The internal list
 * of tags is kept unmodifiable and all fields are final.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final java.util.List<String> tags;     // immutable copy
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = java.util.Collections.unmodifiableList(new java.util.ArrayList<>(b.tags));
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    // getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public java.util.List<String> getTags() { return tags; }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    public static class Builder {
        private String id;
        private String reporterEmail;
        private String title;

        private String description = "";
        private String priority;
        private java.util.List<String> tags = new java.util.ArrayList<>();
        private String assigneeEmail = "";
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source = "";

        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        private Builder(IncidentTicket t) {
            this.id = t.id;
            this.reporterEmail = t.reporterEmail;
            this.title = t.title;
            this.description = t.description;
            this.priority = t.priority;
            this.tags = new java.util.ArrayList<>(t.tags);
            this.assigneeEmail = t.assigneeEmail;
            this.customerVisible = t.customerVisible;
            this.slaMinutes = t.slaMinutes;
            this.source = t.source;
        }

        public Builder description(String d) { this.description = d; return this; }
        public Builder priority(String p) { this.priority = p; return this; }
        public Builder addTag(String tag) { this.tags.add(tag); return this; }
        public Builder tags(java.util.List<String> list) { this.tags = new java.util.ArrayList<>(list); return this; }
        public Builder assigneeEmail(String a) { this.assigneeEmail = a; return this; }
        public Builder customerVisible(boolean v) { this.customerVisible = v; return this; }
        public Builder slaMinutes(Integer m) { this.slaMinutes = m; return this; }
        public Builder source(String s) { this.source = s; return this; }

        public IncidentTicket build() {
            // basic validation could be added here
            return new IncidentTicket(this);
        }
    }
}
