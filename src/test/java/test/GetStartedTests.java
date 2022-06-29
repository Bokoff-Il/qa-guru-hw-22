package test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

public class GetStartedTests extends TestBase {
  @Test
  void openFourPagesAndCheck() {
    step("Check first page", () ->
    $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
        .shouldHave(Condition.text("The Free Encyclopedia")));

    step("Open second page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

    step("Check second page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
            .shouldHave(Condition.text("New ways to explore")));

    step("Open third page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

    step("Check third page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
            .shouldHave(Condition.text("Reading lists with sync")));

    step("Open fourth page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

    step("Check fourth page", () ->
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
            .shouldHave(Condition.text("Send anonymous data")));
  }

}
