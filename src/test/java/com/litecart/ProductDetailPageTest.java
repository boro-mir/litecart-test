package com.litecart;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductDetailPageTest {

    @Test
    public void testPDP() {
        Configuration.browser = "chrome";
        open("http://localhost/litecart/en/");
        SelenideElement plpItem = $$("#box-campaigns .link").first();

        String plpItemName = plpItem.find(By.className("name")).getText();
        String plpItemRegularPrice = plpItem.find(By.className("regular-price")).getText();
        String plpItemRegularPriceFontWeight = plpItem.find(By.className("regular-price")).getCssValue("font-weight");
        String plpItemRegularPriceFontColor = plpItem.find(By.className("regular-price")).getCssValue("color");
        String plpItemCampaignPrice = plpItem.find(By.className("campaign-price")).getText();
        String plpItemCampaignFontColor = plpItem.find(By.className("campaign-price")).getCssValue("color");
        String plpItemCampaignFontWeight = plpItem.find(By.className("campaign-price")).getCssValue("font-weight");

        plpItem.find(By.tagName("strong")).shouldHave(cssClass("campaign-price"));
        plpItem.findAll(By.className("campaign-price")).shouldHave(CollectionCondition.size(1));

        String plpRegPriceFontSizeValue = plpItem.find(By.className("regular-price")).getCssValue("font-size");
        double plpRegPriceFontSize = Double.parseDouble(plpRegPriceFontSizeValue.replaceAll("([^\\d.])", ""));

        String plpCampaignPriceFontSizeValue = plpItem.find(By.className("campaign-price")).getCssValue("font-size");
        double plpCampaignPriceFontSize = Double.parseDouble(plpCampaignPriceFontSizeValue.replaceAll("([^\\d.])", ""));

        assertTrue(plpCampaignPriceFontSize > plpRegPriceFontSize);
        assertEquals(plpItemRegularPriceFontColor.substring(5, 18), "119, 119, 119");
        assertEquals(plpItemCampaignFontColor.substring(10, 14), "0, 0");

        plpItem.click();
        $("[name=add_cart_product]").shouldBe(visible);

        String pdpItemRegularPriceFontColor = $("#box-product s.regular-price").getCssValue("color");
        String pdpItemCampaignPriceFontColor = $("#box-product strong.campaign-price").getCssValue("color");

        $("#box-product .title").shouldHave(exactText(plpItemName));
        $("#box-product s.regular-price")
                .shouldHave(exactText(plpItemRegularPrice))
                .shouldHave(cssValue("font-weight", plpItemRegularPriceFontWeight));
        $("#box-product strong.campaign-price")
                .shouldHave(exactText(plpItemCampaignPrice))
                .shouldHave(cssValue("color", plpItemCampaignFontColor))
                .shouldHave(cssValue("font-weight", plpItemCampaignFontWeight));

        String pdpRegPriceFontSizeValue = $("#box-product s.regular-price").getCssValue("font-size");
        double pdpRegPriceFontSize = Double.parseDouble(pdpRegPriceFontSizeValue.replaceAll("([^\\d.])", ""));

        String pdpCampaignPriceFontSizeValue = $("#box-product strong.campaign-price").getCssValue("font-size");
        double pdpCampaignPriceFontSize = Double.parseDouble(pdpCampaignPriceFontSizeValue.replaceAll("([^\\d.])", ""));

        assertTrue(pdpCampaignPriceFontSize > pdpRegPriceFontSize);
        assertEquals(pdpItemRegularPriceFontColor.substring(5, 18), "102, 102, 102");
        assertEquals(pdpItemCampaignPriceFontColor.substring(10, 14), "0, 0");
    }

}