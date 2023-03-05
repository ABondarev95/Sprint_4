import org.openqa.selenium.*;

public class ScooterForWhoPage {
    private WebDriver driver;
    private By nameField = By.xpath("//div[2]/div[1]/input"); // поле ввода имени
    private By surnameField = By.xpath("//div[2]/div[2]/input"); // поле ввода фамилии
    private By addressField = By.xpath("//div[2]/div[3]/input"); // поле ввода адреса
    private By metroField = By.xpath("//div[4]//input"); // поле выбора метро
    private By phoneNumberField = By.xpath("//div[2]/div[5]/input"); // поле ввода номера телефона
    private By nextStep = By.xpath("//button[text()='Далее']"); // кнопка "Далее"

    public ScooterForWhoPage(WebDriver driver){
        this.driver = driver;
    }
    // заполнение поля "Имя"
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    // заполнение поля "Фамилия"
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    // заполнение поля "Адрес"
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // заполнение поля "Станция метро"
    public void setMetro(String metro) {
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(By.xpath("//button/div[text()='"+ metro +"']")).click();
    }
    // заполнение поля "Телефон"
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    // Заполнение полей заказа
    public void orderPage1(String name, String surname, String address, String metro, String phoneNumber){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhoneNumber(phoneNumber);
    }
    // Нажатие кнопки "Далее"
    public void clickNextStep() {
        driver.findElement(nextStep).click();
    }
}
