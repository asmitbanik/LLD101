/**
 * Base class for all notification channels. Subclasses must implement
 * {@link #doSend} and may assume that the request and its string fields
 * are non-null (empty string if no value was provided).
 *
 * <p>The contract for sending is deliberately loose because different
 * transports have different capabilities:
 * <ul>
 *   <li>the {@code subject} may be ignored by channels that do not
 *       support it (e.g. SMS); callers should not rely on it being
 *       delivered unless the channel documents otherwise.</li>
 *   <li>implementations may truncate the body to a channel-specific
 *       maximum length; any such limit should be clearly documented.
 *       Truncation changes meaning, so avoid it when possible.</li>
 *   <li>implementations may throw {@link IllegalArgumentException}
 *       if the target address is malformed for that transport (eg.
 *       WhatsApp requires a number beginning with "+").</li>
 * </ul>
 * Clients should be prepared to catch runtime exceptions when invoking
 * {@link #send}.
 */
public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    // final entry point performs normalization & auditing
    public final void send(Notification n) {
        if (n == null) {
            throw new IllegalArgumentException("notification cannot be null");
        }
        String subj = n.subject != null ? n.subject : "";
        String body = n.body != null ? n.body : "";
        String email = n.email != null ? n.email : "";
        String phone = n.phone != null ? n.phone : "";
        Notification normalized = new Notification(subj, body, email, phone);
        doSend(normalized);
        audit.add("sent via " + this.getClass().getSimpleName());
    }

    protected abstract void doSend(Notification n);
}
