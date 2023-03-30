package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.AboutRentPage;
import page_objects.MainPageSamokat;
import page_objects.ScooterForWhoPage;
import constants.Links;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final boolean isHeaderButton; // Указатель на местоположение кнопки "Заказать" на главное странице
    private WebDriver driver;
    private final String name; // Имя
    private final String surname; // Фамилия
    private final String address; // Адрес
    private final String metroStation; // Станция метро
    private final String phoneNumber; // Номер телефона
    private final String date; // Дата доставки
    private final String period; // Срок аренды
    private final String color; // Цвет
    private final String comment; // Комментарий для курьера
    public CreateOrderTest(boolean isHeaderButton,
                           String name,
                           String surname,
                           String address,
                           String metroStation,
                           String phoneNumber,
                           String date,
                           String period,
                           String color,
                           String comment) {
        this.isHeaderButton = isHeaderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }
    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] fillingFields() {
        return new Object[][]{
                {true, "Юрий", "Гагарин", "Сокольническая Площадь 4А", "Сокольники", "+78009991122", "30.03.2023", "сутки", "чёрный жемчуг", "Первый позитивный сценарий"},
                {false,"Олег", "Ленин", "улица Мясницкая, 6", "Лубянка", "88005553535", "01.04.2023", "семеро суток", "серая безысходность", "Второй позитивный сценарий"},
        };
    }
    @Test
    // Тест запускат проверку создания заказа
    public void createOrderTest(){
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    // Ниже запуск драйвера для FireFox для отладки
//        FirefoxOptions options = new FirefoxOptions();
//        driver = new FirefoxDriver(options);
        // переход на страницу тестового приложения
        driver.get(Links.SITE_SAMOKAT);
        // Создание объекта класса главной страницы приложения
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        // Выбор кнопки "Заказать"
        objMainPage.setOrderButton(isHeaderButton);
        // Найти и нажать на кнопку "Заказать"
        objMainPage.findClickOrderMainPage();
        // Создание объекта класса страницы "Для кого самокат"
        ScooterForWhoPage objScooterForWho = new ScooterForWhoPage(driver);
        // Заполнение полей первой страницы заказа "Для кого самокат"
        objScooterForWho.fillingFieldsScooterForWhoPage(name, surname, address, metroStation, phoneNumber);
        // Нажатие кнопки "Далее"
        objScooterForWho.clickNextStep();
        // Создание объекта класса страницы "Про аренду"
        AboutRentPage objAboutRentPage = new AboutRentPage(driver);
        // Заполнение полей второй страницы заказа "Про аренду"
        objAboutRentPage.fillingFieldsAboutRentPage(date, period, color, comment);
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
