package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class OrbitzAd {

    WebDriver driver;

    public OrbitzAd(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //orbitz ad click no thanks
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='No Thanks']")
    WebElement btn_Popup;

    public void Skip_Ad() throws InterruptedException {
        Thread.sleep(1000);
        log.info(" >> Skip_Ad() click no thanks");
        btn_Popup.click();
    }



}
