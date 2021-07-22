package pages;

import helper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrbitzHomePage {

    WebDriver driver;

    WaitHelper waitHelper;

    public OrbitzHomePage(WebDriver driver){

        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
//        waitHelper.waitForElement();
    }

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'uitk-tab-text') and text()='Flights']")
     WebElement btn_Flights;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'uitk-tab-text') and text()='Roundtrip']")
    WebElement btn_Roundtrip;

    // Departure city
    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label, 'Leaving from')]")
    WebElement btn_LeavingFrom;

    // Departure city select from the list
    @FindBy(how = How.XPATH, using = "//span/strong[contains(text(),'San Francisco (SFO - San Francisco Intl.)')]")
    WebElement select_DepartureCity;

    // Arrival city
    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label, 'Going to')]")
    WebElement btn_GoingTo;

    // Arrival city select from the list
    @FindBy(how = How.XPATH, using = "//span/strong[contains(text(),'New York (NYC - All Airports)')]")
    WebElement select_ArrivalCity;

    //Departing date text box
    @FindBy(how = How.XPATH, using = "//button[(@id ='d1-btn')]")
    WebElement btn_DepartingDate;

    //Click Done in calendar
    @FindBy(how = How.XPATH, using = "//button[contains(@data-stid,'apply-date-picker')]")
    WebElement btn_calendar_Done;

    //Click Search in homepage
    @FindBy(how = How.XPATH, using = "//button[(@data-testid ='submit-button')]")
    WebElement btn_click_Search;


    public void clickOn_Flights(){
        btn_Flights.click();
    }

    public void clickOn_Roundtrip(){
        btn_Roundtrip.click();
    }

    public void clickOn_LeavingFrom(String departureCity) throws InterruptedException {
        btn_LeavingFrom.sendKeys(departureCity);
        Thread.sleep(1000);
        select_DepartureCity.click();
    }

    public void clickOn_GoingTo(String arrivalCity) throws InterruptedException {
        btn_GoingTo.sendKeys(arrivalCity);
        Thread.sleep(1000);
        select_ArrivalCity.click();
    }

    public void Click_DepartingDateButton() throws InterruptedException {
        btn_DepartingDate.click();
        Thread.sleep(2000);
    }

    public void Click_CalendarDone() throws InterruptedException {
        Thread.sleep(1000);
        btn_calendar_Done.click();
    }

    public void Click_Search() throws InterruptedException {
        Thread.sleep(2000);
        btn_click_Search.click();
    }
}
