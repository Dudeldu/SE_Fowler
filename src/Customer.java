
import java.lang.*;
import java.util.*;

class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<Rental>();
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
        Enumeration<Rental> enum_rentals = rentals.elements();
        StringBuilder result = new StringBuilder(getRentalRecordHeader());

        while (enum_rentals.hasMoreElements()) {
            Rental rental = enum_rentals.nextElement();
            //show figures for this rental
            result.append(getRentalRecord(rental));
        }
        result.append(getRentalRecordFooter(getTotalChargeAmount(), getTotalRenterPoints()));
        return result.toString();
    }

    private int getTotalRenterPoints(){
        int totalRenterPoints = 0;
        for(Rental rental: rentals){
            totalRenterPoints += getFrequentRenterPoints(rental);
        }
        return totalRenterPoints;
    }

    private double getTotalChargeAmount(){
        double totalChargeAmount = 0;
        for(Rental rental: rentals){
            totalChargeAmount += rental.getCharge();
        }
        return totalChargeAmount;
    }

    private int getFrequentRenterPoints(Rental rental) {
        // add frequent renter points
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
            return 2;
        return 1;
    }

    private String getRentalRecordFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private String getRentalRecordHeader() {
        return "Rental Record for " + this.getName() + "\n" +
                "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
    }

    private String getRentalRecord(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + "\t" + rental.getDaysRented() + "\t" + rental.getCharge() + "\n";
    }

}
    