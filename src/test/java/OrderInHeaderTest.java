import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OrderInHeaderTest {
    private WebDriver driver;
    @Test
    // Тест запускат проверку заказа через нажатие "Заказать" в заголовке страницы
    public void orderInHeader(){
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    // Ниже запуск драйвера для FireFox для отладки
//        FirefoxOptions options = new FirefoxOptions();
//        driver = new FirefoxDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Создание объекта класса главной страницы приложения
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        // Нажатие на кнопку заказать в хедере
        objMainPage.clickOrderInHeader();
        // Создание объекта класса страницы заказа
        ScooterForWhoPage objScooterForWho = new ScooterForWhoPage(driver);
        // Заполнение полей первой страницы заказа "Для кого самокат"
        objScooterForWho.orderPage1("Александр", "Бондарев", "Сокольническая Площадь 4А", "Сокольники", "+78009991122");
        // Нажатие кнопки "Далее"
        objScooterForWho.clickNextStep();
        // Создание объекта класса страницы "Про аренду"
        AboutRentPage objAboutRentPage = new AboutRentPage(driver);
        // Заполнение поле второй страницы заказа "Про аренду"
        objAboutRentPage.orderPage2("30.03.2023","сутки", "чёрный жемчуг", "Комментарий для тестирования");
        // Нажать "Заказать" и подтвердить
        objAboutRentPage.createOrder();
        // Проверить, что заказ создан и текст содержит "Заказ оформлен"
        Assert.assertTrue("Заявление не было создано или текст модального окна не соответствует ожидаемому",objAboutRentPage.getOrderConfirmationText().contains("Заказ оформлен"));
    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
