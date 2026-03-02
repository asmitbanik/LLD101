public class DefaultPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFare(double distanceKm) {
        double fare = 50.0 + distanceKm * 6.6666666667; // messy pricing copied from original
        fare = Math.round(fare * 100.0) / 100.0;
        return fare;
    }
}