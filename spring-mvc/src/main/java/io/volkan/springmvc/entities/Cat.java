package io.volkan.springmvc.entities;

public class Cat {

    private String name;

    private String breed;

    private int age;

    private String tip = "This domain object provided by ContentNegotiatingViewResolver";

    public Cat(String name, String breed, int age) {
        super();
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}

