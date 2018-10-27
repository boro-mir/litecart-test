package com.litecart;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    public void adminCanLoginTest(){

        open("http://localhost/litecart/admin/");
        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();

    }

}