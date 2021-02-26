package pl.pacwa.cardb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pacwa.cardb.model.Car;
import pl.pacwa.cardb.model.Color;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class CarDaoImp implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Car> getOneCar(long id) {
        String sgl = "SELECT * FROM cars WHERE id = ?";
        try {
            Car car = jdbcTemplate.queryForObject(sgl,
                    (a, rowNum) -> new Car(a.getLong("id"),
                            a.getString("mark"),
                            a.getString("model"),
                            Color.valueOf(a.getString("color")),
                            LocalDate.parse(a.getString("production_date"))), id);
            return Optional.ofNullable(car);
        } catch (IncorrectResultSizeDataAccessException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Car> findAllCars() {
        String sgl = "SELECT * FROM cars";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sgl);
        return dbMapper(dbOutput);
    }

    @Override
    public List<Car> findCarsByColor(Color color) {
        String sgl = "SELECT * FROM cars WHERE color = ?";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sgl, color.toString());
        return dbMapper(dbOutput);
    }

    @Override
    public List<Car> findCarsByDate(LocalDate fromDate, LocalDate toDate) {
        String sql = "SELECT * FROM cars \n" +
                "WHERE (production_date > ? AND production_date < ?)";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sql, fromDate.toString(), toDate.toString());
        return dbMapper(dbOutput);
    }

    @Override
    public int saveCar(String mark, String model, Color color, LocalDate date) {
        List<Car> allCars = findAllCars();
        Long maxId = allCars
                .stream()
                .max(Comparator.comparing(Car::getId))
                .get()
                .getId();
        Car newCar = new Car(maxId + 1, mark, model, color, date);
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, newCar.getId(), newCar.getMark(), newCar.getModel(),
                newCar.getColor().toString(), newCar.getProductionDate());
    }

    @Override
    public int updateCar(Car newCar) {
        String sql = "UPDATE cars SET cars.mark = ?, cars.model = ?, cars.color = ?, cars.production_date = ?" +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, newCar.getMark(), newCar.getModel(),
                newCar.getColor().toString(),
                Date.valueOf(newCar.getProductionDate()), newCar.getId());
    }

    @Override
    public int deleteCar(long id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private List<Car> dbMapper(List<Map<String, Object>> dbOutput) {
        List<Car> carList = new ArrayList<>();
        dbOutput.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                Color.valueOf(String.valueOf(element.get("color"))),
                LocalDate.parse(String.valueOf(element.get("production_date")))
        )));
        return carList;
    }


}
