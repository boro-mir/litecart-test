package com.litecart;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.System.currentTimeMillis;

public class CreateNewAccountTest {

    @Test
    public void testUserCanCreateAccount() {

        String firstName = "James";
        String lastName = "Gordon";
        String address = "Gotham County";
        String postCode = "08701";
        String city = "Gotham City";
        String state = "New Jersey";
        String email = "gcpd" + currentTimeMillis() + "@dc.com";
        String phone = "+1 732-262-7435";
        String password = "0000";
        SelenideElement infoMessage = $("#notices > div.notice.success");
        SelenideElement logoutButton = $$("#box-account .list-vertical a").get(3);

        open("http://localhost/litecart/en/");
        $("#box-account-login a").click();
        $("#create-account > h1").shouldHave(exactText("Create Account"));
        $("[name=firstname]").setValue(firstName);
        $("[name=lastname]").setValue(lastName);
        $("[name=address1]").setValue(address);
        $("[name=postcode]").setValue(postCode);
        $("[name=city]").setValue(city);
        $("[name=email]").setValue(email);
        $("[name=zone_code]").selectOption(state);
        $("[name=phone]").setValue(phone);
        $("[name=password]").setValue(password);
        $("[name=confirmed_password]").setValue(password);
        $("[name=newsletter").click();
        $("[name=create_account]").click();
        infoMessage.shouldHave(exactText(" Your customer account has been created."));
        logoutButton.click();
        infoMessage.shouldHave(exactText(" You are now logged out."));
        $("[name=email]").setValue(email);
        $("[name=password]").setValue(password);
        $("[name=login]").click();
        infoMessage.shouldHave(exactText(" You are now logged in as James Gordon."));
        logoutButton.click();
        infoMessage.shouldHave(exactText(" You are now logged out."));
    }

}