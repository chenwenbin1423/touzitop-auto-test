package com.touzitop.automation.page;

import com.touzitop.automation.utils.WebDriverUtils;
import com.touzitop.automation.utils.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    //private String URL = "http://passport.isoftstone.com/";
    private String URL = null;

    //private Logger logger = Logger.getLogger(WebDriverUtils.class);

    public void init() {

        setBrowserProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    protected void init(String url) {

        setBrowserProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        setPageUrl(url);
        driver.get(URL);
    }

    private void setBrowserProperties() {
        if ("windows".contains(CommonMethods.isWindowsOrLinuxOS())) {

            System.setProperty("webdriver.chrome.driver","target/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.bin","C:/'Program Files'/Google/Chrome/Application/chrome.exe");
            System.out.println("script is running on os Windows - " +
                    System.getProperty("os.name") + " " +
                    System.getProperty("os.arch") + " " +
                    System.getProperty("os.version")
            );
        } else if ("linux".contains(CommonMethods.isWindowsOrLinuxOS())) {

            System.setProperty("webdriver.chrome.driver","target/drivers/chromedriver");
            System.setProperty("webdriver.chrome.bin","/opt/google/chrome/chrome");
            System.out.println("script is running on os Windows - " +
                    System.getProperty("os.name") + " " +
                    System.getProperty("os.arch") + " " +
                    System.getProperty("os.version")
            );
        }
    }

    public String getPageUrl() {
        return URL;
    }

    public void setPageUrl(String url) {
        URL = url;
    }

    public void quit() {
        driver.quit();
    }

    // get all attributes in an element
    public Object getAllAttributes(WebElement ele){
        Object attrs = ((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", ele);
        return attrs;
    }


    public WebElement findElement(By by) {
        WebElement element = waitElementVisible(by);
        return element;
    }

    //??????????????????
    public WebElement waitElementVisible(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webElement;
    }

    //?????????????????????
    public WebElement waitElementClickable(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
    }

    //????????????
    public void click(By by) {
        waitElementClickable(by).click();
        //TODO ???????????????????????????
        //logger.info("?????????" + by + "?????????");
    }

    //????????????
    public void click(WebElement element) {

        WebDriverWait webDriverWait = new WebDriverWait(WebDriverUtils.driver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        //TODO ???????????????????????????
        //logger.info("?????????" + element + "?????????");
    }

    //????????????
    public void type(By by, String inputData) {
        waitElementVisible(by).sendKeys(inputData);
        //TODO ???????????????????????????
        //logger.info("????????????" + by + "??????????????????" + "???");
    }

    public void action(){
        Actions action = new Actions(driver);
        //TODO
    }


}
