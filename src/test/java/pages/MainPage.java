package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    // URL главной страницы
    private static final String MAIN_PAGE_URL = "https://qa-desk.stand.praktikum-services.ru/";

    // Кнопка "Вход и регистрация"
    private SelenideElement loginAndRegisterButton = $(byXpath(".//button[text()='Вход и регистрация']"));

    // Кнопка "Разместить объявление"
    private SelenideElement placeAdButton = $(byXpath(".//button[text()='Разместить объявление']"));

    // Иконка пользователя (переход в ЛК)
    private SelenideElement profileIcon = $(".circleSmall");

    // Локатор имени пользователя в шапке
    private SelenideElement userNameLabel = $(".profileText.name");


    // Метод открытия главной страницы
    public MainPage openMainPage() {
        open(MAIN_PAGE_URL);
        return this;
    }

    // Метод клика по кнопке "Вход и регистрация"
    public void clickLoginAndRegisterButton() {
        loginAndRegisterButton.shouldBe(visible).click();
    }

    // Метод клика по кнопке "Разместить объявление"
    public void clickPlaceAdButton() {
        placeAdButton.shouldBe(visible).click();
    }

    // Метод перехода в Личный Кабинет
    public void clickProfileIcon() {
        profileIcon.shouldBe(visible).click();
    }
    // Метод проверки, что пользователь залогинен (имя отображается)
    public void checkUserLoggedIn() {
        userNameLabel.shouldBe(visible);
    }
}
