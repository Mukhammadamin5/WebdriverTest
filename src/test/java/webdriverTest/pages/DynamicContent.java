package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicContent {
    public DynamicContent(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='large-10 columns']")
    public WebElement before;

    @FindBy(xpath = "//div[@class='large-10 columns']")
    public WebElement after;
}
