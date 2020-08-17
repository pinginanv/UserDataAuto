import java.util.ArrayList;
import java.util.Random;

public class RandomUser {

    //for test purpose generation of existing user to make it failing
    public static User generateDuplicated() {
        return new User("blelosyc", "qbi", "526702604", "380915341624", "blelosyc.qbi@mail.ua", "MyPass12345", gender(), position());
    }

    public static User generateUser() {

        String firstName = firstName();
        String lastName = lastName();
        String emaillastName = lastName;
        if (emaillastName.length() > 5) {
            emaillastName = emaillastName.substring(0, 4);
        }
        String email = firstName + "." + emaillastName + "@mail.ua";
        System.out.println("Generated email: " + email);


        return new User(firstName, lastName, workPhone(), mobilePhone(), email, "MyPass12345", gender(), position());
    }

    public static String firstName() {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        String firstName = "";
        Random r = new Random();


        int randomlength = r.ints(1, 2, 11).findFirst().getAsInt();


        for (int i = 0; i < randomlength; i++) {
            int randomLetter = r.ints(1, 0, alphabet.length).findFirst().getAsInt();
            firstName = firstName + alphabet[randomLetter];
        }
        System.out.println("Generated name: " + firstName);
        return firstName;
    }

    public static String lastName() {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        String lastName = "";
        Random r = new Random();


        int randomlength = r.ints(1, 2, 11).findFirst().getAsInt();


        for (int i = 0; i < randomlength; i++) {
            int randomLetter = r.ints(1, 0, alphabet.length).findFirst().getAsInt();
            lastName = lastName + alphabet[randomLetter];
        }
        System.out.println("Generated lastName: " + lastName);
        return lastName;
    }

    public static String workPhone() {
        char[] numbers = "0123456789".toCharArray();

        String workPhone = "";
        Random r = new Random();


        int randomlength = r.ints(1, 5, 16).findFirst().getAsInt();


        for (int i = 0; i < randomlength; i++) {
            int randomLetter = r.ints(1, 0, numbers.length).findFirst().getAsInt();
            workPhone = workPhone + numbers[randomLetter];
        }

        System.out.println("Generated workPhone: " + workPhone);
        return workPhone;
    }

    public static String mobilePhone() {

        ArrayList<String> codes = new ArrayList<>();
        codes.add("50");
        codes.add("63");
        codes.add("66");
        codes.add("67");
        codes.add("68");
        codes.add("91");
        codes.add("92");
        codes.add("93");
        codes.add("97");
        codes.add("96");


        char[] numbers = "0123456789".toCharArray();


        String mobilePhone = "";
        Random r = new Random();
        int randomCode = r.ints(1, 0, codes.size()).findFirst().getAsInt();

        for (int i = 0; i < 7; i++) {
            int randomNumber = r.ints(1, 0, numbers.length).findFirst().getAsInt();
            mobilePhone = mobilePhone + numbers[randomNumber];
        }

        mobilePhone = "380" + codes.get(randomCode) + mobilePhone;

        System.out.println("Generated mobilePhone: " + mobilePhone);
        return mobilePhone;
    }

    public static Gender gender() {
        Random r = new Random();
        int randomCode = r.ints(1, 0, Gender.values().length).findFirst().getAsInt();
        return Gender.values()[randomCode];
    }

    public static Position position() {
        Random r = new Random();
        int randomCode = r.ints(1, 0, Position.values().length).findFirst().getAsInt();
        return Position.values()[randomCode];
    }

}
