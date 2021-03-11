
package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class BenefitDashboardPage {
    public BenefitDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "btnAddEmployee")
    public WebElement addEmployeeButton;
    @FindBy(xpath = "//label[@for='firstname']/following-sibling::div //input")
    public WebElement firstName;
    @FindBy(xpath = "//label[@for='lastname']/following-sibling::div/input")
    public WebElement lastName;
    @FindBy(xpath = "//label[@for='dependents']/following-sibling::div/input")
    public WebElement dependent;
    @FindBy(xpath = "//div[@class='col-sm-offset-2 col-sm-10']//child::button[1]")
    public WebElement submit;
    public void enterEmployeeInfo(String name, String lastname, String dependant) {
        firstName.sendKeys(name);
        lastName.sendKeys(lastname);
        dependent.sendKeys(dependant);
        submit.click();
    }
    @FindBy(xpath = "//tbody/tr[2]/td")
    public List<WebElement> details;
    public String benefitCost(String name) {
        name = name.toLowerCase();
        String benCost = "";
        if (!name.startsWith("a")) {
            int dependantNumber = Integer.parseInt(details.get(4).getText().trim());
            double expectedAnnualBenefitCost = 1000 + (dependantNumber * 500); // annual
            double expectedBenefitCostPerPaycheck = expectedAnnualBenefitCost / 26; // 76.4532
            benCost = String.valueOf(expectedBenefitCostPerPaycheck);
            benCost = benCost.substring(0, benCost.indexOf('.') + 3);
            expectedBenefitCostPerPaycheck = Double.parseDouble(benCost);
            System.out.println(expectedBenefitCostPerPaycheck);
            benCost = String.valueOf(expectedBenefitCostPerPaycheck);
        } else { // starts with "a"
            int dependantNumber = Integer.parseInt(details.get(4).getText().trim());
            double expectedAnnualBenefitCost = 1000 + (dependantNumber * 500); // annual
            double expectedBenefitCostPerPaycheck = (expectedAnnualBenefitCost / 26) * 0.9; // 76.4532
            benCost = String.valueOf(expectedBenefitCostPerPaycheck);
            benCost = benCost.substring(0, benCost.indexOf('.') + 3);
            expectedBenefitCostPerPaycheck = Double.parseDouble(benCost);
            System.out.println(expectedBenefitCostPerPaycheck);
            benCost = String.valueOf(expectedBenefitCostPerPaycheck);
        }
        return benCost;
    }
}
