package pl.pacwa.cardb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pacwa.cardb.model.Car;
import pl.pacwa.cardb.model.Color;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getjdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        String dropTable = "DROP TABLE IF EXISTS cars";
        getjdbcTemplate().update(dropTable);

        String createTable = "CREATE TABLE cars (id int PRIMARY KEY AUTO_INCREMENT, " +
                "mark varchar (100) , model varchar (100) , color varchar (50) , " +
                "production_date varchar (50))";
        getjdbcTemplate().update(createTable);

        String sql = "INSERT INTO cars (id, mark, model, color, production_date) VALUES (?, ?, ?, ?, ?)";
        initService().forEach(car ->
                getjdbcTemplate().update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor().toString(),
                        Date.valueOf(car.getProductionDate())));

    }

    private List<Car> initService() {
        List<Car> initDbList = new ArrayList<>();
        initDbList.add(new Car(1L, "Fiat", "126p", Color.GREEN, LocalDate.of(1978, 01, 30)));
        initDbList.add(new Car(2L, "Opel", "Corsa", Color.YELLOW, LocalDate.of(1999, 01, 14)));
        initDbList.add(new Car(3L, "Ford", "C-max", Color.BLUE, LocalDate.of(2009, 02, 25)));
        initDbList.add(new Car(4L, "Skoda", "Fabia", Color.RED, LocalDate.of(1995, 07, 13)));
        initDbList.add(new Car(5L, "Jeep", "Compas", Color.BROWN, LocalDate.of(2019, 10, 05)));
        initDbList.add(new Car(6L, "BMW", "X5", Color.BLACK, LocalDate.of(2020, 05, 18)));
        initDbList.add(new Car(7L, "Honda", "Jazz", Color.PURPLE, LocalDate.of(2001, 06, 27)));
        initDbList.add(new Car(8L, "Lamborghini", "Urus", Color.YELLOW, LocalDate.of(2020, 04, 13)));
        initDbList.add(new Car(9L, "Kia", "Stringer", Color.GREY, LocalDate.of(2020, 12, 29)));
        initDbList.add(new Car(10L, "Ford", "Kuga", Color.GREEN, LocalDate.of(2008, 10, 22)));
        initDbList.add(new Car(11L, "Honda", "Civic", Color.RED, LocalDate.of(2011, 05, 07)));

        return initDbList;
    }
}
