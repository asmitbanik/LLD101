import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        TaxPolicy tax = new TaxRules();
        DiscountPolicy disc = new DiscountRules();
        InvoiceFormatter fmt = new PlainInvoiceFormatter();
        InvoiceRepository repo = new FileStore();

        CafeteriaSystem sys = new CafeteriaSystem(tax, disc, fmt, repo);
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        CheckoutResult res = sys.checkout("student", order);
        System.out.print(res.text);
        System.out.println("Saved invoice: " + res.invoiceId + " (lines=" + res.savedLines + ")");
    }
}
