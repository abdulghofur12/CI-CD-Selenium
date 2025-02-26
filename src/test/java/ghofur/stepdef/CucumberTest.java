package ghofur.stepdef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ghofur.stepdef"},
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"}
)
public class CucumberTest {
}