import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutRentPage {
    private WebDriver driver;
    private By dateInput = By.xpath("//div[2]/div[1]//input"); // поле выбора даты
    private By rentalPeriod = By.className("Dropdown-arrow"); // поле указания периода аренды
    private By scooterColor = By.xpath("//div[3]/label[1]"); // чек-бокс выбора цвета самоката
    private By commentForCourier = By.xpath("//div[4]/input"); // поле комментарий для курьера
    private By createOrderButton = By.xpath("//div[3]/button[text()='Заказать']"); // Кнопка "Заказать" внизу страницы
    private By orderModalWindow = By.className("Order_ModalHeader__3FDaJ"); // Модальное окно "Хотите оформить заказ?"
    private By yesButtonModal = By.xpath("//button[text()='Да']"); // Кнопка "Да" в модальном окне
    private By orderConfirmationModal = By.xpath("//div[text()='Заказ оформлен']"); // Заголовок "Заказ оформлен" в модальном окне
    public AboutRentPage(WebDriver driver){
        this.driver = driver;
    }
    // Выбор даты доставки
    public void setDate(String date){
        driver.findElement(dateInput).sendKeys(date, Keys.RETURN);
    }
    // Выбор срока аренды
    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath("//div[2]/div[text()='"+ period +"']")).click(); // Выбор периода из выпадающего списка
    }
    // Выбор цвета самоката
    public void setScooterColor(String color){
        driver.findElement(By.xpath("//label[text()='"+ color + "']")).click();
    }
    // Добавление комментария для курьера
    public void setCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }
    // Заполнение всех полей страницы
    public void orderPage2(String date, String period, String color, String comment){
        setDate(date);
        setRentalPeriod(period);
        setScooterColor(color);
        setCommentForCourier(comment);
    }
    // Метод создания и подтверждения заказа
    public void createOrder(){
        driver.findElement(createOrderButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalWindow));
        driver.findElement(yesButtonModal).click();
    }
    // Метод получения информации о создании заказа
    public String getOrderConfirmationText(){
        return driver.findElement(orderConfirmationModal).getText();
    }
}
