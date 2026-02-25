import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresDBClient {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdata",
                "jdatastudent", "123456");
        Statement statement = connection.createStatement();

        // statement.execute("create table cars(id bigserial primary key, brand varchar(256) not null);");

        // statement.executeUpdate("insert into cars (brand) values ('Skoda');" );
        // statement.executeUpdate("insert into cars (brand) values ('VW'), ('Audi'), ('Porshe');" );

        // statement.executeUpdate("delete from cars where id = 2" );

        ResultSet resultSet = statement.executeQuery("select * from cars");

        List<Car> cars = new ArrayList<>();
        while(resultSet.next()) {
            long id = resultSet.getLong("id");
            String brand = resultSet.getString("brand");

            // System.out.printf("Машина с id=%s марки %s", id, brand);
            // System.out.println();
            cars.add(new Car(id, brand));
        }

        cars.forEach(System.out::println);

        statement.close();
        connection.close();
    }
}

class Car {
    private long id;
    private String brand;

    public Car(long id, String brand) {
        this.id = id;
        this.brand = brand;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                '}';
    }
}