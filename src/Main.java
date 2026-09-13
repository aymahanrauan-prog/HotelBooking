// 1. Product
class HotelBooking {
    private String guestName;
    private String roomType;
    private boolean hasBreakfast;
    private boolean hasLunch;
    private boolean hasDinner;
    private boolean hasTransfer;

    HotelBooking(String guestName, String roomType, boolean hasBreakfast, boolean hasLunch, boolean hasDinner, boolean hasTransfer) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.hasBreakfast = hasBreakfast;
        this.hasLunch = hasLunch;
        this.hasDinner = hasDinner;
        this.hasTransfer = hasTransfer;
    }

    @Override
    public String toString() {
        return "HotelBooking [" +
                "guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", hasBreakfast=" + hasBreakfast +
                ", hasLunch=" + hasLunch +
                ", hasDinner=" + hasDinner +
                ", hasTransfer=" + hasTransfer +
                ']';
    }
}

enum RoomType {
    Standard, Superior, Suite;
}

// Builder
class HotelBookingBuilder {
    private String guestName;
    private String roomType;
    private boolean hasBreakfast = false;
    private boolean hasLunch = false;
    private boolean hasDinner = false;
    private boolean hasTransfer = false;

    public HotelBookingBuilder setGuestName(String guestName) {
        this.guestName = guestName;
        return this;
    }

    public HotelBookingBuilder setRoomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public HotelBookingBuilder setBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
        return this;
    }

    public HotelBookingBuilder setLunch(boolean hasLunch) {
        this.hasLunch = hasLunch;
        return this;
    }

    public HotelBookingBuilder setDinner(boolean hasDinner) {
        this.hasDinner = hasDinner;
        return this;
    }

    public HotelBookingBuilder setTransfer(boolean hasTransfer) {
        this.hasTransfer = hasTransfer;
        return this;
    }

    public HotelBooking build() {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new IllegalStateException("Guest name cannot be empty!");
        }
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new IllegalStateException("Room type cannot be empty!");
        }
        return new HotelBooking(guestName, roomType, hasBreakfast, hasLunch, hasDinner, hasTransfer);
    }
}

// Director
class BookingDirector {
    public HotelBooking buildVIPPackage(HotelBookingBuilder builder, String guestName) {
        return builder.setGuestName(guestName)
                .setRoomType("Suite")
                .setBreakfast(true)
                .setLunch(true)
                .setDinner(true)
                .setTransfer(true)
                .build();
    }
}


// Client
public class Main {
    public static void main(String[] args) {
        HotelBooking custom = new HotelBookingBuilder()
                .setGuestName("Rauan")
                .setRoomType("Standard")
                .setBreakfast(true)
                .build();
        System.out.println(custom);

        BookingDirector director = new BookingDirector();
        HotelBooking vip = director.buildVIPPackage(new HotelBookingBuilder(), "Rauan");
        System.out.println(vip);
    }
}