package owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:browserstack.properties"
})
public interface BrowserstackConfig extends Config {

  @Key("login")
  String login();

  @Key("password")
  String password();

  @Key("app")
  String app();

  @Key("device")
  String device();

  @Key("version")
  String version();
}
