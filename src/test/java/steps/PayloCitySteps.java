
package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BenefitDashboardPage;
import pages.PayloCityHomePage;
import utils.ConfigReader;
import utils.Driver;
import java.util.List;
public class PayloCitySteps {
    WebDriver driver;
    PayloCityHomePage payloCityHomePages;
    BenefitDashboardPage benefitDashboardPage;
    @Given("user is on paylocity homepage")
    public void user_is_on_paylocity_homepage() {
        driver = Driver.getDriver();
        driver.get("file:///Users/renasmustafaaktas/Desktop/Paylocity%20QA%20Interview%20Challenge/login.html");
    }
    @Given("user logs in the Benefits Dashboard page")
    public void user_logs_in_the_benefits_dashboard_page() {
        payloCityHomePages = new PayloCityHomePage(driver);
        payloCityHomePages.username.sendKeys(ConfigReader.getProperty("username"));
        payloCityHomePages.password.sendKeys(ConfigReader.getProperty("password"));
        payloCityHomePages.loginButton.click();
        system.out.println();
    }
    @When("user  clicks add employee button")
    public void user_clicks_add_employee_button() {
        benefitDashboardPage = new BenefitDashboardPage(driver);
        benefitDashboardPage.addEmployeeButton.click();
    }
    @When("user  enters employee details {string}, {string}, {string}")
    public void user_enters_employee_details(String name, String lastname, String dependant) throws InterruptedException {
        benefitDashboardPage = new BenefitDashboardPage(driver);
        benefitDashboardPage.enterEmployeeInfo(name, lastname, dependant);
    }
    @Then("user verifies their info {string}, {string}, {string}, benefitCost and netPay on the benefits dashboard")
    public void user_verifies_their_info_benefit_cost_and_net_pay_on_the_benefits_dashboard(String name, String lastname, String dependant) {
        benefitDashboardPage = new BenefitDashboardPage(driver);
        List<WebElement> employeeInfo = benefitDashboardPage.details;
        Assert.assertEquals(employeeInfo.get(1).getText().trim(), name);
        Assert.assertEquals(employeeInfo.get(2).getText().trim(), lastname);
        Assert.assertEquals(employeeInfo.get(4).getText().trim(), dependant);
    }
    @Then("user sees if employee gets {string} percent discount.")
    public void user_sees_if_employee_gets_percent_discount(String name) {
        benefitDashboardPage = new BenefitDashboardPage(driver);
        Assert.assertEquals(benefitDashboardPage.details.get(6).getText().trim(), benefitDashboardPage.benefitCost(name));
        double expNetPay = 2000 - Double.parseDouble(benefitDashboardPage.benefitCost(name));
        Assert.assertEquals(benefitDashboardPage.details.get(7).getText().trim(), String.valueOf(expNetPay));
    }
}
