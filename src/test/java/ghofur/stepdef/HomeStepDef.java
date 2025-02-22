package ghofur.stepdef;

import ghofur.stepdef.page.HomePage;
import io.cucumber.java.en.Then;


public class HomeStepDef extends BaseTest {

    HomePage homePage;

    @Then("user is on homepage")
    public void userIsOnHomepage() {
        homePage = new HomePage(driver);
        homePage.validateOnHomePage();
    }
}
