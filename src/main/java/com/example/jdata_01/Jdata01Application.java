package com.example.jdata_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Jdata01Application implements CommandLineRunner {
	@Autowired
	DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Jdata01Application.class, args);
	}

	@Override
	public void run(String ...args) throws SQLException {
		List<Car> cars = new ArrayList<>();
		//------------------------------------------------------------
		// Работа с БД PostgreSQL
		//------------------------------------------------------------
		// Версия с DataSource
		/*
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from cars");
		while(resultSet.next()) {
			long id = resultSet.getLong("id");
			String brand = resultSet.getString("brand");
			cars.add(new Car(id, brand));
		}
		cars.forEach(System.out::println);

		statement.close();
		connection.close();
		*/

		//------------------------------------------------------------
		// Версия с JdbcTemplate: простой запрос без параметров
		/*
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from cars");
		while(sqlRowSet.next()) {
			long id = sqlRowSet.getLong("id");
			String brand = sqlRowSet.getString("brand");
			cars.add(new Car(id, brand));
		}
		cars.forEach(System.out::println);
        */
		//------------------------------------------------------------
		// Версия с JdbcTemplate: сложный запрос - параметры передаются через ? для того, чтобы их автоматом экранировать
		// Защищаемся от атаки типа:
		// "select * from cars where brand = " + "'Audi'; drop database jdata;"
		/*
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from cars where brand = ?", "Audi");
		while(sqlRowSet.next()) {
			long id = sqlRowSet.getLong("id");
			String brand = sqlRowSet.getString("brand");
			cars.add(new Car(id, brand));
		}
		cars.forEach(System.out::println);
		*/
		//------------------------------------------------------------
		// Версия с JdbcTemplate: сложный запрос плюс пример с получением объекта сразу
		/*
		Car car = jdbcTemplate.queryForObject("select * from cars where brand = ?",
				(ResultSet rs, int rowNum) -> new Car(rs.getLong("id"), rs.getString("brand")),
				"VW");
		System.out.println(car);
		*/
		//------------------------------------------------------------
		// Версия с JdbcTemplate: И еще одна версия - пример с получением списка объектов
		/*
		List<Car> c1 = jdbcTemplate.query("select * from cars",
				(rs, rowNum) -> new Car(rs.getLong("id"), rs.getString("brand"))
		);
		c1.forEach(System.out::println);
		*/
		//------------------------------------------------------------
		// Версия с namedParametersJdbcTemplate
		Car car2 = namedParameterJdbcTemplate.queryForObject("select * from cars where brand = :brand",
				Map.of("brand","VW"),
				(ResultSet rs, int rowNum) -> new Car(rs.getLong("id"), rs.getString("brand"))
			);
		System.out.println(car2);
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