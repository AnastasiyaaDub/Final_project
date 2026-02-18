package steps;

import api.AuthApi;
import io.cucumber.java.ru.*;
import pages.*;
import model.User;
import model.DataGenerator;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static java.time.zone.ZoneRulesProvider.refresh;
import static org.openqa.selenium.devtools.v115.network.Network.clearBrowserCookies;

public class StepDefinitions {

    MainPage mainPage = new MainPage();
    RegistrationPage registrationPage = new RegistrationPage();
    LoginPage loginPage = new LoginPage();
    NewAdPage newAdPage = new NewAdPage();
    ProfilePage profilePage = new ProfilePage();


    private User currentUser;
    private String currentAdTitle;

    @Дано("Открыть главную страницу")
    public void openMainPage() {
        mainPage.openMainPage();
    }
    @Дано("Создать объявление")
    public void createAdViaUI() {
        // Мы уже залогинены (из Background), поэтому просто выполняем шаги
        mainPage.clickPlaceAdButton();

        // Генерируем данные
        currentAdTitle = "Продам велик " + UUID.randomUUID().toString().substring(0, 5);
        newAdPage.fillForm(currentAdTitle, "Описание для редактирования", "1000");

        newAdPage.clickPublish();

        // Ждем, пока форма закроется и мы вернемся на главную или в профиль
        // Можно добавить небольшую проверку, что мы успешно вышли из формы
        mainPage.clickProfileIcon(); // Сразу переходим в профиль для надежности
        profilePage.checkMyAdsSectionLoaded();
    }

    @Когда("Нажать кнопку {string}")
    public void clickButton(String buttonName) {
        if (buttonName.equals("Вход и регистрация")) {
            mainPage.clickLoginAndRegisterButton();
        }else if (buttonName.equals("Нет аккаунта")) {
                loginPage.clickNoAccount();
        } else if (buttonName.equals("Создать аккаунт")) {
            registrationPage.clickCreateAccount();
        } else if (buttonName.equals("Войти")) {
        loginPage.clickLoginButton();
    } else if (buttonName.equals("Разместить объявление")) {
            mainPage.clickPlaceAdButton();
        } else if (buttonName.equals("Опубликовать")) {
            newAdPage.clickPublish();
        }
    }

    @И("Заполнить форму регистрации валидными данными")
    public void fillRegistrationForm() {
        currentUser = DataGenerator.generateRandomUser();

        registrationPage.fillForm(currentUser.email(), currentUser.password());
    }


    @Тогда("Имя пользователя отображается в шапке профиля")
    public void checkUserNameInHeader() {
        mainPage.checkUserLoggedIn();
    }

    @И("Пользователь уже зарегистрирован в системе")
    public void userIsAlreadyRegistered() {
        // Генерируем данные
        currentUser = DataGenerator.generateRandomUser();
        // Регистрируем через API, чтобы занять email
        AuthApi.register(currentUser.email(), currentUser.password());
    }

    @И("Заполнить форму регистрации данными уже существующего пользователя")
    public void fillFormWithExistingUser() {
        // Заполняем форму теми же данными, что мы зарегистрировали через API
        registrationPage.fillForm(currentUser.email(), currentUser.password());
    }

    @Тогда("Отображается сообщение об ошибке {string}")
    public void checkErrorMessage(String expectedError) {
        registrationPage.checkErrorMessageVisible();
    }

    @И("Заполнить форму авторизации данными созданного пользователя")
    public void fillLoginFormWithExistingUser() {
        // Используем данные из переменной currentUser, которую создали в шаге "Пользователь уже зарегистрирован"
        loginPage.login(currentUser.email(), currentUser.password());
    }

    @И("Заполнить форму объявления")
    public void fillAdForm() {
        currentAdTitle = "Продам велик " + UUID.randomUUID().toString().substring(0, 5); // Случайное название
        String desc = "Очень хороший велосипед, почти новый.";
        String price = "5000";

        newAdPage.fillForm(currentAdTitle, desc, price);
    }

    @Тогда("Объявление успешно создано")
    public void checkAdCreated() {
        // Так как мы попадаем на главную, идем в профиль проверять
        mainPage.clickProfileIcon();
        profilePage.checkMyAdsSectionLoaded();
        profilePage.checkAdExists(currentAdTitle);
    }
    @И("Перейти в профиль пользователя")
    public void openProfile() {
        mainPage.clickProfileIcon();
        profilePage.checkMyAdsSectionLoaded();
    }

    @Когда("Нажать кнопку редактирования объявления")
    public void clickEditAd() {
        profilePage.editAdByTitle(currentAdTitle);
    }
    @Тогда("Открывается форма редактирования объявления")
    public void checkEditFormOpened() {
        newAdPage.checkTitleValue(currentAdTitle);
    }

    @Когда("Нажать кнопку удаления объявления")
    public void clickDeleteAd() {
        newAdPage.clickDelete();
    }

    @Тогда("Объявление удалено")
    public void checkAdDeleted() {
        // Проверка, что в профиле объявления больше нет
        profilePage.checkAdNotExists(currentAdTitle);
    }

}


