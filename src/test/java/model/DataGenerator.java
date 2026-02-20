package model;
import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    private static final Faker FAKER = new Faker(new Locale("ru"));


    public static User generateRandomUser() {
        String email = FAKER.internet().emailAddress();
        String password = FAKER.internet().password(8, 12, true, true);

        return new User(email, password);
    }

    public static String generateAdTitle() {
        return FAKER.commerce().productName();
    }
    public static String generateAdDescription() {
        return FAKER.lorem().sentence();
    }
    public static String generateAdPrice() {
        return String.valueOf(FAKER.number().numberBetween(100, 10000));
    }
}

