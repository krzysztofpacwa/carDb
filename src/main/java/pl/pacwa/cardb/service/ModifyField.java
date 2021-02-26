package pl.pacwa.cardb.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModifyField {

    @NotNull(message = "ID cannot be null.")
    @Min(value = 1, message = "ID must be greater than 1.")
    private Long id;

    @NotNull(message = "Piorytet cannot be null.")
    @NotBlank(message = "Piorytet cannot be blank.")
    private String piorytet;

    @NotNull(message = "Value cannot be null.")
    @NotBlank(message = "Value cannot be blank.")
    private String value;

    public ModifyField() {
    }

    public ModifyField(@NotNull(message = "ID cannot be null.") @Min(value = 1, message = "ID must be greater than 1.")
                               Long id, @NotNull(message = "Piorytet cannot be null.")
                                @NotBlank(message = "Piorytet cannot be blank.")
                                String piorytet, @NotNull(message = "Value cannot be null.")
                            @NotBlank(message = "Value cannot be blank.") String value) {
        this.id = id;
        this.piorytet = piorytet;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPiorytet() {
        return piorytet;
    }

    public void setPiorytet(String piorytet) {
        this.piorytet = piorytet;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ModifyField{" +
                "id=" + id +
                ", piorytet='" + piorytet + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
