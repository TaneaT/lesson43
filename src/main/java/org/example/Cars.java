package org.example;

public class Cars {

    Integer id;
    String name;


    public Cars(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id= " + id  +
                " name= " + name + '\n';
    }
}
