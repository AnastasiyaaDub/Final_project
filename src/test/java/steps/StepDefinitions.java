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

    @When("Нажать кнопку \"Вход и регистрация\"")
    public void clickLoginAndRegister() {
        mainPage.clickLoginAndRegisterButton();
    }

    @When("Нажать кнопку \"Нет аккаунта\"")
    public void clickNoAccount() {
        loginPage.clickNoAccount();
    }

    @When("Нажать кнопку \"Создать аккаунт\"")
    public void clickCreateAccount() {
        registrationPage.clickCreateAccount();
    }

    @When("Нажать кнопку \"Войти\"")
    public void clickLogin() {
        loginPage.clickLoginButton();
    }

    @When("Нажать кнопку \"Разместить объявление\"")
    public void clickPlaceAd() {
        mainPage.clickPlaceAdButton();
    }

    @When("Нажать кнопку \"Опубликовать\"")
    public void clickPublish() {
        newAdPage.clickPublish();
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
        registrationPage.checkErrorMessageText(expectedError);
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


