import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {
    private static WebDriver webDriver;
    private static User user;
    private WebDriverWait wait;

    @BeforeClass
    public static void beforeAll() {
        System.setProperty("webdriver.gecko.driver", "C:\\Projects\\Drivers\\geckodriver.exe");
        user = RandomUser.generateCorrectUser(); //all tests should pass
        //user = RandomUser.generateExisting(); //for test purpose Registration test should fail, rest pass
       //user = RandomUser.generateWrongUser(); //for test purpose All tests should fail
        webDriver = new FirefoxDriver();

    }

    @AfterClass
    public static void afterAll() {
           webDriver.quit();
    }

    @Before
    public void beforeEach() {
        wait = new WebDriverWait(webDriver, 4, 2000);
    }

    @Test
    public void A_RegisterUniqueUser() {

        webDriver.get("https://user-data.hillel.it/html/registration.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration")));

        webDriver.findElement(By.className("registration")).click();
        webDriver.findElement(By.id("first_name")).sendKeys(user.getFirstName());
        webDriver.findElement(By.id("last_name")).sendKeys(user.getLastName());
        webDriver.findElement(By.id("field_work_phone")).sendKeys(user.getWorkPhone());
        webDriver.findElement(By.id("field_phone")).sendKeys(user.getMobilePhone());
        webDriver.findElement(By.id("field_email")).sendKeys(user.getEmail());
        webDriver.findElement(By.id("field_password")).sendKeys(user.getPassword());
        if (user.getGender().equals(Gender.Female)) {
            webDriver.findElement(By.id("female")).click();
        } else if (user.getGender().equals(Gender.Male)) {
            webDriver.findElement(By.id("male")).click();
        }
        if (user.getPosition().equals(Position.Manager)) {
            webDriver.findElement(By.cssSelector("option[value=manager]")).click();
        } else if (user.getPosition().equals(Position.QA)) {
            webDriver.findElement(By.cssSelector("option[value=qa]")).click();
        } else if (user.getPosition().equals(Position.Developer)) {
            webDriver.findElement(By.cssSelector("option[value=developer]")).click();
        }

        webDriver.findElement(By.id("button_account")).click();

        wait.until(ExpectedConditions.alertIsPresent());

        String result = webDriver.switchTo().alert().getText();
        webDriver.switchTo().alert().accept();

        Assert.assertEquals("Registration failed with error: " + result, "Successful registration", result);



    }

    @Test
    public void B_LoginUser() {
        webDriver.get("https://user-data.hillel.it/html/registration.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("authorization")));
        webDriver.findElement(By.className("authorization")).click();
        webDriver.findElement(By.id("password")).sendKeys(user.getPassword());
        webDriver.findElement(By.id("email")).sendKeys(user.getEmail());
        webDriver.findElement(By.className("login_button")).click();


        try {
            wait.until(ExpectedConditions.urlMatches("https://user-data.hillel.it/index.html"));
        } catch (TimeoutException e) {
            System.out.println("wait finished with TimeOut: " + e.getMessage() + " : " + e.getCause());
        }

        String result = webDriver.getCurrentUrl();
        System.out.println(webDriver.getCurrentUrl());
        Assert.assertEquals("Login failed", "https://user-data.hillel.it/index.html", result);

    }

    @Test
    public void C_searchUser() {
        Boolean result = false;
        webDriver.get("https://user-data.hillel.it/index.html");

        try {
            if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                System.out.println("alert present");
                System.out.println(webDriver.switchTo().alert().getText());
                webDriver.switchTo().alert().accept();
            }
        } catch (TimeoutException e) {
            System.out.println("wait finished with TimeOut: " + e.getMessage() + " : " + e.getCause());
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("employees")));
        webDriver.findElement(By.id("employees")).click();
        List<WebElement> tds = webDriver.findElements(By.cssSelector("td"));
        for (WebElement td : tds) {

            if (td.getText().equals(user.getEmail())) {
                result = true;
                System.out.println("found: " + td.getText());
            }
        }

        Assert.assertEquals("User is not present", true, result);

    }

}
