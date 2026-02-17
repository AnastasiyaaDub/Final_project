package ranners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber") // Указываем, что запускаем Cucumber
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty") // Вывод в консоль
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "steps")    // Где искать шаги
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features")

public class TestRunner {



}
