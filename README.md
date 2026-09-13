Markdown
# Assignment 1: Builder Pattern — Hotel Booking System

**Course:** Software Design Patterns  
**Language:** Java  
**Author:** Rauan Aymakhan

---

## 1. Domain / Product Description
This project implements the **Builder Creational Design Pattern** for a **Hotel Booking System**.

Constructing a hotel reservation object can be complex because different guests require different configurations (e.g., room types, meal plans like breakfast, lunch, or dinner, and add-ons like airport transfer). The Builder pattern allows step-by-step construction of `HotelBooking` objects with optional attributes while maintaining readability and immutability.

### Key Components Implemented:
- **Product (`HotelBooking`):** Represents the complex booking object with multiple fields.
- **Builder (`HotelBookingBuilder`):** Provides a fluent API (method chaining) to construct the booking step by step.
- **Director (`BookingDirector`):** Orchestrates predefined booking configurations (e.g., VIP All-Inclusive Package).
- **Client (`Main`):** Demonstrates manual construction via Builder and automated construction via Director.

---

## 2. Clean Code Principles Applied

Below are the 5 Clean Code principles explicitly identified and justified in this implementation:

### Principle 1: Meaningful, Intention-Revealing Names
- **Justification:** Class and method names clearly express their domain purpose without ambiguity. Boolean flags follow English language conventions (`hasBreakfast`, `hasTransfer`).
- **Code Excerpt:**
  ```java
  // BEFORE (bad/cryptic naming):
  public HotelBookingBuilder setB(boolean b) { this.b = b; return this; }

  // AFTER (clean/expressive naming):
  public HotelBookingBuilder setBreakfast(boolean hasBreakfast) {
      this.hasBreakfast = hasBreakfast;
      return this;
  }
### Principle 2: Validated Construction (Fails Fast)
- **Justification:** An object should never be created in an invalid or incomplete state. The `build()` method validates required fields before instantiating the `HotelBooking` object, throwing a descriptive exception if requirements are not met.
- **Code Excerpt:**
  ```java
  // AFTER (Annotated Implementation):
  public HotelBooking build() {
      if (guestName == null || guestName.trim().isEmpty()) {
          throw new IllegalStateException("Guest name cannot be empty!");
      }
      if (roomType == null || roomType.trim().isEmpty()) {
          throw new IllegalStateException("Room type cannot be empty!");
      }
      return new HotelBooking(guestName, roomType, hasBreakfast, hasLunch, hasDinner, hasTransfer);
  }
### Principle 3: Small Methods Doing One Thing (Single Responsibility)
- **Justification:** Each method in the Builder class does exactly one thing: updates a single field and returns the builder instance (`this`) for method chaining.
- **Code Excerpt:**
  ```java
  // AFTER (Focused method):
  public HotelBookingBuilder setTransfer(boolean hasTransfer) {
      this.hasTransfer = hasTransfer;
      return this;
  }
### Principle 4: Method Chaining (Fluent API)
- **Justification:** Avoids telescoping constructors with long parameter lists (which lead to position-based errors). Instead, method chaining allows clear, readable object creation.
- **Code Excerpt:**
  ```java
  // BEFORE (Telescoping constructor - hard to tell what 'true, false, true' mean):
  HotelBooking booking = new HotelBooking("Rauan", "Suite", true, false, true, true);

  // AFTER (Fluent API - self-documenting):
  HotelBooking booking = new HotelBookingBuilder()
          .setGuestName("Rauan")
          .setRoomType("Standard")
          .setBreakfast(true)
          .build();
### Principle 5: Encapsulation & Immutable Construction State
- **Justification:** Default values are set explicitly in the builder fields (`false` for optional add-ons), preventing uninitialized or null state errors.
- **Code Excerpt:**
  ```java
  // AFTER (Explicit default values):
  public class HotelBookingBuilder {
      private String guestName;
      private String roomType;
      private boolean hasBreakfast = false;
      private boolean hasLunch = false;
      private boolean hasDinner = false;
      private boolean hasTransfer = false;
  }
## 3. How to Run
1. Open the project in **IntelliJ IDEA**.
2. Run the `Main.java` file.
3. Observe the output for both custom-built and Director-built hotel reservations in the console.