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

public class WeatherTest {
    private WebDriver driver;
    JavascriptExecutor js;


    //locators
    private final By searchLine = By.id("js-search");
    private final By findCity = By.id("js-search");
    private final By citySelectionAndForecastForToday= By.cssSelector(".js-myloc-uid-4944 .founditem__desc:nth-child(3)");
    private final By forecastForTomorrow = By.linkText("Завтра");
    private final By forecastForThreeDays = By.linkText("3 дня");
    private final By tenDaysForecast = By.linkText("10 дней");
    private final By forecastFor2Weeks = By.linkText("2 недели");
    private final By forecastForTheMonth = By.linkText("Месяц");

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
    public void wheaterTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Размер открытого окна
        driver.manage().window().setSize(new Dimension(866, 708));


        // Открываем сайт погоды gismeteo.ua
        driver.get("https://www.gismeteo.ua/");
        Assert.assertTrue(elementIsDisplayed(searchLine));

        // Ставим курсор в строку поиска и вводим город который нам нужен
        driver.findElement(searchLine).click();
        Assert.assertTrue(elementIsDisplayed(findCity));

        // Вводим в строку поиска город Киев
        driver.findElement(findCity).sendKeys("Киев");
        Assert.assertTrue(elementIsDisplayed(citySelectionAndForecastForToday));

        // Нажимаем на название и переходим на страницу с прогнозом погоды на сегодня в городе Киев
        driver.findElement(citySelectionAndForecastForToday).click();
        Assert.assertTrue(elementIsDisplayed(forecastForTomorrow));

        // Просмотр прогноза погоды на завтра в городе Киев
        driver.findElement(forecastForTomorrow).click();
        Assert.assertTrue(elementIsDisplayed(forecastForThreeDays));

        // Просмотр прогноза погоды на три дня в городе Киев
        driver.findElement(forecastForThreeDays).click();
        Assert.assertTrue(elementIsDisplayed(tenDaysForecast));

        // Просмотр прогноза погоды на десять дней в городе Киев
        driver.findElement(tenDaysForecast).click();
        Assert.assertTrue(elementIsDisplayed(forecastFor2Weeks));

        // Просмотр прогноза погоды на две недели в городе Киев
        driver.findElement(forecastFor2Weeks).click();
        Assert.assertTrue(elementIsDisplayed(forecastForTheMonth ));

        // Просмотр прогноза погоды на месяц в городе Киев
        driver.findElement(forecastForTheMonth).click();

        // Закрыть сайт погоды gismeteo.ua
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
