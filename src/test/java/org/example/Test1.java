package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Test1 {
    private WebDriver driver;
    JavascriptExecutor js;

    //locators
    private final By userName = By.id("prependedInput");
    private final By password = By.id("prependedInput2");
    private final By signInButton = By.id("_submit");
    private final By projects = By.xpath("//div[@id='main-menu']/ul/li[3]/a/span");
    private final By myProjects = By.xpath("//div[@id='main-menu']/ul/li[3]/ul/li[4]/a/span");
    private final By createProject = By.linkText("Создать проект");
    private final By projectName = By.name("crm_project[name]");
    private final By organization = By.xpath("//div[contains(@id,'s2id_crm_project_company-uid')]//span[@class='select2-arrow']");
    private final By inputOrganization = By.xpath("//div[@id='select2-drop']//input");
    private final By searchByName = By.xpath("//ul/li[descendant::span[contains(text(),'Pashkov')]]");
    private final By mainContact = By.xpath("//div[contains(@id,'s2id_crm_project_contactMain-uid')]//span[@class='select2-arrow']");
    private final By contactSelection = By.xpath("//div[@class='select2-search']/input[@type='text'][@class='select2-input']");
    private final By enterName = By.xpath("//ul/li[descendant::span[contains(text(),'Пашков Павел')]][1]");
    private final By subdivision = By.name("crm_project[businessUnit]");
    private final By curator = By.name("crm_project[curator]");
    private final By supervisor = By.name("crm_project[rp]");
    private final By administrator = By.name("crm_project[administrator]");
    private final By manager = By.name("crm_project[manager]");
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
    public void test1() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Assert.assertTrue(elementIsDisplayed(projects));

        driver.findElement(projects).click();
        Assert.assertTrue(elementIsDisplayed(myProjects));

        driver.findElement(myProjects).click();
        Assert.assertTrue(elementIsDisplayed(createProject));

        driver.findElement(createProject).click();
        Assert.assertTrue(elementIsDisplayed(projectName));

        driver.findElement(projectName).click();
        driver.findElement(projectName).sendKeys("ProjectPashkovTest");
        Assert.assertTrue(elementIsDisplayed(organization));

        driver.findElement(organization).click();
        Assert.assertTrue(elementIsDisplayed(inputOrganization));

        driver.findElement(inputOrganization).sendKeys("PashkovTest");
        Assert.assertTrue(elementIsDisplayed(searchByName));

        driver.findElement(searchByName).click();
        Assert.assertTrue(elementIsDisplayed(mainContact));


        driver.findElement(mainContact).click();
        Assert.assertTrue(elementIsDisplayed(contactSelection));

        driver.findElement(contactSelection).sendKeys("Пашков Павел");
        Assert.assertTrue(elementIsDisplayed(enterName));

        driver.findElement(enterName).click();
        Assert.assertTrue(elementIsDisplayed(subdivision));

        driver.findElement(subdivision).click();
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[businessUnit]"));
            dropdown.findElement(By.xpath("//option[. = 'Research & Development']")).click();
        }
        Assert.assertTrue(elementIsDisplayed(curator));

        driver.findElement(curator).click();
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[curator]"));
            dropdown.findElement(By.xpath("//option[. = 'Applanatest Applanatest Applanatest']")).click();
        }
        Assert.assertTrue(elementIsDisplayed(supervisor));

        driver.findElement(supervisor).click();
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[rp]"));
            dropdown.findElement(By.xpath("//option[. = 'Applanatest1 Applanatest1 Applanatest1']")).click();
        }
        Assert.assertTrue(elementIsDisplayed(administrator));

        driver.findElement(administrator).click();
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[administrator]"));
            dropdown.findElement(By.xpath("//option[. = 'Applanatest2 Applanatest2 Applanatest2']")).click();
        }
        Assert.assertTrue(elementIsDisplayed(manager));

        driver.findElement(manager).click();
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[manager]"));
            dropdown.findElement(By.xpath("//option[. = 'Амелин Владимир']")).click();
        }
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
