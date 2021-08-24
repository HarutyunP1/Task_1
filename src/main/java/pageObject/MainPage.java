package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css ="button[class='btn btn-success btn-block']")
    private WebElement signInButton;
    @FindBy(xpath = "//span[text()='Users']")
    private WebElement usersButton;
    @FindBy(css = "a[href='/users-management']")
    private WebElement usersManagementButton;



    public void signIn(String email,String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }
    public void navigateUsersManagementPage(){
        usersButton.click();
        usersManagementButton.click();
    }

}
