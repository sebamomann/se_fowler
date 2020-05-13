
import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private final String name;
    private final Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration<Rental> enum_rentals = rentals.elements();
        StringBuilder result = new StringBuilder("Rental Record for " + this.getName() + "\n");
        result.append("\t");
        result.append("Title");
        result.append("\t");
        result.append("\t");
        result.append("Days");
        result.append("\t");
        result.append("Amount");
        result.append("\n");

        while (enum_rentals.hasMoreElements()) {
            Rental each = enum_rentals.nextElement();
            //determine amounts for each line
            double thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getRentedDays() > 1) {
                frequentRenterPoints++;
            }
            //show figures for this rental
            result.append("\t");
            result.append(each.getMovie().getTitle());
            result.append("\t");
            result.append("\t");
            result.append(each.getRentedDays());
            result.append("\t");
            result.append(thisAmount);
            result.append("\n");
            totalAmount += thisAmount;
        }

        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private double amountFor(Rental rental) {
        double thisAmount = 0;

        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getRentedDays() > 2)
                    thisAmount += (rental.getRentedDays() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getRentedDays() * 3;
                break;
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (rental.getRentedDays() > 3)
                    thisAmount += (rental.getRentedDays() - 3) * 1.5;
                break;
        }

        return thisAmount;
    }
}
