package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;


class SearchAndOpenArticleTest extends TestBase {
    @Test
    void searchAndOpenArticleTest() {
        back();
        step("Type search", () ->{
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(
                CollectionCondition.sizeGreaterThan(0));
        });
        step("Open article about Appium ", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
            $(AppiumBy.className("android.widget.TextView")).shouldHave(Condition.text("Appium"));
        });

    }
}

