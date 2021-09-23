package pageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    @FindBy(css = "button[data-role='gdpr-accept']")
    private WebElement acceptCookieButton;
    @FindBy(css = "img[class=\"btn-close\"]")
    private WebElement closeAdvertisementButton;
    @FindBy(css = "input[name='SearchText']")
    private WebElement searchField;
    @FindBy(css = "input[class='search-button']")
    private WebElement searchButton;
    @FindBy(css = "input[class=\"next-checkbox-input\"]")
    private List<WebElement> checkboxes;
    @FindBy(css = "div[class=\"_1OUGS\"] img")
    private List<WebElement> images;

    public void openPage(String url){
        driver.get(url);
    }

    public void acceptCookies(){
        try {
            acceptCookieButton.click();
            closeAdvertisementButton.click();
        }catch (NoSuchElementException e){e.printStackTrace();}
    }
    public void searchItem(String itemName){
        searchField.sendKeys(itemName);
        searchButton.click();
    }
    public void chooseFreeShippingOptions(){
        checkboxes.get(0).click();
    }
    public void clickOnThirdItem(){
        images.get(2).click();
    }
    public void changeToNewTab(){
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }


    public void takeScreenShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath.replace("\\", "/"));
        FileUtils.copyFile(SrcFile, DestFile);
    }







}
