package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class NewAdPage {

    /* Поля с дропдаунами показали себя как нестабильные и сложные. Поскольку в тесте не проверяются сами поля, а только общее создание
    эти поля при создании останутся "по умолчанию".
     */

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
    private SelenideElement descriptionTextarea = $("textarea[name='description']");

    // Поле "Стоимость"
    private SelenideElement priceInput = $(byName("price"));

    // Кнопка "Опубликовать"
    private SelenideElement publishButton = $(byXpath(".//button[text()='Опубликовать']"));

    // Локатор кнопки удаления (требует уточнения после фикса блока редактирования)
    private SelenideElement deleteButton = $(byText("Удалить"));


    public NewAdPage setName(String name) {
        nameInput.setValue(name);
        return this;
    }

    // Метод выбора категории.
    public NewAdPage selectCategory(String category) {
        categoryInput.click();
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

    public void fillForm(String name, String description, String price) {
        setName(name);
        // Скроллим страницу вниз на 500 пикселей (или до нужного элемента)
        executeJavaScript("window.scrollBy(0, 500)");

                setDescription(description)
                .setPrice(price);


    }
    public void checkTitleValue(String expectedTitle) {
        nameInput.shouldHave(value(expectedTitle));
    }

    public void clickDelete() {
        deleteButton.shouldBe(visible).click();
    }

}
