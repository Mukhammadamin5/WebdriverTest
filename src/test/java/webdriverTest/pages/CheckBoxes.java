package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckBoxes {
    public CheckBoxes(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    public WebElement checkbox1;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    public WebElement checkbox2;
}
