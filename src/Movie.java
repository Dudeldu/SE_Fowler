import com.sun.javaws.exceptions.InvalidArgumentException;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String title;
    private Price price;

    public Movie(String title, int price) throws InvalidArgumentException {
        this.title = title;
        switch (price){
            case CHILDRENS:
                this.price = new ChildrenPrice();
                break;
            case REGULAR:
                this.price = new RegularPrice();
                break;
            case NEW_RELEASE:
                this.price = new NewReleasePrice();
                break;
            default:
                throw new InvalidArgumentException(new String[]{""});
        }
    }

    public Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented){
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}

abstract class Price{
    abstract double getCharge(int daysRented);
    int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}

class RegularPrice extends Price{
    @Override
    double getCharge(int daysRented) {
        if (daysRented > 2)
            return (daysRented - 2) * 1.5 + 2;
        return 2.;
    }
}

class NewReleasePrice extends Price{
    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented){
        if(daysRented > 2)
            return 2;
        return 1;
    }
}

class ChildrenPrice extends Price{
    @Override
    double getCharge(int daysRented) {
        if (daysRented > 3)
            return (daysRented - 3) * 1.5 + 1.5;
        return 1.5;
    }
}