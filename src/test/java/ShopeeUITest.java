import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ShopeeUITest {
    WebDriver driver;
    @Before
    public void setup() {
        // Tạo một instance của Logger cho Selenium WebDriver
        Logger logger = Logger.getLogger("org.openqa.selenium");
        // Thiết lập mức độ logging
        logger.setLevel(Level.OFF);

        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
        options.addArguments("--headless", "--whitelisted-ips=''");
        driver = new ChromeDriver(options);

        // Navigate to the Shopee homepage
        driver.get("https://shopee.vn/");

        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void testHomepageTitle() {
        String expected = "Shopee Việt Nam | Mua và Bán Trên Ứng Dụng Di Động Hoặc Website";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testHomepageLogo() {
        WebElement logoButton = driver.findElement(By.xpath("//header/div[2]/div[1]/a[1]/div[1]/*[1]"));
        assertEquals(true, logoButton.isDisplayed());
    }

    @Test
    public void testHomepageLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'Đăng Nhập')]"));
        assertEquals(true, loginButton.isDisplayed());
    }

    @Test
    public void testHomepageSearchBar() {
        // Find the search box element by its name attribute
        WebElement searchBar = driver.findElement(By.className("shopee-searchbar-input__input"));
        assertEquals(true, searchBar.isDisplayed());
    }

    @Test
    public void testHomepageCategories() {
        WebElement categoriesButton = driver.findElement(By.xpath("//body/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]"));
        assertEquals(true, categoriesButton.isDisplayed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
