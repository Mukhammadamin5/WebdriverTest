package webdriverTest.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import webdriverTest.pages.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestSpecificRubrics {
    protected static WebDriver driver;


    @BeforeSuite
    public void setUpMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDownMethod(){
        driver.quit();
    }

    @Test
    public void loginSuccess() {
        driver.get("http://localhost:7080/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.userName.sendKeys("tomsmith");
        loginPage.password.sendKeys("SuperSecretPassword!");

        loginPage.loginBtn.click();
        Assert.assertTrue(loginPage.message.getText().contains("You logged into a secure area!"));
    }

    @Test
    public void loginFailure() {
        driver.get("http://localhost:7080/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.userName.sendKeys("wrong");
        loginPage.password.sendKeys("wrong");

        loginPage.loginBtn.click();
        Assert.assertTrue(loginPage.message.getText().contains("Your username is invalid!"));
    }

    @Test
    public void checkboxes(){
        driver.get("http://localhost:7080/checkboxes");
        CheckBoxes checkBoxes = new CheckBoxes(driver);
        checkBoxes.checkbox1.click();

        Assert.assertTrue(checkBoxes.checkbox1.isSelected(), "checkbox1 is not selected");

        Assert.assertTrue(checkBoxes.checkbox2.isSelected(), "checkbox1 is not selected");
    }

    @Test
    public void dragAndDrop(){
        driver.get("http://localhost:7080/drag_and_drop");
        DragAndDrop dragAndDrop = new DragAndDrop(driver);

        Actions actions = new Actions(driver);
        actions.clickAndHold(dragAndDrop.columA).moveToElement(dragAndDrop.columB).click().release(dragAndDrop.columB).build().perform();
//        actions.dragAndDrop(dragAndDrop.columB, dragAndDrop.columA).build().perform();
    }

    @Test(priority = 18)
    public void contextMenu(){
        driver.get("http://localhost:7080/context_menu");
        Actions actions = new Actions(driver);
        ContextMenu contextMenu = new ContextMenu(driver);

        actions.contextClick(contextMenu.box).perform();

        Assert.assertEquals(driver.switchTo().alert().getText(), "You selected a context menu");

    }

    @Test
    public void dropdown(){
        driver.get("http://localhost:7080/dropdown");
        DropDownPage dropDownPage = new DropDownPage(driver);
        Select select = new Select(dropDownPage.dropDown);
        select.selectByVisibleText("Option 1");

        Assert.assertTrue(dropDownPage.option1.isSelected());
        select.selectByVisibleText("Option 2");

        Assert.assertTrue(dropDownPage.option2.isDisplayed());
    }

    @Test
    public void dynamicContent() throws InterruptedException {
        driver.get("http://localhost:7080/dynamic_content");
        DynamicContent dynamicContent = new DynamicContent(driver);

        driver.navigate().refresh();
        driver.navigate().refresh();

        if(!dynamicContent.before.getText().equals(dynamicContent.after.getText())){
            Assert.assertTrue(true);
        }
        Thread.sleep(5000);
    }

    @Test
    public void dynamicControls(){
        driver.get("http://localhost:7080/dynamic_controls");
        DynamicControls dynamicControls = new DynamicControls(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        dynamicControls.removeAndAddBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//input[@type='checkbox']"))));
        Assert.assertTrue(dynamicControls.message.isDisplayed());

        dynamicControls.removeAndAddBtn.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//p[@id='message']"), "It's back!"));
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='message']")).getText(), "It's back!");


        dynamicControls.enable_disableBtn.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//p[@id='message']"), "It's enabled!"));
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='message']")).getText(), "It's enabled!");
    }

    @Test
    public void dynamicLoading(){
        driver.get("http://localhost:7080/dynamic_loading/2");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        DynamicLoading dynamicLoading = new DynamicLoading(driver);

        dynamicLoading.startBtn.click();

        wait.until(ExpectedConditions.visibilityOf(dynamicLoading.text));
        Assert.assertTrue(dynamicLoading.text.isDisplayed());
    }

    @Test
    public void fileDownload() throws InterruptedException {
        driver.get("http://localhost:7080/download");
        FileDownload fileDownload = new FileDownload(driver);

        fileDownload.download.click();
        Thread.sleep(2000);

        File fileLocation = new File("C:\\Users\\HP_001\\Downloads");

        File [] files = fileLocation.listFiles();
        boolean downloadedOrNot = false;

        for (File file : files){
            if (file.getName().equals("some-file.txt")){
                System.out.println("File downloaded");
                downloadedOrNot = true;
                break;
            }
        }
        Assert.assertTrue(downloadedOrNot, "File not downloaded");
    }

    @Test
    public void fileUpload(){
        driver.get("http://localhost:7080/upload");
        FileUpload fileUpload = new FileUpload(driver);
        fileUpload.upload.sendKeys("C:\\Users\\HP_001\\Downloads\\some-file.txt");
        fileUpload.fileSubmitBtn.click();
        Assert.assertTrue(fileUpload.msg.isDisplayed());
    }

    @Test
    public void floatingMenu(){
        driver.get("http://localhost:7080/floating_menu");
        WebElement floatingMenu = driver.findElement(By.xpath("//h3[.='Floating Menu']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)", "");
        Assert.assertTrue(floatingMenu.isDisplayed());
    }

    @Test
    public void iframe(){
        driver.get("http://localhost:7080/iframe");
        Iframe iframe = new Iframe(driver);

        iframe.close.click();
        driver.switchTo().frame("mce_0_ifr");

        iframe.textArea.sendKeys("Hello ");
        Assert.assertEquals(iframe.textArea.getText(), "Hello Your content goes here.");
    }

    @Test
    public void mouseHover(){
        driver.get("http://localhost:7080/hovers");
        Actions actions = new Actions(driver);
        MouseHover mouseHover = new MouseHover(driver);

        actions.moveToElement(mouseHover.image1).perform();
        Assert.assertTrue(mouseHover.user1.isDisplayed());

        actions.moveToElement(mouseHover.image2).perform();
        Assert.assertTrue(mouseHover.user2.isDisplayed());

        actions.moveToElement(mouseHover.image3).perform();
        Assert.assertTrue(mouseHover.user3.isDisplayed());
    }

    @Test
    public void javaScriptAlerts(){
        driver.get("http://localhost:7080/javascript_alerts");
        JavaScriptAlerts javaScriptAlerts = new JavaScriptAlerts(driver);

        javaScriptAlerts.JSAlert.click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Alert");
        driver.switchTo().alert().accept();

        javaScriptAlerts.JSConfirm.click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.xpath("(//p)[2]")).getText(), "You clicked: Ok");

        javaScriptAlerts.JSPrompt.click();
        driver.switchTo().alert().sendKeys("Hello");
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.xpath("(//p)[2]")).getText().contains("Hello"));
    }

    @Test
    public void javaScriptError() {
        driver.get("http://localhost:7080/javascript_error");

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        logEntries.filter(Level.ALL);
        List<LogEntry> logs = logEntries.filter(Level.ALL);

        for (LogEntry logEntry : logs){
            if(logEntry.toString().contains("Cannot read properties of undefined (reading 'xyz')")) {
                Assert.assertTrue(logEntry.toString().contains("Cannot read properties of undefined (reading 'xyz')"));
            }
        }
    }

    @Test
    public void openInNewTab(){
        driver.get("http://localhost:7080/windows");
        driver.findElement(By.linkText("Click Here")).click();

        String actualTitle;
        for(String eachWindow : driver.getWindowHandles()){
            driver.switchTo().window(eachWindow);
        }

        actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle, "New Window");
    }

    @Test
    public void notificationMessage(){
        driver.get("http://localhost:7080/notification_message_rendered");
        NotificationMessage notificationMessage = new NotificationMessage(driver);

        while (true) {
            if (driver.findElement(By.xpath("//div[@id='flash-messages']")).getText().contains("Action successful")) {
                break;
            } else {
                try {
                    notificationMessage.clickBtn.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println();
                    notificationMessage.clickBtn.click();
                } finally {
                    notificationMessage.clickBtn.click();
                }
            }
        }
        System.out.println("Action successful");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='flash']")).getText().contains("Action successful"));

    }




}
