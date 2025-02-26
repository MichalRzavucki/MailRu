package pojo;

public class UsersRequest {
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer age;
    private String sex;
    private Double money;

    public UsersRequest() {
    }

    public UsersRequest(Integer id, String firstName, String secondName, Integer age, String sex, Double money) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
