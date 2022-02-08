package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class BookingPage {
    public WebDriver driver;

    public BookingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[@class='langCardClose']")
    public WebElement promptClose;

    @FindBy(xpath="//div[@class='makeFlex column flexOne']")
    public WebElement login;

    @FindBy(xpath="//input[@id='username']")
    public WebElement txt_userName;

    @FindBy(xpath="//input[@id='password']")
    public WebElement txt_password;

    @FindBy(xpath="//button[@data-cy='continueBtn']")
    public WebElement btn_continue;

    @FindBy(xpath="//button[@data-cy='login']")
    public WebElement btn_login;

    @FindBy(xpath="//input[@id='fromCity']")
    public WebElement btn_From;

    @FindBy(xpath="//input[@placeholder='From']")
    public WebElement searchFrom;

    @FindBy(xpath="//p[text()='Bengaluru, India']")
    public WebElement txt_searchFrom;

    @FindBy(xpath="//input[@id='toCity']")
    public WebElement btn_To;

    @FindBy(xpath="//input[@placeholder='To']")
    public WebElement btn_searchTo;

    @FindBy(xpath="//p[text()='New Delhi, India']")
    public WebElement txt_searchTo;

    @FindBy(xpath="//label[@for='departure']")
    public WebElement date;

    @FindBy(xpath="(//p[text()='10'])[1]")
    public WebElement fromDate;

    @FindBy(xpath="//label[@for='return']")
    public WebElement returnDate;

    @FindBy(xpath="(//p[text()='14'])[1]")
    public WebElement toDate;

    @FindBy(xpath="//label[@for='travellers']")
    public WebElement travellers;

    @FindBy(xpath="//li[@data-cy='adults-2']")
    public WebElement noOfAdults;

    @FindBy(xpath="//li[@data-cy='children-1']")
    public WebElement noOfChildren;

    @FindBy(xpath="//button[@data-cy='travellerApplyBtn']")
    public WebElement btn_apply;

    @FindBy(xpath="//p[@data-cy='submit']//a")
    public WebElement btn_search;

    @FindBy(xpath="(//span[contains(@class,'up sort-arrow')])[1]")
    public WebElement priceSort;

    @FindBy(xpath="//li[text()='Round Trip']//span")
    public WebElement btn_roundtrip;

    @FindBy(xpath="(//label[@class='splitViewListing checked  '])[1]")
    public WebElement btn_Options;

    @FindBy(xpath="((//span[contains(text(),'Price')])[1]/following::span[contains(text(),'options available')]/following::button)[1]")
    public WebElement btn_View;

    @FindBy(xpath="(//span[@class='customRadioBtn'])[1]")
    public WebElement btn_flightSelection;

    @FindBy(xpath="//button[text()='Book Now']")
    public WebElement btn_BookNow;

    @FindBy(xpath="//p[text()='Premium Flex']")
    public List<WebElement> btn_PremiumFlex;

    @FindBy(xpath="//button[text()='Continue']")
    public WebElement btn_bookContinue;

    @FindBy(xpath="//span[text()='View Fare Rules']")
    public WebElement lnk_viewFare;

    @FindBy(xpath="(//p[contains(text(),'2 hours to 3 days')])[1]//following::p[text()='ADULT : '][1]//b")
    public WebElement txt_AdultFee;

    @FindBy(xpath="(//p[contains(text(),'2 hours to 3 days')])[1]//following::p[text()='CHILD : '][1]//b")
    public WebElement txt_ChildFee;

    @FindBy(xpath="(//div[@class='commonOverlay ']//span[contains(@class,overlayCrossIcon)])[1]")
    public WebElement btn_close;

    @FindBy(xpath="(//section[@class='fareSummary']//div[@class='fareTypeWrap']//div[@class='fareRow']/span)")
    public List<WebElement> txt_countfare;

    @FindBy(xpath="//span[contains(@class,'iconMinusImg')]")
    public WebElement btn_minus;

    @FindBy(xpath="//span[contains(text(),'Total Amount')]//following::span[1]")
    public WebElement txt_totalAmount;

}
