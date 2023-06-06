package org.example;

public class Person {

    Integer id;
    String first_name;
    String last_name;
    Integer age;

    public Person(Integer id, String first_name, String last_name, Integer age) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "id= " + id  +
                " first_name= " + first_name +
                " last_name= " + last_name +
                " age= " + age + '\n';
    }
}
