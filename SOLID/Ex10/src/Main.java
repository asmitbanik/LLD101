public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));

        DistanceCalculatorInterface dist = new DistanceCalculator();
        DriverAllocatorInterface alloc = new DriverAllocator();
        PaymentGatewayInterface pay = new PaymentGateway();
        PricingStrategy pricing = new DefaultPricingStrategy();

        TransportBookingService svc = new TransportBookingService(dist, alloc, pay, pricing);
        svc.book(req);
    }
}
