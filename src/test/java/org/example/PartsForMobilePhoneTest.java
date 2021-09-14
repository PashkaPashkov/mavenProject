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

public class PartsForMobilePhoneTest {
    private WebDriver driver;
    JavascriptExecutor js;

    // locators
    private final By loginIcon = By.cssSelector(".login .icon");
    private final By userName = By.id("username");
    private final By password =  By.id("password");
    private final By signInButton =  By.name("signin[do]");
    private final By phonesTabletsTab =   By.linkText("Запчасти для телефонов и планшетов");
    private final By displaysSubsection =   By.cssSelector(".category:nth-child(5) .title");
    private final By displaysSubsectionApple = By.cssSelector(".category:nth-child(4) .title");
    private final By displaysSubsectionApplePage =   By.xpath("(//a[contains(text(),'3')])[2]");
    private final By buyButton = By.xpath("//div[6]/div[5]/form/div/div[2]/input");
    private final By trashBucket = By.xpath("//section");
    private final By logout = By.cssSelector(".logout");

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
    public void partsForMobilePhone() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Размер открываемого окна
        driver.manage().window().setSize(new Dimension(831, 812));

        // Открываем сайт mokc.com.ua
        driver.get("http://mokc.com.ua/");
        // Открытие страницы: Вход в аккаунт
        Assert.assertTrue(elementIsDisplayed(loginIcon));

        driver.findElement(loginIcon).click();
        Assert.assertTrue(elementIsDisplayed(userName));
        Assert.assertTrue(elementIsDisplayed(password));
        Assert.assertTrue(elementIsDisplayed(signInButton));


        // Установка курсора на поле для ввода Логина пользователя
        driver.findElement(userName).click();
        // Ввод логина пользователя
        driver.findElement(userName).sendKeys("Pavel_Pashkov");
        // Ввод пароля пользователя
        driver.findElement(password).sendKeys("87604371284");
        // Нажатие клавиши "Войти" с последующим переходом на главную страницу сайта с авторизованным аккаунтом
        driver.findElement(signInButton).click();
        Assert.assertTrue(elementIsDisplayed(phonesTabletsTab));

        // Переход во вкладку: "Запчасти для телефонов и планшетов "
        driver.findElement(phonesTabletsTab).click();
        Assert.assertTrue(elementIsDisplayed(displaysSubsection));

        // Переход в подраздел: "Дисплеи Сенсоры"
        driver.findElement(displaysSubsection).click();
        Assert.assertTrue(elementIsDisplayed(displaysSubsectionApple));

        // Переход в подраздел: "Дисплеи для Apple"
        driver.findElement(displaysSubsectionApple).click();
        Assert.assertTrue(elementIsDisplayed(displaysSubsectionApplePage));

        // Переход на третью страницу раздела: "Дисплеи для Apple"
        driver.findElement(displaysSubsectionApplePage).click();
        Assert.assertTrue(elementIsDisplayed(buyButton));

        // Нажатие на клавишу "купить" позицию: "Дисплей для Apple iPhone 11 Pro Max с тачскрином"
        driver.findElement(buyButton).click();
        Assert.assertTrue(elementIsDisplayed(trashBucket));

        // Товар: "Дисплей для Apple iPhone 11 Pro Max с тачскрином" добавлен в корзин .
        driver.findElement(trashBucket).click();
        Assert.assertTrue(elementIsDisplayed(logout));

        // Выход из аккаунта
        driver.findElement(logout).click();

        // Закрытие сайта(Теста)
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