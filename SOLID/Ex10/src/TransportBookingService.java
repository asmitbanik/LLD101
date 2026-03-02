public class TransportBookingService {
    private final DistanceCalculatorInterface dist;
    private final DriverAllocatorInterface alloc;
    private final PaymentGatewayInterface pay;
    private final PricingStrategy pricing;

    public TransportBookingService(DistanceCalculatorInterface dist,
                                   DriverAllocatorInterface alloc,
                                   PaymentGatewayInterface pay,
                                   PricingStrategy pricing) {
        this.dist = dist;
        this.alloc = alloc;
        this.pay = pay;
        this.pricing = pricing;
    }

    public void book(TripRequest req) {
        double km = dist.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = alloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = pricing.calculateFare(km);

        String txn = pay.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
