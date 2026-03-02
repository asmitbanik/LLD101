import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        List<PricingRule> rules = new ArrayList<>();
        rules.add(new RoomTypeRule(LegacyRoomTypes.SINGLE, 14000.0));
        rules.add(new RoomTypeRule(LegacyRoomTypes.DOUBLE, 15000.0));
        rules.add(new RoomTypeRule(LegacyRoomTypes.TRIPLE, 12000.0));
        rules.add(new RoomTypeRule(LegacyRoomTypes.DELUXE, 16000.0));

        rules.add(new AddOnRule(AddOn.MESS, 1000.0));
        rules.add(new AddOnRule(AddOn.LAUNDRY, 500.0));
        rules.add(new AddOnRule(AddOn.GYM, 300.0));

        HostelFeeCalculator calc = new HostelFeeCalculator(rules);
        Money monthly = calc.calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        FakeBookingRepo repo = new FakeBookingRepo();
        repo.save(bookingId, req, monthly, deposit);
    }
}
