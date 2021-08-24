import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.MainPage;
import pageObject.UserManagementPage;
import utils.RestAssuredUtils;
import utils.Utils;

import java.util.concurrent.TimeUnit;


public class TestOne {
    static String UIPageUrl = "https://offers-preprod.affise.com/user/login";
    static String randomEmail = Utils.randomEmail();
    static String randomPassword = Utils.randomPassword();
    static String randomFirstName = Utils.randomFirstName();
    static String randomLastName = Utils.randomLastName();

    public WebDriver driver;
    public MainPage mainPage;
    public UserManagementPage userManagementPage;
    String baseUrl = "https://api-preprod.affise.com";
    String basePath = "/3.0/admin/user";



    @BeforeClass
    public void preCond() {
        //STEP 1
        RestAssuredUtils.setBaseURI(baseUrl);
        RestAssuredUtils.setBasePath(basePath);
        RequestSpecification requestSpecification = RestAssuredUtils.getRequestSpecifications();
        requestSpecification.header("api-key", "456505a43730b5f2b4a98fb4ce5408c4").
                contentType("multipart/form-data").
                multiPart("email", randomEmail).
                multiPart("password", randomPassword).
                multiPart("first_name", randomFirstName).
                multiPart("last_name", randomLastName).
                multiPart("roles[]", "ROLE_ADMIN");
        Response response = RestAssuredUtils.getApiResponse(Method.POST, requestSpecification);
        Assert.assertTrue(response.getStatusCode() == 200, "Not Created");
        System.out.println(randomEmail);
        System.out.println(randomPassword);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        userManagementPage = new UserManagementPage(driver);
    }

    @Test
    public void test() throws Exception {
        driver.get(UIPageUrl);
        mainPage.signIn(randomEmail, randomPassword);
        mainPage.navigateUsersManagementPage();
        userManagementPage.createUser();
        Assert.assertTrue(userManagementPage.isPresentDeleteButton(), "User is created");
        userManagementPage.takeScreenShot(driver,System.getProperty("user.dir") + "/src/main/resources/test.png");
    }

}

