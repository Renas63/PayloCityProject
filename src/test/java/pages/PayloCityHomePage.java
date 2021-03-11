package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PayloCityHomePage {
    public PayloCityHomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(name = "form-username")
    public WebElement username;
    @FindBy(name = "form-password")
    public WebElement password;
    @FindBy(id = "btnLogin")
    public WebElement loginButton;
}