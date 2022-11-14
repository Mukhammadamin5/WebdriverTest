package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationMessage {
    public NotificationMessage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(partialLinkText = "Click here")
    public WebElement clickBtn;

    @FindBy(xpath = "//div[@id='flash-messages']")
    public WebElement msg;
}
