import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ShopeeUITestFirefox {
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
        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");

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
    public void testHomepageTitle() {
        String expected = "Shopee Việt Nam | Mua và Bán Trên Ứng Dụng Di Động Hoặc Website";
        String actual = (String) ((JavascriptExecutor) driver).executeScript("return document.title;");
        assertEquals(expected, actual);
    }

    @Test
    public void testHomepageLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header/div[2]/div[1]/a[1]/div[1]/*[1]")));
        WebElement logoButton = driver.findElement(By.xpath("//header/div[2]/div[1]/a[1]/div[1]/*[1]"));
        assertEquals(true, logoButton.isDisplayed());
    }

    @Test
    public void testHomepageLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Đăng Nhập')]")));
        WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'Đăng Nhập')]"));

        if (!loginButton.isDisplayed()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(true, loginButton.isDisplayed());
    }

    @Test
    public void testHomepageSearchBar() {
        // Find the search box element by its name attribute
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopee-searchbar-input__input")));
        WebElement searchBar = driver.findElement(By.className("shopee-searchbar-input__input"));

        if (!searchBar.isDisplayed()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(true, searchBar.isDisplayed());
    }

    @Test
    public void testLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Đăng Nhập')]")));
        WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'Đăng Nhập')]"));
        loginButton.click();

        wait.until(ExpectedConditions.urlToBe("https://shopee.vn/buyer/login?next=https%3A%2F%2Fshopee.vn%2F"));

        String expected = "https://shopee.vn/buyer/login?next=https%3A%2F%2Fshopee.vn%2F";
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
