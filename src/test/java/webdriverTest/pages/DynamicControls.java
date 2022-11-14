package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicControls {
    public DynamicControls(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@type='button'])[1]")
    public WebElement removeAndAddBtn;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement checkbox;

    @FindBy(xpath = "//p[@id='message']")
    public WebElement message;

    @FindBy(xpath = "(//button[@type='button'])[2]")
    public WebElement enable_disableBtn;
}
