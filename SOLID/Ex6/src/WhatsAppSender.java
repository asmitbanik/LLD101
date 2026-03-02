public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    protected void doSend(Notification n) {
        // WhatsApp requires an E.164-formatted number; if the caller
        // violates that the contract allows us to throw an exception so the
        // caller can handle it.
        if (!n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
    }
}
