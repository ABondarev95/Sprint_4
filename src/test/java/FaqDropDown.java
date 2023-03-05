import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

public class FaqDropDown {
    private WebDriver driver;
    @Test
    public void faqDropDown(){
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Создание объекта класса главной страницы приложения
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        // Пролистать до "Вопросы о важном"
        objMainPage.scrollToFaq();
        // Кликнуть на первый элемент списка "Вопросы о важном"
        objMainPage.clickFaqListHeader();
        // Подождать появления текста
        objMainPage.waitForFaqListPanelOpen();
        // Проверка, что текст в раскрывающемся окне совпадает с ожидаемым
        Assert.assertEquals("Текст в раскрывающемся окне не совпадает",
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                objMainPage.getFaqListPanelElement1Text());
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
