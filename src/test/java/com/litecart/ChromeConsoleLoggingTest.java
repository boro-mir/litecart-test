package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class ChromeConsoleLoggingTest {

    @BeforeTest
    public void setUp() {
        SelenideElement successLoginConfirmationMessage = $("#notices > div.notice.success");

        Configuration.browser = "chrome";
        open("http://localhost/litecart/admin/");

        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successLoginConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));
    }

    @Test
    public void testGetBrowserLogs() {
        open("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        ElementsCollection products = $$(".dataTable .row [title=Edit]");

        for (SelenideElement product : products) {
            product.click();
            Selenide.getWebDriverLogs("browser").forEach(System.out::println);
            Selenide.back();
        }
    }

}
