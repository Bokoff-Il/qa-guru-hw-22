package owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:local.properties"
})
public interface LocalConfig extends Config {

  @Key("url")
  String url();

  @Key("platformName")
  String platformName();

  @Key("deviceName")
  String deviceName();

  @Key("platformVersion")
  String platformVersion();
}
