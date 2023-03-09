package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageSamokat {
    private final WebDriver driver;
    private final By faqBlockHeader = By.xpath("//div[text()='Вопросы о важном']"); // Заголовок блока "Вопросы о важном
    private By faqHeader = By.id("accordion__heading"); // Заголовок списка "Вопросы о важном"
    private By faqBody = By.id("accordion__panel"); // Раскрывающееся сообщение в списке "Вопросы о важном
    private By orderButton = By.xpath("//button"); // кнопка "Заказать"

    public MainPageSamokat(WebDriver driver){
        this.driver = driver;
    }
    // Найти вопросы о важном и сделать скролл до них
    public void scrollToFaq() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqBlockHeader));
    }
    // Выбор элемента в списке "Вопросы о важном"
    public void setFaqElement (int id){
        faqHeader = By.id("accordion__heading-" + id);
        faqBody = By.id("accordion__panel-" + id);
    }
    // Выбор кнопки "Закать" на главной странице при различных входных условиях
    public void setOrderButton(boolean button){
        if(button){
            orderButton = By.xpath("//div[1]/div[2]/button[1]");
        } else {
            orderButton = By.xpath("//div[4]/div[2]/div[5]/button");
        }
    }
    // Нажатие на кнопку "Заказать" в хедере
    public void findClickOrderMainPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        driver.findElement(orderButton).click();
    }
    // Метод поиска текста в раскрывающемся списке "Вопросы о важном"
    public String checkFaqList(){
        driver.findElement(faqHeader).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(faqBody)));
        return driver.findElement(faqBody).getText();
    }
}
