package com.litecart;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class LeftNavMenuTest {

    @Test
    public void userCanOpenAnyMenuLinkTest() {
        open("http://localhost/litecart/admin/");
        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successLoginConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));
        menuOptions.findBy(exactText("Appearence")).click();
        assertEquals("Template | My Store", title());
        $("#doc-template").shouldHave(cssClass("selected"));
        logotypeButton.click();
        assertEquals("Logotype | My Store", title());
        menuOptions.findBy(exactText("Catalog")).click();
        assertEquals("Catalog | My Store", title());
        subMenuOptions.findBy(exactText("Product Groups")).click();
        assertEquals("Product Groups | My Store", title());
        subMenuOptions.findBy(exactText("Option Groups")).click();
        assertEquals("Option Groups | My Store", title());
        subMenuOptions.findBy(exactText("Manufacturers")).click();
        assertEquals("Manufacturers | My Store", title());
        subMenuOptions.findBy(exactText("Suppliers")).click();
        assertEquals("Suppliers | My Store", title());
        subMenuOptions.findBy(exactText("Delivery Statuses")).click();
        assertEquals("Delivery Statuses | My Store", title());
        subMenuOptions.findBy(exactText("Sold Out Statuses")).click();
        assertEquals("Sold Out Statuses | My Store", title());
        subMenuOptions.findBy(exactText("Quantity Units")).click();
        assertEquals("Quantity Units | My Store", title());
        subMenuOptions.findBy(exactText("CSV Import/Export")).click();
        assertEquals("CSV Import/Export | My Store", title());
        menuOptions.findBy(exactText("Countries")).click();
        assertEquals("Countries | My Store", title());
        menuOptions.findBy(exactText("Currencies")).click();
        assertEquals("Currencies | My Store", title());
        menuOptions.findBy(exactText("Customers")).click();
        assertEquals("Customers | My Store", title());
        subMenuOptions.findBy(exactText("CSV Import/Export")).click();
        assertEquals("CSV Import/Export | My Store", title());
        subMenuOptions.findBy(exactText("Newsletter")).click();
        assertEquals("Newsletter | My Store", title());
        menuOptions.findBy(exactText("Geo Zones")).click();
        assertEquals("Geo Zones | My Store", title());
        menuOptions.findBy(exactText("Languages")).click();
        assertEquals("Languages | My Store", title());
        subMenuOptions.findBy(exactText("Storage Encoding")).click();
        assertEquals("Storage Encoding | My Store", title());
        menuOptions.findBy(exactText("Modules")).click();
        assertEquals("Job Modules | My Store", title());
        subMenuOptions.findBy(exactText("Customer")).click();
        assertEquals("Customer Modules | My Store", title());
        subMenuOptions.findBy(exactText("Shipping")).click();
        assertEquals("Shipping Modules | My Store", title());
        subMenuOptions.findBy(exactText("Payment")).click();
        assertEquals("Payment Modules | My Store", title());
        subMenuOptions.findBy(exactText("Order Total")).click();
        assertEquals("Order Total Modules | My Store", title());
        subMenuOptions.findBy(exactText("Order Success")).click();
        assertEquals("Order Success Modules | My Store", title());
        subMenuOptions.findBy(exactText("Order Action")).click();
        assertEquals("Order Action Modules | My Store", title());
        menuOptions.findBy(exactText("Orders")).click();
        assertEquals("Orders | My Store", title());
        subMenuOptions.findBy(exactText("Order Statuses")).click();
        assertEquals("Order Statuses | My Store", title());
        menuOptions.findBy(exactText("Pages")).click();
        assertEquals("Pages | My Store", title());
        menuOptions.findBy(exactText("Reports")).click();
        assertEquals("Monthly Sales | My Store", title());
        subMenuOptions.findBy(exactText("Most Sold Products")).click();
        assertEquals("Most Sold Products | My Store", title());
        subMenuOptions.findBy(exactText("Most Shopping Customers")).click();
        assertEquals("Most Shopping Customers | My Store", title());
        menuOptions.findBy(exactText("Settings")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Defaults")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("General")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Listings")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Images")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Checkout")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Advanced")).click();
        assertEquals("Settings | My Store", title());
        subMenuOptions.findBy(exactText("Security")).click();
        assertEquals("Settings | My Store", title());
        menuOptions.findBy(exactText("Slides")).click();
        assertEquals("Slides | My Store", title());
        menuOptions.findBy(exactText("Tax")).click();
        assertEquals("Tax Classes | My Store", title());
        subMenuOptions.findBy(exactText("Tax Rates")).click();
        assertEquals("Tax Rates | My Store", title());
        menuOptions.findBy(exactText("Translations")).click();
        assertEquals("Search Translations | My Store", title());
        subMenuOptions.findBy(exactText("Scan Files")).click();
        assertEquals("Scan Files For Translations | My Store", title());
        subMenuOptions.findBy(exactText("CSV Import/Export")).click();
        assertEquals("CSV Import/Export | My Store", title());
        menuOptions.findBy(exactText("Users")).click();
        assertEquals("Users | My Store", title());
        menuOptions.findBy(exactText("vQmods")).click();
        assertEquals("vQmods | My Store", title());
    }

    SelenideElement successLoginConfirmationMessage = $("#notices > div.notice.success");
    SelenideElement logotypeButton = $("#doc-logotype > a > span");
    ElementsCollection menuOptions = $$("#box-apps-menu li");
    ElementsCollection subMenuOptions = $$(".docs li");

}