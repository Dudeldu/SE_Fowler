class Rental {
    private Movie movie;
    private int daysRented;
    public Rental(Movie newmovie, int newdaysRented) {
        movie = newmovie;
        daysRented = newdaysRented;
    }
    public int getDaysRented() {
        return daysRented;
    }
    public Movie getMovie() {
        return movie;
    }

    public double getCharge() {
        double resultAmount = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                resultAmount += 2;
                if (getDaysRented() > 2)
                    resultAmount += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                resultAmount += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                resultAmount += 1.5;
                if (getDaysRented() > 3)
                    resultAmount += (getDaysRented() - 3) * 1.5;
                break;
        }
        return resultAmount;
    }
}