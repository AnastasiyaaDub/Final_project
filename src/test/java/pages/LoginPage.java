package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // Поле "Email"
    private SelenideElement emailInput = $(byName("email"));

    // Поле "Пароль"
    private SelenideElement passwordInput = $(byName("password"));

    // Кнопка "Войти". Ищем по тексту "Войти"
    private SelenideElement loginButton = $(byXpath(".//button[text()='Войти']"));

    // Кнопка "Нет аккаунта"
    private SelenideElement noAccountButton = $(byXpath(".//button[text()='Нет аккаунта']"));



    public LoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.shouldBe(visible).click();
    }

    // Составной метод для логина
    public void fillLoginForm(String email, String password) {
        setEmail(email);
        setPassword(password);

    }

    // Метод клика по кнопке "Нет аккаунта"
    public void clickNoAccount() {
        noAccountButton.shouldBe(visible).click();
    }
}
