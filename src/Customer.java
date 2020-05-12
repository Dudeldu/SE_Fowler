
import java.lang.*;
import java.util.*;

class Customer {
    private String name;
    private Vector rentals = new Vector();
    public Customer (String newname){
        name = newname;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName (){
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();	    
        StringBuilder result = new StringBuilder(getRentalRecordHeader());

        while (enum_rentals.hasMoreElements()) {
            double thisAmount;
            Rental each = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints ++;
            //show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append("\t").append(each.getDaysRented()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        result.append(getRentalRecordFooter(totalAmount, frequentRenterPoints));
        return result.toString();
    }

    private String getRentalRecordFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private String getRentalRecordHeader() {
        return "Rental Record for " + this.getName() + "\n" +
                "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
    }

    private double amountFor(Rental rental) {
        double resultAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                resultAmount += 2;
                if (rental.getDaysRented() > 2)
                    resultAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                resultAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                resultAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    resultAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return resultAmount;
    }

}
    