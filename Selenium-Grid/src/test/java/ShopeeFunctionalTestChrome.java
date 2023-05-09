import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ShopeeFunctionalTestChrome {
    WebDriver driver;
    @Before
    public void setup() throws MalformedURLException {
        // Tạo một instance của Logger cho Selenium WebDriver
        Logger logger = Logger.getLogger("org.openqa.selenium");
        // Thiết lập mức độ logging
        logger.setLevel(Level.OFF);

        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
//        options.addArguments("--headless", "--whitelisted-ips=''");

        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");

        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

//        driver = new ChromeDriver(options);

        // Navigate to the Shopee homepage
        driver.get("https://shopee.vn/");

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.titleContains("Shopee Việt Nam | Mua và Bán Trên Ứng Dụng Di Động Hoặc Website"));

        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }

    @Test
    public void testSearchbar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopee-searchbar-input__input")));

        // Find the search box element by its name attribute
        WebElement searchBox = driver.findElement(By.className("shopee-searchbar-input__input"));
        // Enter a search term into the search box
        searchBox.sendKeys("Iphone 14");

        WebElement input = driver.findElement(By.className("shopee-searchbar-input__input"));

        String expected = "Iphone 14";
        String actual = input.getAttribute("value");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopee-searchbar-input__input")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-solid-primary")));

        // Find the search box element by its name attribute
        WebElement searchBox = driver.findElement(By.className("shopee-searchbar-input__input"));
        // Enter a search term into the search box
        searchBox.sendKeys("Iphone 14");

        WebElement searchButton = driver.findElement(By.className("btn-solid-primary"));
        searchButton.click();

        wait.until(ExpectedConditions.urlToBe("https://shopee.vn/search?keyword=iphone%2014"));
        String expected = "https://shopee.vn/search?keyword=iphone%2014";
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
