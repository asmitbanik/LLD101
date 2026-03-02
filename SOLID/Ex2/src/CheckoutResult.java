public class CheckoutResult {
    public final String invoiceId;
    public final String text;
    public final int savedLines;

    public CheckoutResult(String invoiceId, String text, int savedLines) {
        this.invoiceId = invoiceId;
        this.text = text;
        this.savedLines = savedLines;
    }
}