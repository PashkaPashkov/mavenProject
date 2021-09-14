package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Test2 {
    private WebDriver driver;
    JavascriptExecutor js;

    //locators
    private final By userName = By.id("prependedInput");
    private final By password = By.id("prependedInput2");
    private final By signInButton = By.id("_submit");
    private final By counterparties = By.xpath("//div[@id='main-menu']/ul/li/a/span");
    private final By contactFaces = By.xpath("//div[@id='main-menu']/ul/li/ul/li[4]/a/span");
    private final By createContactFace = By.linkText("Создать контактное лицо");
    private final By lastName = By.name("crm_contact[lastName]");
    private final By firstName = By.name("crm_contact[firstName]");
    private final By organization = By.xpath("//div[contains(@id,'s2id_crm_contact_company-uid')]//span[@class='select2-arrow']");
    private final By inputOrganization = By.xpath("//div[@class='select2-search']/input");
    private final By searchByName = By.xpath("//ul/li[descendant::span[contains(text(),'Pashkov')]]");
    private final By position = By.name("crm_contact[jobTitle]");
    private final By saveAndExit = By.cssSelector(".btn-group:nth-child(4) > .btn");


    @Before

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test2() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().setSize(new Dimension(1050, 708));

        driver.get("https://crm.geekbrains.space/user/login");

        Assert.assertTrue(elementIsDisplayed(userName));
        Assert.assertTrue(elementIsDisplayed(password));
        Assert.assertTrue(elementIsDisplayed(signInButton));

        driver.findElement(userName).click();
        driver.findElement(userName).sendKeys("Applanatest1");
        driver.findElement(password).click();
        driver.findElement(password).sendKeys("Student2020!");
        driver.findElement(signInButton).click();
        Assert.assertTrue(elementIsDisplayed(counterparties));

        driver.findElement(counterparties).click();
        Assert.assertTrue(elementIsDisplayed(contactFaces));

        driver.findElement(contactFaces).click();
        Assert.assertTrue(elementIsDisplayed(createContactFace));

        driver.findElement(createContactFace).click();
        Assert.assertTrue(elementIsDisplayed(lastName));
        Assert.assertTrue(elementIsDisplayed(firstName));

        driver.findElement(lastName).click();
        driver.findElement(lastName).sendKeys("Пашков");
        driver.findElement(firstName).click();
        driver.findElement(firstName).sendKeys("Павел");
        Assert.assertTrue(elementIsDisplayed(organization));

        driver.findElement(organization).click();
        Assert.assertTrue(elementIsDisplayed(inputOrganization));

        driver.findElement(inputOrganization).sendKeys("PashkovTest");
        Assert.assertTrue(elementIsDisplayed(searchByName));

        driver.findElement(searchByName).click();
        driver.findElement(By.cssSelector(".lang-ru")).click();
        Assert.assertTrue(elementIsDisplayed(position));

        driver.findElement(position).click();
        driver.findElement(position).sendKeys("Tester");
        Assert.assertTrue(elementIsDisplayed(saveAndExit));

        driver.findElement(saveAndExit).click();
        driver.close();
        System.out.println("Test Complete!!!");
    }

    private boolean elementIsDisplayed(final By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException nsee) {
            System.out.println("No such element by locator:" + locator);
            return false;
        }
    }
}