package helpers;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

import org.aeonbits.owner.ConfigFactory;
import owner.BrowserstackConfig;

public class Browserstack {
  public static String videoUrl(String sessionId) {
    BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    String login = config.login();
    String password = config.password();

    String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

    return given()
        .auth().basic(login, password)
        .log().all()
        .when()
        .get(url)
        .then()
        .log().all()
        .statusCode(200)
        .extract()
        .path("automation_session.video_url");
  }
}
