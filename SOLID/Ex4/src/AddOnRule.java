public class AddOnRule implements PricingRule {
    private final AddOn addOn;
    private final double rate;

    public AddOnRule(AddOn addOn, double rate) {
        this.addOn = addOn;
        this.rate = rate;
    }

    @Override
    public Money monthly(BookingRequest req) {
        if (req.addOns.contains(addOn)) {
            return new Money(rate);
        }
        return new Money(0.0);
    }
}