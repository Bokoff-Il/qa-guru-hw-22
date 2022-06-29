package drivers;

import com.codeborne.selenide.WebDriverProvider;
import java.net.MalformedURLException;
import java.net.URL;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import owner.BrowserstackConfig;

public class BrowserstackMobileDriver implements WebDriverProvider {

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.merge(capabilities);

    mutableCapabilities.setCapability("browserstack.user",  config.login());
    mutableCapabilities.setCapability("browserstack.key", config.password());
    mutableCapabilities.setCapability("app", config.app());
    mutableCapabilities.setCapability("device", config.device());
    mutableCapabilities.setCapability("os_version", config.version());
    mutableCapabilities.setCapability("project", "qa-guru-hw-21");
    mutableCapabilities.setCapability("build", "browserstack-build-1");
    mutableCapabilities.setCapability("name", "selenide android test");

    return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
  }

  public static URL getBrowserstackUrl() {
    try{
      return new URL("http://hub.browserstack.com/wd/hub");
    } catch(MalformedURLException e){
      throw new RuntimeException(e);
    }
  }
}
