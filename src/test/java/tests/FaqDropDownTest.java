package tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.MainPageSamokat;
import constants.Links;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqDropDownTest {
    private WebDriver driver;
    private final int faqListId; // id списка элемента "Вопросы о важном"
    private final String faqBodyText; // текст внутри раскрывающегося списка

    // Конструктор класса с двумя параметрами
    public FaqDropDownTest(int faqListId, String faqBodyText) {
        this.faqListId = faqListId;
        this.faqBodyText = faqBodyText;
        }
    // Метод получения тестовых данных
    @Parameterized.Parameters
    public static Object[][] getFaqListText() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7,"Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    @Test
    public void faqDropDownTest(){
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        driver.get(Links.SITE_SAMOKAT);
        // Создание объекта класса главной страницы приложения
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        // Пролистать до "Вопросы о важном"
        objMainPage.scrollToFaq();
        // Выбор элемента из списка "Вопросы о важном"
        objMainPage.setFaqElement(faqListId);
        // Проверка соответствия текста раскрывающегося списка "Вопросы о важном"
        String actual = objMainPage.checkFaqList();
        assertEquals("Текст раскрывающегося списка 'Вопросы о важном' не соответствует ожидаемому результату", faqBodyText, actual);
    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
