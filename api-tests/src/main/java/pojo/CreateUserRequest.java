package pojo;

public class CreateUserRequest {
    private String firstName;
    private String secondName;
    private Integer age;
    private String sex;
    private Double money;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String firstName, String secondName, int age, String sex, double money) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
