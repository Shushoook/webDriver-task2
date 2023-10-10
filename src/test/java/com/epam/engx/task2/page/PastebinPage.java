package com.epam.engx.task2.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public final class PastebinPage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private static final String XPATH_TEN_MINUTE = "/html/body/span[2]/span/span[2]/ul/li[3]";

    private final WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement text;

    @FindBy(id = "postform-name")
    private WebElement title;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationContainer;

    @FindBy(xpath = XPATH_TEN_MINUTE)
    private WebElement expirationTenMin;

    @FindBy(xpath = "//span[@role='presentation' and ..//span[@id='select2-postform-format-container']]")
    private WebElement formatContainer;

    @FindBy(xpath = "//ul/li[text()[contains(.,'Bash')]]")
    private WebElement formatBash;

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        driver.get(HOMEPAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public BadCodePage postCode(CharSequence name, CharSequence code) {
        title.sendKeys(name);
        centerTheElement(formatContainer);
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(formatContainer));
        formatContainer.click();
        formatBash.click();
        expirationContainer.click();
        expirationTenMin.click();
        text.sendKeys(code);
        text.submit();

        return new BadCodePage(driver);
    }

    private void centerTheElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }
}

