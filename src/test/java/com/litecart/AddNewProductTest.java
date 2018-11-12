package com.litecart;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.System.currentTimeMillis;

public class AddNewProductTest {

    @Test
    public void testAddNewProduct() {
        SelenideElement successConfirmationMessage = $("#notices > div.notice.success");
        ElementsCollection menu = $$("#box-apps-menu > li > a");
        ElementsCollection tabs = $$(".tabs > ul li");

        open("http://localhost/litecart/admin/");
        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));
        menu.findBy(exactText("Catalog")).click();
        $("#content > div:nth-child(2) > a:nth-child(2)").click();
        $(By.name("name[en]")).setValue("Captain America Nerf Gun");
        $(By.name("code")).setValue("CA_NG" + currentTimeMillis());
        $$("#tab-general .input-wrapper [name='product_groups[]']").get(2).click();
        $(By.name("quantity")).setValue("100.00");
        SelenideElement uploadButton = $(By.name("new_images[]"));
        uploadButton.uploadFile(new File("src/test/resources/Marvel_Captain_America-Civil_War_Blaster_Reveal_Shield.jpg"));
        $(By.name("date_valid_from")).setValue("2018").sendKeys(Keys.TAB);
        $(By.name("date_valid_from")).setValue("1115");
        $(By.name("date_valid_to")).setValue("2019").sendKeys(Keys.TAB);
        $(By.name("date_valid_to")).setValue("1115");

        tabs.findBy(exactText("Information")).waitUntil(visible, 4000).click();
        $(By.name("manufacturer_id")).selectOption("ACME Corp.");
        $(By.name("keywords")).setValue("marvel nerf guns");
        $(By.name("short_description[en]")).setValue("Captain America Nerf Blaster");
        $(".trumbowyg-editor").setValue("Iconic Captain America shield design\n" +
                "Push star button to reveal blaster\n" +
                "Launch 2 NERF darts using blaster reveal feature\n" +
                "Includes Blaster Reveal Shield, 2 NERF darts, and instructions.");
        $(By.name("head_title[en]")).setValue("Marvel Captain America: Civil War Blaster Reveal Shield");
        $(By.name("meta_description[en]")).setValue("by marvel");

        tabs.findBy(exactText("Prices")).waitUntil(visible, 4000).click();
        $(By.name("purchase_price")).setValue("32.33");
        $(By.name("purchase_price_currency_code")).selectOption("US Dollars");
        $(By.name("gross_prices[USD]")).setValue("32.33");
        $(By.name("save")).click();
        successConfirmationMessage.shouldHave(exactText(" Changes were successfully saved."));
    }

}