package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    // Заголовок раздела "Мои объявления"
    private SelenideElement myAdsHeader = $(byText("Мои объявления"));

    // Иконка редактирования (карандаш)
    private SelenideElement editAdIcon = $(".editIcon");

    // Проверка, что раздел "Мои объявления" загрузился
    public ProfilePage checkMyAdsSectionLoaded() {
        myAdsHeader.shouldBe(visible);
        return this;
    }

    // Клик по иконке редактирования
    public void clickEditAdIcon() {
        editAdIcon.shouldBe(visible).click();
    }

    public void editAdByTitle(String title) {
        //метод поиска объявления по названию
        $(byText(title))
                .closest(".card")
                .find(".editButton")
                .shouldBe(visible)
                .click();
    }
}
