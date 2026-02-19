package steps;

import api.AuthApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.*;
import pages.*;
import model.User;
import model.DataGenerator;
import java.util.UUID;

public class StepDefinitions {

    MainPage mainPage = new MainPage();
    RegistrationPage registrationPage = new RegistrationPage();
    LoginPage loginPage = new LoginPage();
    NewAdPage newAdPage = new NewAdPage();
    ProfilePage profilePage = new ProfilePage();


    private User currentUser;
    private String currentAdTitle;

    @Given("Открыть главную страницу")
    public void openMainPage() {
        mainPage.openMainPage();
    }
    @Given("Создать объявление")
    public void createAdViaUI() {

        mainPage.clickPlaceAdButton();

        // Генерируем данные
        currentAdTitle = "Продам велик " + UUID.randomUUID().toString().substring(0, 5);
        newAdPage.fillForm(currentAdTitle, "Описание для редактирования", "1000");

        newAdPage.clickPublish();

        mainPage.clickProfileIcon();
        profilePage.checkMyAdsSectionLoaded();
    }

    @When("Нажать кнопку {string}")
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

    @And("Заполнить форму регистрации валидными данными")
    public void fillRegistrationForm() {
        currentUser = DataGenerator.generateRandomUser();

        registrationPage.fillForm(currentUser.email(), currentUser.password());
    }


    @Then("Имя пользователя отображается в шапке профиля")
    public void checkUserNameInHeader() {
        mainPage.checkUserLoggedIn();
    }

    @And("Пользователь уже зарегистрирован в системе")
    public void userIsAlreadyRegistered() {
        // Генерируем данные
        currentUser = DataGenerator.generateRandomUser();
        // Регистрируем через API, чтобы занять email
        AuthApi.register(currentUser.email(), currentUser.password());
    }

    @And("Заполнить форму регистрации данными уже существующего пользователя")
    public void fillFormWithExistingUser() {
        // Заполняем форму теми же данными, что мы зарегистрировали через API
        registrationPage.fillForm(currentUser.email(), currentUser.password());
    }

    @Then("Отображается сообщение об ошибке {string}")
    public void checkErrorMessage(String expectedError) {
        registrationPage.checkErrorMessageVisible();
    }

    @And("Заполнить форму авторизации данными созданного пользователя")
    public void fillLoginFormWithExistingUser() {
        // Используем данные из переменной currentUser, которую создали в шаге "Пользователь уже зарегистрирован"
        loginPage.login(currentUser.email(), currentUser.password());
    }

    @And("Заполнить форму объявления")
    public void fillAdForm() {
        currentAdTitle = "Продам велик " + UUID.randomUUID().toString().substring(0, 5); // Случайное название
        String desc = "Очень хороший велосипед, почти новый.";
        String price = "5000";

        newAdPage.fillForm(currentAdTitle, desc, price);
    }

    @Then("Объявление успешно создано")
    public void checkAdCreated() {
        // Так как мы попадаем на главную, идем в профиль проверять
        mainPage.clickProfileIcon();
        profilePage.checkMyAdsSectionLoaded();
        profilePage.checkAdExists(currentAdTitle);
    }
    @And("Перейти в профиль пользователя")
    public void openProfile() {
        mainPage.clickProfileIcon();
        profilePage.checkMyAdsSectionLoaded();
    }

    @When("Нажать кнопку редактирования объявления")
    public void clickEditAd() {
        profilePage.editAdByTitle(currentAdTitle);
    }
    @Then("Открывается форма редактирования объявления")
    public void checkEditFormOpened() {
        newAdPage.checkTitleValue(currentAdTitle);
    }

    @When("Нажать кнопку удаления объявления")
    public void clickDeleteAd() {
        newAdPage.clickDelete();
    }

    @Then("Объявление удалено")
    public void checkAdDeleted() {
        // Проверка, что в профиле объявления больше нет
        profilePage.checkAdNotExists(currentAdTitle);
    }

}


