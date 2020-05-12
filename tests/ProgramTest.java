
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class ProgramTest {

    @Test
    void test_rental_statement_1() throws InvalidArgumentException {
        String result;
        Movie m1 = new Movie("movie1", 1);
        Movie m2 = new Movie("movie2", 2);
        Rental r1 = new Rental(m1, 10);
        Rental r2 = new Rental(m2, 5);
        Customer c1 = new Customer("joe");
        c1.addRental(r1);
        c1.addRental(r2);
        result = c1.statement();
        Assertions.assertEquals("Rental Record for joe\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tmovie1\t\t10\t30.0\n" +
                "\tmovie2\t\t5\t4.5\n" +
                "Amount owed is 34.5\n" +
                "You earned 3 frequent renter points", result);
    }

    @Test
    void test_rental_statement_2() throws InvalidArgumentException {
        String result;
        Movie m1 = new Movie("m1", 0);
        Movie m2 = new Movie("m2", 2);
        Rental r1 = new Rental(m1, 1000);
        Rental r2 = new Rental(m2, 50);
        Customer c1 = new Customer("doe");
        c1.addRental(r1);
        c1.addRental(r2);
        result = c1.statement();
        Assertions.assertEquals("Rental Record for doe\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tm1\t\t1000\t1499.0\n" +
                "\tm2\t\t50\t72.0\n" +
                "Amount owed is 1571.0\n" +
                "You earned 2 frequent renter points", result);
    }

    @Test
    void test_rental_statement_3() throws InvalidArgumentException {
        String result;
        Movie m1 = new Movie("movie1", 1);
        Rental r1 = new Rental(m1, 1);
        Customer c1 = new Customer("joe");
        c1.addRental(r1);
        result = c1.statement();
        Assertions.assertEquals("Rental Record for joe\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tmovie1\t\t1\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points", result);
    }
}