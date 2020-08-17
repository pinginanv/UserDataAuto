public class User {

    private String firstName;
    private String lastName;
    private String workPhone;
    private String MobilePhone;
    private String email;
    private String password;
    private Gender gender;
    private Position position;

    public User(String firstName, String lastName, String workPhone, String mobilePhone, String email, String password, Gender gender, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPhone = workPhone;
        this.MobilePhone = mobilePhone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
