package stepdefs;

import calendar.DepartureArrivalDates;
import helper.WaitHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.OrbitzAd;
import pages.OrbitzFlightSearchResultsPage;
import pages.OrbitzHomePage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HomePageStepDef {
    WebDriver driver;
    OrbitzHomePage orbitzHomePage;
    OrbitzFlightSearchResultsPage orbitzFlightSearchResultsPage;
    OrbitzAd orbitzAd;
    WaitHelper waitHelper;


    @Before
    public void BeforeSteps() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.orbitz.com/");
        String actual = driver.getTitle();
        String expected = "Orbitz Hotel Deals, Flights, Cheap Vacations & Rental Cars";
        Assert.assertEquals(actual, expected);
    }

    @Given("I navigate to orbitz website")
    public void i_navigate_to_orbitz_website() {
        orbitzHomePage  = new OrbitzHomePage(driver);
        orbitzHomePage.clickOn_Flights();
        orbitzHomePage.clickOn_Roundtrip();
    }

    @When("I search for roundtrip flights to {string} and {string}")
    public void i_search_for_roundtrip_flights_to_and(String departureCity, String arrivalCity) throws InterruptedException {
        orbitzHomePage  = new OrbitzHomePage(driver);
        orbitzHomePage.clickOn_LeavingFrom(departureCity);
        orbitzHomePage.clickOn_GoingTo(arrivalCity);
    }

    @When("I depart {int} weeks from today and arrival {int} weeks from today")
    public void i_depart_weeks_from_today_and_arrival_weeks_from_today(int departWeeksFromCurrentDay, int arrivalWeeksFromCurrentDay) throws InterruptedException, ParseException {
        orbitzHomePage  = new OrbitzHomePage(driver);
        orbitzHomePage.Click_DepartingDateButton();
        Thread.sleep(2000);

        //To achieve dynamic xpath I have used webelement in step definition class
        departDateFromCalender(departWeeksFromCurrentDay);
        arrivalDateFromCalender(arrivalWeeksFromCurrentDay);
        orbitzHomePage.Click_CalendarDone();
    }

    @When("I click on search")
    public void i_click_on_search() throws InterruptedException, ParseException {
        orbitzHomePage  = new OrbitzHomePage(driver);
        orbitzHomePage.Click_Search();
    }


    @Then("I Assert that the search results are rendered correctly")
    public void i_assert_that_the_search_results_are_rendered_correctly() throws InterruptedException, ParseException {
        orbitzFlightSearchResultsPage = new OrbitzFlightSearchResultsPage(driver);
        Assert.assertEquals(orbitzFlightSearchResultsPage.Get_DepartLocation(),"San Francisco, CA (SFO-San Francisco Intl.)");
        Assert.assertEquals(orbitzFlightSearchResultsPage.Get_ArrivalLocation(),"New York (NYC-All Airports)");

        Assert.assertEquals(orbitzFlightSearchResultsPage.Get_DepartDateValue(),DepartureArrivalDates.departMonthDate());
        Assert.assertEquals(orbitzFlightSearchResultsPage.Get_ArrivalDateValue(),DepartureArrivalDates.arrivalDate());

    }

    @When("I search for Nonstop and Most expensive flights for round trip")
    public void i_search_for_nonstop_and_most_expensive_flights_for_round_trip() throws InterruptedException {
        waitHelper = new WaitHelper(driver);
        orbitzFlightSearchResultsPage = new OrbitzFlightSearchResultsPage(driver);
        orbitzFlightSearchResultsPage.Get_MoreOptions();
        orbitzFlightSearchResultsPage.Click_FilterSearch();
        orbitzFlightSearchResultsPage.Click_PriceFilters();
        WebElement element = driver.findElement(By.xpath("//legend[normalize-space()='Filter by']"));
        waitHelper.waitForElement(element,60);
        orbitzFlightSearchResultsPage.select_DepartureFlight();
        orbitzFlightSearchResultsPage.select_ReturnFlight();
    }

    @When("I skip the ad")
    public void i_skip_the_ad() throws InterruptedException {
        orbitzAd = new OrbitzAd(driver);
        orbitzAd.Skip_Ad();
    }

    @Then("Assert the flight details & price on the flight review page.")
    public void assert_the_flight_details_price_on_the_flight_review_page() throws InterruptedException {
        review_FlightDetails();
    }

    public void departDateFromCalender(int weeks) throws InterruptedException {

        Thread.sleep(1000);
        WebElement btn = driver.findElement(By.xpath("//button[contains(@class,'uitk-date-picker-day uitk-new-date-picker-day selected edge')]"));
        String aria_label = btn.getAttribute("data-day");
        log.info(" >> departDateFromCalender() Default depart date: "+aria_label);
        Thread.sleep(1000);
        if(!aria_label.isEmpty()) {
            WebElement depart = driver.findElement(By.xpath("//button[contains(@data-day,'"+DepartureArrivalDates.departDate(weeks)+"')]"));
            depart.click();
            log.info(" >> departDateFromCalender() Actual departure date that is selected :" + depart);
        }
        else {
            driver.findElement(By.xpath("//button[contains(@class,'uitk-date-picker-day uitk-new-date-picker-day selected edge')]"));
        }

    }


    public void arrivalDateFromCalender(int weeks) throws InterruptedException, ParseException {
        Thread.sleep(1000);

        WebElement find_CurrentArrivalDateFromCalendar = driver.findElement(By.xpath("//td[contains(@class,'uitk-date-picker-day-number end endSelected')]//button[contains(@class,'uitk-date-picker-day uitk-new-date-picker-day selected edge')]"));

        String aria_label = find_CurrentArrivalDateFromCalendar.getAttribute("data-day");

        log.info(" >> arrivalDateFromCalender() Default arrival date: "+aria_label);
        Thread.sleep(1000);
        if(!aria_label.isEmpty()) {
            WebElement arrival = driver.findElement(By.xpath("//button[contains(@data-day,'"+DepartureArrivalDates.arrivalDay(weeks)+"')]"));
            arrival.click();
            log.info(" >> arrivalDateFromCalender() Actual arrival date that is selected :" + arrival);
        }
        else {
            driver.findElement(By.xpath("//td[contains(@class,'uitk-date-picker-day-number end endSelected')]//button[contains(@class,'uitk-date-picker-day uitk-new-date-picker-day selected edge')]"));
        }
    }


    public void review_FlightDetails() throws InterruptedException {
        waitHelper = new WaitHelper(driver);
        log.info(" >> review_FlightDetails() Flight itinerary ");
        // Store all currently open tabs in tabs
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        // Switch to newly open Tab
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(4000);

        WebElement element = driver.findElement(By.xpath("//tbody/tr/td[1]/div[contains(@class,'uitk-text uitk-type-300 uitk-type-medium uitk-text-emphasis-theme')]"));
        waitHelper.waitForElement(element,90);

        //assert departure
        String departure= driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/main[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/h2[1]")).getText();
        Assert.assertTrue(departure.contains("San Francisco"));

        //assert arrival
        String arrival= driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/main[1]/div[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/h2[1]")).getText();
        Assert.assertTrue(arrival.contains("New York"));

        //switch to price
        driver.findElement(By.xpath("//h2[normalize-space()='Price summary']")).click();
        String price = driver.findElement(By.xpath("//table[@data-test-id='trip-total']//tbody//tr//td[2]//span")).getText();
        log.info("Total Price validations:"+price);

    }

    @After
    public void closeBrowser() {
        driver.quit();
        log.info(" >> closeBrowser() Browser is closed");
    }

}
