package managers;
import org.openqa.selenium.WebDriver;
import objects.BookingPage;

public class PageObjectManager {

    private WebDriver driver;

    private BookingPage bookPage;


    public PageObjectManager(WebDriver driver) {

        this.driver = driver;

    }
    public BookingPage getBookingPage(){

        return (bookPage == null) ? bookPage = new BookingPage(driver) : bookPage;

    }

}