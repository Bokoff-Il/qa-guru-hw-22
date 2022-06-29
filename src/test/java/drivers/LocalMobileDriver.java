package drivers;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import owner.LocalConfig;

public class LocalMobileDriver implements WebDriverProvider {

  LocalConfig config = ConfigFactory.create(LocalConfig.class, System.getProperties());

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    File app = getApp();

    UiAutomator2Options options = new UiAutomator2Options();
    options.merge(capabilities);
    options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    options.setPlatformName(config.platformName());
    options.setDeviceName(config.deviceName());
    options.setPlatformVersion(config.platformVersion());
    options.setApp(app.getAbsolutePath());
    options.setAppPackage("org.wikipedia.alpha");
    options.setAppActivity("org.wikipedia.main.MainActivity");

    return new AndroidDriver(getAppiumServiceUrl(), options);
  }

  public URL getAppiumServiceUrl() {
    try {
      return new URL(config.url());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private File getApp() {
    String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/"
                    + "releases/download/latest/app-alpha-universal-release.apk";
    String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

    File app = new File(appPath);
    if (!app.exists()) {

      if (!app.exists()) {
        try (InputStream in = new URL(appUrl).openStream()) {
          copyInputStreamToFile(in, app);
        } catch (IOException e) {
          throw new AssertionError("Failed to download application", e);
        }
      }
    }
    return app;
  }
}
