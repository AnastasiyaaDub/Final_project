package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // Поле "Email"
    private SelenideElement emailInput = $(byName("email"));

    // Поле "Пароль"
    private SelenideElement passwordInput = $(byName("password"));

    // Поле "Повторите пароль"
    private SelenideElement confirmPasswordInput = $(byName("submitPassword"));

    // Кнопка "Создать аккаунт"
    private SelenideElement createAccountButton = $(byXpath(".//button[text()='Создать аккаунт']"));

    // Локатор сообщения об ошибке
    private SelenideElement errorMessage = $(".input_span__yWPqB");



    // Метод заполнения поля Email
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    // Метод заполнения поля Пароль
    public RegistrationPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    // Метод заполнения поля Повтор пароля
    public RegistrationPage setConfirmPassword(String password) {
        confirmPasswordInput.setValue(password);
        return this;
    }

    // Метод клика по кнопке "Создать аккаунт"
    public void clickCreateAccount() {
        createAccountButton.shouldBe(visible).click();
    }

    // Метод проверки появления ошибки
    public void checkErrorMessageText(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
    }

    public RegistrationPage fillForm(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        confirmPasswordInput.setValue(password);
        return this;
    }

}
