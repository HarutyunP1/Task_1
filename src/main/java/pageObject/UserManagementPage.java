package pageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import utils.Utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;

public class UserManagementPage {
    static String randomEmail = Utils.randomEmail();
    static String randomPassword = Utils.randomPassword();
    static String randomFirstName = Utils.randomFirstName();
    static String randomLastName = Utils.randomLastName();
    File file;
    WebDriver driver;
    @FindBy(css = "div[class='src-app-routes-UsersManagement-components-Actions-css__container']")
    private WebElement createUserButton;
    @FindBy(css = "input[placeholder=\"username@example.com\"]")
    private WebElement emailField;
    @FindBy(css = "input[placeholder=\"6 and more symbols\"]")
    private WebElement passwordFieldField;
    @FindBy(css = "input[placeholder=\"Example\"]")
    private WebElement firstNameField;
    @FindBy(css = "input[placeholder=\"User\"]")
    private WebElement lastNameFieldField;
    @FindBy(xpath = "//label//span[text()='General Manager']")
    private WebElement generalMananger;
    @FindBy(xpath = "//div[text()='Permissions']")
    private WebElement permissionButton;
    @FindBy(xpath = "//span[text()='General']")
    private WebElement general;
    @FindBy(xpath = "//label//span[text()='Read']")
    private WebElement read;
    @FindBy(xpath = "//span//span[text()='Users']")
    private WebElement users;
    @FindBy(xpath = "//span//span[text()='Affiliates editing']")
    private WebElement affiliatesEditing;
    @FindBy(xpath = "//button//span[text()='Save']")
    private WebElement write;
    @FindBy(css = "span[class=\"src-shared-components-AsyncMultiSelect-css__placeholder\"]")
    private List<WebElement> placeHolders;
    @FindBy(css = "button[class=\"src-shared-components-Button-css__container src-shared-components-Button-css__green\"]")
    private WebElement saveButton;
    @FindBy(css = "button[class=\"src-shared-components-Button-css__container\"]")
    private WebElement changeButton;
    @FindBy(xpath = "div[class=\"src-shared-components-AsyncMultiSelect-ListItem-css__container\"]")
    private WebElement searchResults;
    @FindBy(xpath = "//button//span[text()='Delete']")
    private WebElement deleteButton;
    @FindBy(xpath = "//button[text()='Apply']")
    private WebElement applyButton;
    @FindBy(css = "div[class=\"fakeViewContent__kpitz4sq79c_k9sf295igf\"]")
    private WebElement photo;

    public UserManagementPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static int findFreePort() {
        int port = 0;
        // For ServerSocket port number 0 means that the port number is automatically allocated.
        try (ServerSocket socket = new ServerSocket(0)) {
            // Disable timeout and reuse address after closing the socket.
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
        } catch (IOException ignored) {
        }
        if (port > 0) {
            return port;
        }
        throw new RuntimeException("Could not find a free port");
    }

    public void createUser() {
        try {
            file = new File(System.getProperty("user.dir") + "/src/main/resources/Avatar.jpg");
            createUserButton.click();
            emailField.sendKeys(randomEmail);
            passwordFieldField.sendKeys(randomPassword);
            firstNameField.sendKeys(randomFirstName);
            lastNameFieldField.sendKeys(randomLastName);
            generalMananger.click();
            changeButton.click();
            uploadFile();
            applyButton.click();
            permissionButton.click();
            general.click();
            read.click();
            users.click();
            affiliatesEditing.click();
            write.click();
            placeHolders.get(1).sendKeys("155");
            searchResults.click();
            saveButton.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPresentDeleteButton() {
        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(deleteButton));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void takeScreenShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath.replace("\\", "/"));
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public void uploadFile() throws IOException, AWTException, InterruptedException {
        Thread.sleep(2000);
        Robot rb = new Robot();
        StringSelection str = new StringSelection("C:\\Users\\Harutyun_Petrosyan\\IdeaProjects\\Task\\src\\main\\resources\\Avatar.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

    }


}
