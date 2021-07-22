package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Slf4j
public class OrbitzFlightSearchResultsPage {

    WebDriver driver;

    public OrbitzFlightSearchResultsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Assert depart place
    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label,'Flying from San Francisco, CA (SFO-San Francisco Intl.)')]")
    WebElement text_DepartLocation;

    //Assert arrival place
    @FindBy(how = How.XPATH, using = "//button[contains(@aria-label,'Flying to New York (NYC-All Airports)')]")
    WebElement text_ArrivalLocation;

    //Assert depart place
    @FindBy(how = How.XPATH, using = "//button[contains(@data-name,'startDate')]")
    WebElement text_DepartDate;

    //Assert arrival place
    @FindBy(how = How.XPATH, using = "//button[contains(@data-name,'endDate')]")
    WebElement text_ArrivalDate;

    //More options
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'More options')]")
    WebElement btn_MoreOptions;

    //Nonstop option
    @FindBy(how = How.XPATH, using = "//input[@id='search-more-filters-option-0']")
    WebElement btn_Nonstop;

    //Search after filer selection
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Search']")
    WebElement btn_FilterSearch;

    //Price listing sort
    @FindBy(how = How.XPATH, using = "//select[@id='listings-sort']")
    WebElement btn_PriceListingSort;


    //Select Non-stop highest price flight from one-way
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/section[1]/main[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/button[1]")
    WebElement select_Flight;

    //Select Non-stop highest price flight from one-way price
    @FindBy(how = How.XPATH, using = "//div[@class='uitk-price-lockup uitk-flex-item left-align']//span[@class='uitk-lockup-price']")
    WebElement price_Flight;

    //continue button after any flight selection
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue']")
    WebElement btn_Continue;



    public String Get_DepartLocation() throws InterruptedException {
        Thread.sleep(2000);
        log.info(" >> Get_DepartLocation() : "+text_DepartLocation.getText());
        return text_DepartLocation.getText();
    }

    public String Get_ArrivalLocation() throws InterruptedException {
        Thread.sleep(2000);
       log.info(" >> Get_ArrivalLocation() : "+text_ArrivalLocation.getText());
        return text_ArrivalLocation.getText();
    }

    public String Get_DepartDateValue() throws InterruptedException {
        Thread.sleep(2000);
        log.info(" >> Get_DepartDateValue() : "+text_DepartDate.getText());
        return text_DepartDate.getText();
    }

    public String Get_ArrivalDateValue() throws InterruptedException {
        Thread.sleep(2000);
        log.info(" >> Get_ArrivalDateValue : "+text_ArrivalDate.getText());
        return text_ArrivalDate.getText();
    }

    //click more options nonstop filter
    public void Get_MoreOptions() throws InterruptedException {
        Thread.sleep(2000);
        btn_MoreOptions.click();
        btn_Nonstop.click();
        log.info(" >> Get_MoreOptions : "+btn_Nonstop.isSelected());
    }

    public void Click_FilterSearch() throws InterruptedException {
        Thread.sleep(5000);
        btn_FilterSearch.click();
    }

    //click filters to sort the items to highest price
    public void Click_PriceFilters() throws InterruptedException {
        Thread.sleep(4000);
        log.info(" >> Click_PriceFilters() ");
        btn_PriceListingSort.click();
        Thread.sleep(3000);
        Select dropdown = new Select(btn_PriceListingSort);
        dropdown.selectByVisibleText("Price (Highest)");
    }

    public void select_DepartureFlight() throws InterruptedException {
        Thread.sleep(1000);
        log.info(" >> select_DepartureFlight() non-stop highest priced flight");
        select_Flight.click();

        String departurePrice = price_Flight.getText();
        log.info(" >> select_DepartureFlight() departure price :"+ departurePrice);

        Thread.sleep(1000);
        btn_Continue.click();
    }

    //same as above refactoring needed
    public String select_ReturnFlight() throws InterruptedException {
        Click_PriceFilters();
        Thread.sleep(1000);
        log.info(" >> select_ReturnFlight() non-stop highest priced flight in return");
        select_Flight.click();

        String totalPrice = price_Flight.getText();
        log.info("total price "+ totalPrice);

        Thread.sleep(1000);
        btn_Continue.click();
        return totalPrice;
    }
}
