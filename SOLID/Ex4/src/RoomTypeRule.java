public class RoomTypeRule implements PricingRule {
    private final int type;
    private final double rate;

    public RoomTypeRule(int type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    @Override
    public Money monthly(BookingRequest req) {
        if (req.roomType == type) {
            return new Money(rate);
        }
        return new Money(0.0);
    }
}