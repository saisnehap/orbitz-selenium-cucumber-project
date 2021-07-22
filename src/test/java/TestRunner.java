import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        plugin = {"pretty", "html:build/cucumber",
                "json:build/cucumber.json"},
        features = "/Users/saisankarshanmannepalli/IdeaProjects/orbitz-selenium-cucumber-project/src/test/resources/features.orbitz",
        glue = "stepdefs"
)
public class TestRunner {
}
