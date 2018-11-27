package com.litecart.pageobjects.pages;

import com.codeborne.selenide.Selenide;

abstract class AbstractPage<T> {

    /**
     * URL of the page.
     */
    protected String url;

    /**
     * Opens a web page.
     *
     * @return page object
     */
    public T open() {
        Selenide.open(url);
        return (T) this;
    }

    /**
     * Goes back by browser controls.
     *
     * @return page object
     */
    public T back() {
        Selenide.back();
        return (T) this;
    }
}
