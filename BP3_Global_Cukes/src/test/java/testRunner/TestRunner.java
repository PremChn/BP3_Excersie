package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //Add_Items_and_Place_Order.feature
        //Login_with_Valid_Credentials.feature
        features = {".//Features/Login_with_Valid_Credentials.feature",".//Features/Add_Items_and_Place_Order.feature"},
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        stepNotifications = true,
        plugin={"pretty","html:target/report.html"}
        )
public class TestRunner {
}
