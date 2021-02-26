package pl.pacwa.cardb.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

public class Car {

    @NotNull(message = "ID cannot be null.")
    @Min(value = 1, message = "ID must be greater than 1.")
    private long id;

    @NotNull(message = "Mark cannot be null.")
    @NotBlank(message = "Mark cannot be blank.")
    @Size(min = 1, message = "Mark must have at least 1 character length.")
    private String mark;

    @NotNull(message = "Model cannot be null.")
    @NotBlank(message = "Model cannot be blank.")
    @Size(min = 1, message = "Model must have at least 1 character length.")
    private String model;


    private Color color;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;

    public Car() {
    }

    public Car(@NotNull(message = "ID cannot be null.") @Min(value = 1, message = "ID must be greater than 1.") long id, @NotNull(message = "Mark cannot be null.") @NotBlank(message = "Mark cannot be blank.") @Size(min = 1, message = "Mark must have at least 1 character length.") String mark, @NotNull(message = "Model cannot be null.") @NotBlank(message = "Model cannot be blank.") @Size(min = 1, message = "Model must have at least 1 character length.") String model, Color color, LocalDate productionDate) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionDate = productionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", productionDate=" + productionDate +
                '}';
    }
}
