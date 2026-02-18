package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import static com.codeborne.selenide.Selenide.*;


public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @After
    public void tearDown(Scenario scenario) {

        clearBrowserCookies();
        clearBrowserLocalStorage();

        closeWebDriver();
    }

}
