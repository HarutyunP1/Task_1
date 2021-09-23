import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.MainPage;

import java.util.concurrent.TimeUnit;


public class TestOne {
    static String UIPageUrl = "https://best.aliexpress.com/?lan=en";
    static String searchItem="nylon shoulder bag";
    static String filePath= System.getProperty("user.dir") + "/src/main/resources/test.png";
    public WebDriver driver;
    public MainPage mainPage;




    @BeforeClass
    public void preCond() {
        createDriver();
        mainPage = new MainPage(driver);
    }

    @Test
    public void test() throws Exception {
        driver.get(UIPageUrl);
        mainPage.acceptCookies();
        mainPage.searchItem(searchItem);
        mainPage.chooseFreeShippingOptions();
        mainPage.clickOnThirdItem();
        mainPage.changeToNewTab();
        mainPage.takeScreenShot(driver,filePath);
        driver.close();
    }


    private void createDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }
}

