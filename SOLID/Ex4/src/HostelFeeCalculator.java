import java.util.*;

public class HostelFeeCalculator {
    private final List<PricingRule> rules;

    public HostelFeeCalculator(List<PricingRule> rules) {
        this.rules = rules;
    }

    /**
     * calculate monthly fee by summing contributions of each rule
     */
    public Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0.0);
        for (PricingRule r : rules) {
            total = total.plus(r.monthly(req));
        }
        return total;
    }
}
