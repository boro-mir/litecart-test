package com.litecart;


import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;

public class LeftNavMenuTest {

    @Test
    public void leftNavMenuTest() {

        Configuration.browser = "chrome";
        open("http://localhost/litecart/admin/");
        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successLoginConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));

        for (SelenideElement element : menu) {
            element.click();

            if (!subMenu.exists()) {
                assertTitle(element.text());
            }

            if (subMenu.exists()) {
                ElementsCollection unselectedElements = subMenu.findAll("li").filter(not(cssClass("selected")));
                for (SelenideElement subElement : unselectedElements) {
                    subElement.click();
                    String urlEnding = url().substring(url().lastIndexOf('=') + 1);
                    assertTrue(urlEnding.contains(subMenu.find(".selected").getText().substring(0, 3).toLowerCase()));
                }
            }
        }
    }


    SelenideElement successLoginConfirmationMessage = $("#notices > div.notice.success");
    SelenideElement subMenu = $(".docs");
    ElementsCollection menu = $$("#box-apps-menu > li > a");

    private void assertTitle(String pageName) {
        assertTrue(Selenide.title().contains(pageName),
                "Real title is [" + Selenide.title() + "], could not find [" + pageName + "] in it.");
    }
}