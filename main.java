// Singleton Pattern
class AirlineSystem {
    private static AirlineSystem instance;

    private AirlineSystem() {
        // private constructor to prevent instantiation
    }

    public static AirlineSystem getInstance() {
        if (instance == null) {
            instance = new AirlineSystem();
        }
        return instance;
    }

    // Other methods and attributes can be added here
}

// Factory Pattern
interface Ticket {
    void book();
}

class EconomyTicket implements Ticket {
    @Override
    public void book() {
        System.out.println("Booking Economy Class Ticket");
    }
}

class BusinessTicket implements Ticket {
    @Override
    public void book() {
        System.out.println("Booking Business Class Ticket");
    }
}

class TicketFactory {
    public static Ticket createTicket(String type) {
        if ("Economy".equalsIgnoreCase(type)) {
            return new EconomyTicket();
        } else if ("Business".equalsIgnoreCase(type)) {
            return new BusinessTicket();
        }
        return null;
    }
}

// Adapter Pattern
class LegacyBookingSystem {
    public void reserveEconomy() {
        System.out.println("Legacy system reserved an economy ticket.");
    }
}

interface NewBookingSystem {
    void reserve(String type);
}

class LegacyBookingAdapter implements NewBookingSystem {
    private LegacyBookingSystem legacySystem;

    public LegacyBookingAdapter(LegacyBookingSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void reserve(String type) {
        if ("Economy".equalsIgnoreCase(type)) {
            legacySystem.reserveEconomy();
        }
    }
}

// Facade Pattern
class BookingFacade {
    private TicketFactory ticketFactory;
    private NewBookingSystem newBookingSystem;

    public BookingFacade() {
        this.ticketFactory = new TicketFactory();
        this.newBookingSystem = new LegacyBookingAdapter(new LegacyBookingSystem());
    }

    public void bookTicket(String classType) {
        Ticket ticket = ticketFactory.createTicket(classType);
        if (ticket != null) {
            ticket.book();
            newBookingSystem.reserve(classType);
        } else {
            System.out.println("Invalid ticket type");
        }
    }
}

// Command Pattern
interface Command {
    void execute();
}

class BookingCommand implements Command {
    private BookingFacade bookingFacade;
    private String classType;

    public BookingCommand(BookingFacade bookingFacade, String classType) {
        this.bookingFacade = bookingFacade;
        this.classType = classType;
    }

    @Override
    public void execute() {
        bookingFacade.bookTicket(classType);
    }
}

public class Main {
    public static void main(String[] args) {
        // Singleton Pattern
        AirlineSystem airlineSystem = AirlineSystem.getInstance();

        // Factory Pattern
        Ticket economyTicket = TicketFactory.createTicket("Economy");
        economyTicket.book();

        // Adapter Pattern
        NewBookingSystem newBookingSystem = new LegacyBookingAdapter(new LegacyBookingSystem());
        newBookingSystem.reserve("Economy");

        // Facade Pattern
        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.bookTicket("Business");

        // Command Pattern
        Command bookEconomyCommand = new BookingCommand(bookingFacade, "Economy");
        bookEconomyCommand.execute();
    }
}
