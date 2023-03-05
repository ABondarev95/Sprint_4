import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageSamokat {
    private WebDriver driver;
    // первый элемент в списке "Вопросы о важном"
    private By faqListElement1 = By.id("accordion__heading-0");
    // раскрывающийся текст первого элемента в списке "Вопросы о важном"
    private By faqListPanelElement1 = By.id("accordion__panel-0");
    private By orderInHeader = By.className("Button_Button__ra12g"); // кнопка "Заказать" в хедере

    public MainPageSamokat(WebDriver driver){
        this.driver = driver;
    }
    // Найти вопросы о важном и сделать скролл до них
    public void scrollToFaq() {
        WebElement element = driver.findElement(faqListElement1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Нажатие на элемент списка "Вопросы о важном"
    public void clickFaqListHeader() {
        driver.findElement(faqListElement1).click();
    }
    // Нажатие на кнопку "Заказать" в хедере
    public void clickOrderInHeader() {
        driver.findElement(orderInHeader).click();
    }
    // Метод ожидания прогрузки текста
    public void waitForFaqListPanelOpen() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(faqListPanelElement1));
    }
    // Метод получения текста из открытого элемента списка "Вопросы о важном"
    public String getFaqListPanelElement1Text(){
        return driver.findElement(faqListPanelElement1).getText();
    }

}
