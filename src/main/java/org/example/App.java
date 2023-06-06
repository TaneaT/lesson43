package org.example;

import java.sql.*;
import java.util.*;


public class App 
{
    public static void main( String[] args )
    {
        {

            String url = ""; // deleted my url

            try (Connection connection = DriverManager.getConnection(url)) {



//                addPerson(connection, "Vladimir", "Popov", 23);
//                addPerson(connection, "Eugen", "Doga", 53);
//                addPerson(connection, "Ion", "Rotaru", 16);

//                System.out.println(getPersonById(connection, 2));

//                PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE id IN(4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);");
//                statement.executeUpdate();
//                System.out.println(getAllPersons(connection));

//                createTableCars(connection);
//                addCar(connection,"BMW",1);
//                addCar(connection,"Toyota",2);
//                addCar(connection,"Mercedes",3);
                System.out.println(getCarById(connection,2));
                System.out.println(getAllCars(connection));
//                PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE id IN(4,5,6);");
//                statement.executeUpdate();


//                System.out.println(join(connection));

                List<String[]> joinedData = join(connection);
                for (String[] data : joinedData) {
                    System.out.println(data[0] + " - " + data[1]);
                }



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }



            }

    static void createTablePerson(Connection connection) throws SQLException{
        PreparedStatement createTable = connection.prepareStatement("CREATE TABLE person(\n" +
                "   id INT AUTO_INCREMENT,\n" +
                "   first_name VARCHAR(40),\n" +
                "   last_name VARCHAR(40),\n" +
                "   age INT,\n" +
                "   PRIMARY KEY(id)\n" +
                "); ");
        createTable.execute();
        createTable.close();
    }

    static void createTableCars(Connection connection) throws SQLException{
        PreparedStatement createTableCars = connection.prepareStatement("CREATE TABLE cars(\n" +
                "   id INT AUTO_INCREMENT,\n" +
                "   name VARCHAR(40),\n" +
                "   person_id INT REFERENCES person(id),\n" +
                "   PRIMARY KEY(id)\n" +
                "); ");

        createTableCars.execute();
        createTableCars.close();
    }

    static void addPerson(Connection connection,String first_name,String last_name,int age) throws SQLException{
        PreparedStatement addPerson = connection.prepareStatement("INSERT INTO person(first_name,last_name,age) VALUES(?,?,?);");

        addPerson.setString(1,first_name);
        addPerson.setString(2,last_name);
        addPerson.setInt(3,age);

        addPerson.executeUpdate();
        addPerson.close();
    }


    static void addCar(Connection connection,String name, int person_id) throws SQLException {
        PreparedStatement addCar = connection.prepareStatement("INSERT INTO cars(name,person_id) VALUES (?,?)");

        addCar.setString(1,name);
        addCar.setInt(2,person_id);

        addCar.executeUpdate();
        addCar.close();


    }
    static Person getPersonById(Connection connection, int person_id) throws SQLException {
        PreparedStatement getPerson = connection.prepareStatement("SELECT * FROM person WHERE id = ?");

        getPerson.setInt(1,person_id);

        ResultSet result = getPerson.executeQuery();

        result.next();

        Integer id = result.getInt(1);
        String first_name = result.getString(2);
        String last_name = result.getString(3);
        Integer age = result.getInt(4);

        Person person = new Person(id,first_name,last_name,age);

        return person;
    }

    static Cars getCarById (Connection connection, int car_id) throws SQLException{
        PreparedStatement getCarById = connection.prepareStatement("SELECT * FROM cars WHERE id = ?;");

        getCarById.setInt(1,car_id);

        ResultSet resultSet = getCarById.executeQuery();
        resultSet.next();

        Integer id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        Integer person_id = resultSet.getInt(3);

        Cars cars = new Cars(id,name);

        return cars;
    }

    static List<Person> getAllPersons(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person");

        ResultSet resultSet = preparedStatement.executeQuery();


        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            Integer age = resultSet.getInt(4);

            personList.add(new Person(id, firstName, lastName, age));
        }

        preparedStatement.close();

        return personList;
    }

    static List<Cars> getAllCars (Connection connection) throws SQLException{
        PreparedStatement getAllCars = connection.prepareStatement("SELECT * FROM cars ");

        ResultSet resultSet = getAllCars.executeQuery();

        List<Cars> carsList = new ArrayList<>();

        while (resultSet.next()){
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);

            carsList.add(new Cars(id,name));
        }
        getAllCars.close();
        return carsList;
    }

    static List<String[]> join (Connection connection) throws SQLException{
        PreparedStatement join = connection.prepareStatement("SELECT person.first_name, cars.name FROM person JOIN cars ON person.id = cars.person_id;");

       ResultSet resultSet = join.executeQuery();
        List<String[]> joinedData = new ArrayList<>();

        while (resultSet.next()) {
            String first_name = resultSet.getString(1);
            String name = resultSet.getString(2);
            joinedData.add(new String[] { first_name, name });
        }

       join.close();

return  joinedData;
    }


}
