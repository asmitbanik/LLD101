public interface PricingRule {
    Money monthly(BookingRequest req);
}