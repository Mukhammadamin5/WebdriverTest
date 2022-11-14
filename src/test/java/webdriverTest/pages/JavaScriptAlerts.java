package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JavaScriptAlerts {
    public JavaScriptAlerts(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button)[1]")
    public WebElement JSAlert;

    @FindBy(xpath = "(//button)[2]")
    public WebElement JSConfirm;

    @FindBy(xpath = "(//button)[3]")
    public WebElement JSPrompt;


}
