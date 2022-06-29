package owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
    "classpath:credentials.properties"
})
public interface CredentialsConfig extends Config {

  @Key("login")
  String login();

  @Key("password")
  String password();

  @Key("app")
  String app();
}
