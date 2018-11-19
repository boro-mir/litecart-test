package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class NewTabTest {

    @Test
    public void testVerifyNewTabWasOpen() {
        SelenideElement successLoginConfirmationMessage = $("#notices > div.notice.success");

        Configuration.browser = "chrome";
        open("http://localhost/litecart/admin/");

        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successLoginConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));

        open("http://localhost/litecart/admin/?app=countries&doc=countries");

        ElementsCollection countries = $$("table td:nth-child(5) a");
        countries.first().click();

        ElementsCollection linksToThirdPartyServices = $$("#content a[target='_blank']");

        for (SelenideElement link : linksToThirdPartyServices) {
            String mainWindow = getWebDriver().getWindowHandle();
            System.out.println(mainWindow);

            link.click();

            Selenide.Wait().until(ExpectedConditions.numberOfWindowsToBe(2));
            System.out.println(getWebDriver().getWindowHandles());
            Selenide.switchTo().window(1).close();

            Selenide.switchTo().window(mainWindow);
        }
    }

}
