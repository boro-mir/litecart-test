package com.litecart;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CountriesAndGeoZonesTest {

    @BeforeTest
    public void setUP() {
        SelenideElement successLoginConfirmationMessage = $("#notices > div.notice.success");
        Configuration.browser = "chrome";
        open("http://localhost/litecart/admin/");
        $("[name=username]").setValue("admin");
        $("[name=password]").setValue("admin").pressEnter();
        successLoginConfirmationMessage.shouldHave(exactText(" You are now logged in as admin"));
    }

    @Test
    public void testCountriesListIsSorted() {
        $$("#box-apps-menu > li").find(exactText("Countries")).click();
        System.out.println(countriesList());
    }

    public List<String> countriesList() {
        List<String> countries = new ArrayList<String>();
        System.out.println($$(".dataTable .row td:nth-child(5)").size());
        ElementsCollection countriesList = $$(".dataTable .row td:nth-child(5)");
        for (SelenideElement eachCountry : countriesList) {
            String name = eachCountry.getText();
            String country = new String(String.valueOf(name.charAt(0)));
            countries.add(country.toLowerCase());
        }
        return countries;
    }

//        ElementsCollection countriesList = $$(".dataTable .row td:nth-child(5)");
//        for (SelenideElement country : countriesList) {
//            country.getText();
//        }
//
//    System.out.println($$(".dataTable .row").size());
//        System.out.println($$(".dataTable .row td:nth-child(5)").size());

    private ElementsCollection listOfCountries = $$("table td:nth-child(5)");

    @Test
    public void testSortingOfCountries() {

        open("http://localhost/litecart/admin/?app=countries&doc=countries");

//        List<String> countries = listOfCountries.texts();
//        countries.sort(String::compareToIgnoreCase);
//
//        listOfCountries.shouldHave(CollectionCondition.texts(countries));

        List<String> countries = listOfCountries.texts();
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        listOfCountries.shouldHave(CollectionCondition.texts(countries));
    }

}