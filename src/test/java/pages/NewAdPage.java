package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class NewAdPage {

    // Поле "Название"
    private SelenideElement nameInput = $(byName("name"));

    // Поле "Категория"
    private SelenideElement categoryInput = $(byName("category"));

    // Радиокнопки "Состояние"
    private SelenideElement conditionNew = $(byXpath(".//label[text()='Новый']"));
    private SelenideElement conditionUsed = $(byXpath(".//label[text()='Б/У']"));

    // Поле "Город"
    private SelenideElement cityInput = $(byName("city"));

    // Поле "Описание"
    private SelenideElement descriptionTextarea = $(byName("description"));

    // Поле "Стоимость"
    private SelenideElement priceInput = $(byName("price"));

    // Кнопка "Опубликовать"
    private SelenideElement publishButton = $(byXpath(".//button[text()='Опубликовать']"));

    public NewAdPage setName(String name) {
        nameInput.setValue(name);
        return this;
    }

    // Метод выбора категории.
    public NewAdPage selectCategory(String category) {
        categoryInput.click(); // Открываем дропдаун
        $(byText(category)).shouldBe(visible).click(); // Выбираем нужный пункт (Авто, Книги и т.д.)
        return this;
    }

    // Метод выбора состояния
    public NewAdPage selectCondition(boolean isNew) {
        if (isNew) {
            conditionNew.click();
        } else {
            conditionUsed.click();
        }
        return this;
    }

    // Метод выбора города
    public NewAdPage selectCity(String city) {
        cityInput.click();
        $(byText(city)).shouldBe(visible).click();
        return this;
    }

    public NewAdPage setDescription(String description) {
        descriptionTextarea.setValue(description);
        return this;
    }

    public NewAdPage setPrice(String price) {
        priceInput.setValue(price);
        return this;
    }

    public void clickPublish() {
        publishButton.shouldBe(visible).click();
    }

}
