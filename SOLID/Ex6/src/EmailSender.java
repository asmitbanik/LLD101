public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected void doSend(Notification n) {
        // this implementation simply prints the values; no truncation is
        // performed so callers see the full message they supplied.
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
    }
}
