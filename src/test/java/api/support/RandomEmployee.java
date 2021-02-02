package api.support;

import java.util.Random;
import java.util.UUID;

public class RandomEmployee {
    String name;
    int salary;
    int age;
    String profileImageUrl;
    private int id;

    public RandomEmployee() {
        this("Name " + UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", ""),
                1 + new Random().nextInt(99999),
                (1 + new Random().nextInt(9)) * 10 + new Random().nextInt(90),
                "");
    }

    public RandomEmployee(String name, int salary, int age, String profileImageUrl) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RandomEmployee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
