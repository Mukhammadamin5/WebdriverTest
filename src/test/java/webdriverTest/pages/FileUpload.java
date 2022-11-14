package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileUpload {
    public FileUpload(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name=\"file\"]")
    public WebElement upload;

    @FindBy(id = "file-submit")
    public WebElement fileSubmitBtn;

    @FindBy(xpath = "//h3")
    public WebElement msg;
}
