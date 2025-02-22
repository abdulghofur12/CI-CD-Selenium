package ghofur.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks extends BaseTest {

    @Before
    public void beforeTest(){
        getDriver();
    }
    @After
    public void  AfterTest(){
        driver.close();

    }
}
