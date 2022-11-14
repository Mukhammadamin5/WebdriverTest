package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DropDownPage {
    public DropDownPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//option[.='Option 1']")
    public WebElement option1;

    @FindBy(xpath = "//option[.='Option 2']")
    public WebElement option2;

    @FindBy(xpath = "//select[@id='dropdown']")
    public WebElement dropDown;
}
