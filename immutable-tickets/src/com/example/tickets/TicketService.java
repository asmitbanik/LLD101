package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * Service builds tickets with the immutable builder and returns new instances for
 * any updates, preserving the original object for audit purposes.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        Validation.requireTicketId(id);
        Validation.requireEmail(reporterEmail, "reporterEmail");
        Validation.requireNonBlank(title, "title");

        IncidentTicket.Builder b = new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW");
        return b.build();
    }

    /**
     * Returns a new ticket object with priority escalated and
     * "ESCALATED" tag added.
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    /**
     * Returns a new ticket object with the assignee changed. Does not
     * mutate the original.
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        if (assigneeEmail != null) {
            Validation.requireEmail(assigneeEmail, "assigneeEmail");
        }
        return t.toBuilder().assigneeEmail(assigneeEmail).build();
    }
}
