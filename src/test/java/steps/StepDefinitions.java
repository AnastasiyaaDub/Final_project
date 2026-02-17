package steps;

import io.cucumber.java.ru.*;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import model.User;
import model.DataGenerator;

public class StepDefinitions {

    MainPage mainPage = new MainPage();
    RegistrationPage registrationPage = new RegistrationPage();
    LoginPage loginPage = new LoginPage();

    // Храним данные текущего пользователя, чтобы использовать их в других шагах
    private User currentUser;

    @Дано("Открыть главную страницу")
    public void openMainPage() {
        mainPage.openMainPage();
    }

    @Когда("Нажать кнопку {string}")
    public void clickButton(String buttonName) {
        if (buttonName.equals("Вход и регистрация")) {
            mainPage.clickLoginAndRegisterButton();
        }else if (buttonName.equals("Нет аккаунта")) {
                loginPage.clickNoAccount();
        } else if (buttonName.equals("Создать аккаунт")) {
            registrationPage.clickCreateAccount();
        }
    }

    @И("Заполнить форму регистрации валидными данными")
    public void fillRegistrationForm() {
        currentUser = DataGenerator.generateRandomUser();

        registrationPage.fillForm(currentUser.email(), currentUser.password());
    }
    //@И("Нажать кнопку 'Создать аккаунт'")


    @Тогда("Имя пользователя отображается в шапке профиля")
    public void checkUserNameInHeader() {
        mainPage.checkUserLoggedIn();
    }

    // Геттер для пользователя (понадобится для шага логина)
    public User getCurrentUser() {
        return currentUser;
    }

}


