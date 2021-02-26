package pl.pacwa.cardb.dao;

import pl.pacwa.cardb.model.Car;
import pl.pacwa.cardb.model.Color;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarDao {


    Optional<Car> getOneCar(long id);

    List<Car> findAllCars();

    List<Car> findCarsByColor(Color color);

    List<Car>  findCarsByDate(LocalDate fromDate, LocalDate toDate);

    int saveCar(String mark, String model, Color color, LocalDate date);

    int updateCar(Car newCar);

    int deleteCar (long id);

}
