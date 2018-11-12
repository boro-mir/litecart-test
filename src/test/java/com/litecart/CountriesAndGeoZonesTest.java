package com.litecart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CountriesAndGeoZonesTest {

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
    public void testSortingOfCountries() {
        open("http://localhost/litecart/admin/?app=countries&doc=countries");

        // get list of countries
        ElementsCollection listOfCountries = $$(".dataTable td:nth-child(5)");

        // check sorting of the list of countries
        listOfCountries.shouldHave(exactTexts(sortTexts(listOfCountries)));

        // get list of rows
        ElementsCollection listOfRows = $$(".dataTable tr.row");

        // initialize list of countries with additional zones
        List<SelenideElement> countriesWithZones = new ArrayList<SelenideElement>();

        // iterate through the list of table rows
        for (SelenideElement row : listOfRows) {

            // check that the value of zones does not equals 0
            if (!row.find("td:nth-child(6)").text().equals("0")) {

                // add this country to the list
                countriesWithZones.add(row.find("td:nth-child(5) a"));
            }
        }

        // check if there are any countries with additional zones
        if (countriesWithZones.size() > 0) {

            // iterate through every country from the list of countries with additional zones
            for (SelenideElement country : countriesWithZones) {

                // click on country
                country.click();

                // get list of zones
                ElementsCollection listOfZones = $$(".dataTable tbody td:nth-child(3) input");

                // check sorting of the list of zones
                listOfZones.shouldHave(exactTexts(sortTexts(listOfZones)));

                // go back by browser controls
                Selenide.back();
            }
        }
    }

    @Test
    public void testSortingOfGeoZones() {
        open("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        // get list of countries
        ElementsCollection listOfCountries = $$(".dataTable td:nth-child(3) a");

        // iterate through the list of countries
        for (SelenideElement country : listOfCountries) {

            // click on country
            country.click();

            // get list of zones
            ElementsCollection listOfZones = $$(".dataTable tbody td:nth-child(3) [selected]");

            // get texts of zones
            List<String> listOfZonesText = listOfZones.texts();

            // remote empty string
            listOfZonesText.remove(listOfZonesText.size() - 1);

            // assert that the list of zones is in alphabetical order
            Assert.assertEquals(listOfZonesText, sortTexts(listOfZonesText));

            // go back by browser controls
            Selenide.back();
        }
    }

    private List<String> sortTexts(ElementsCollection listOfTexts) {

        // get texts of the collection of elements
        List<String> newList = listOfTexts.texts();

        // sort the list of texts
        Collections.sort(newList, String.CASE_INSENSITIVE_ORDER);

        // return sorted list
        return newList;
    }

    private List<String> sortTexts(List<String> listOfTexts) {

        // sort the list
        Collections.sort(listOfTexts, String.CASE_INSENSITIVE_ORDER);

        // return sorted list
        return listOfTexts;
    }
}