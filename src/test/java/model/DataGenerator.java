package model;
import java.util.Random;

public class DataGenerator {

    public static User generateRandomUser() {
        long timestamp = System.currentTimeMillis();
        String email = "user_" + timestamp + "@test.com";
        String password = "Pass_" + new Random().nextInt(10000) + "!";

        return new User(email, password);
    }
}
