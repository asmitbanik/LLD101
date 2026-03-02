public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected void doSend(Notification n) {
        // subject is ignored by design for SMS (no subject field on device)
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
    }
}
