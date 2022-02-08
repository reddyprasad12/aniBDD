package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.PageObjectManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import objects.BookingPage;
import dataProvider.ConfigFileReader;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Steps{
    public WebDriver driver;
    public BookingPage book;
    public PageObjectManager pm;
    public ArrayList<String> al;

    @Given("the user is on make my trip homepage")
    public void the_user_is_on_make_my_trip_homepage() {
        ConfigFileReader cf= new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", cf.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(cf.getImplicitlyWait(), TimeUnit.SECONDS);
        driver.get(cf.getApplicationUrl());
    }
    @When("the user login with {string} and {string}")
    public void the_user_login_with_and(String userName, String password) throws InterruptedException {
        pm= new PageObjectManager(driver);
        book= pm.getBookingPage();

        book.login.click();
        Thread.sleep(1000);
        book.txt_userName.sendKeys(userName);
        Thread.sleep(1000);
        book.btn_continue.click();
        Thread.sleep(1000);
        book.txt_password.sendKeys(password);
        book.btn_login.click();
        Thread.sleep(1000);
    }

    @Then("the user enter {string} to {string} in from and to box")
    public void the_user_enter_to_in_from_and_to_box(String strFrom, String strTo) throws InterruptedException {

        Thread.sleep(1000);
        book.btn_roundtrip.click();
        Thread.sleep(1000);
        book.btn_From.click();

        book.searchFrom.sendKeys(strFrom);
        Thread.sleep(1000);

        book.txt_searchFrom.click();
        Thread.sleep(1000);

        book.btn_To.click();
        Thread.sleep(1000);

        book.btn_searchTo.sendKeys(strTo);
        Thread.sleep(1000);
        book.txt_searchTo.click();

    }
    @And("enter the date and select number of passengers as {string} Adults and {string} Children")
    public void enter_the_date_and_select_number_of_passengers(String adults,String child) throws InterruptedException {

        book.date.click();
        Thread.sleep(2000);

        book.fromDate.click();
        Thread.sleep(1000);

        book.toDate.click();
        Thread.sleep(1000);

        book.travellers.click();
        driver.findElement(By.xpath("//li[@data-cy='adults-"+adults+"']")).click();
        Thread.sleep(100);

        driver.findElement(By.xpath("//li[@data-cy='children-"+child+"']")).click();

        Thread.sleep(500);

        book.btn_apply.click();
        Thread.sleep(100);

        book.btn_search.click();
        Thread.sleep(5000);
    }
    @Then("select the lower priced airlines and click book button")
    public void select_the_lower_priced_airlines_and_click_book_button() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'up sort-arrow')])[1]")));
        //book.booklowerPrice();
        if(book.priceSort.isDisplayed())
        {
            if(book.btn_Options.isEnabled())
            {
                book.btn_BookNow.click();
            }
            else
            {
                book.btn_View.click();
                book.btn_flightSelection.click();
                book.btn_BookNow.click();
            }
        }
    }
    @And("change economy class to premium flex and continue")
    public void change_economy_class_to_premium_flex_and_continue() throws InterruptedException {
       Thread.sleep(1000);

        if(book.btn_bookContinue.isDisplayed()) {

            int count= book.btn_PremiumFlex.size();
            for(int i=0;i<count;i++) {

                book.btn_PremiumFlex.get(i).click();
            }
        }
        book.btn_bookContinue.click();
    }
    @Then("verify the fare rules for selected time period and verify it for all the passengers")
    public void verify_the_fare_rules_for_selected_time_period_and_verify_it_for_all_the_passengers() throws InterruptedException {
        al = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String) al.get(1));

        book.lnk_viewFare.click();
        String adultFee=book.txt_AdultFee.getText().trim();
        String childFee=book.txt_ChildFee.getText().trim();

        System.out.println("AdultFee : "+adultFee);
        System.out.println("ChildFee : "+childFee);

        String amount[]=adultFee.split("\\+");
        String adultfee=amount[0].replace(",","");
        String adultfees=adultfee.substring(2,adultfee.length()).trim();

        String childfees=amount[1].substring(2,amount[1].length()).trim();

        int firstAmount=Integer.parseInt(adultfees);
        int secondAmount=Integer.parseInt(childfees);

        int sum=firstAmount+secondAmount;

        System.out.println("Total sum for the Airline+MMT fee is : "+sum*2);

        book.btn_close.click();

    }
    @Then("verify the total amount and validate it")
    public void verify_the_total_amount_and_validate_it() throws InterruptedException {
        Thread.sleep(1000);
        int sum=0;
        book.btn_minus.click();
        for(int i=1;i<=book.txt_countfare.size();i++)
        {
            String price=driver.findElement(By.xpath("(//section[@class='fareSummary']//div[@class='fareTypeWrap']//div[@class='fareRow']/span)["+i+"]")).getText().trim();
            Thread.sleep(500);
            int s=price.length();
            String str=price.substring(2,s).replace(",","");
            int value=Integer.parseInt(str);
            sum=sum+value;
        }
        String amount=book.txt_totalAmount.getText().trim();
        String newAmt=amount.substring(2,amount.length()).replace(",","");
        int txtAmount=Integer.parseInt(newAmt);

        if(sum==txtAmount)
        {
            Assert.assertEquals(sum,txtAmount);
            System.out.println("The total amount for the fare is : "+amount);
        }
        driver.quit();
    }

}