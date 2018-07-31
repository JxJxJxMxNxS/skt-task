package nearsoft.skytouch.task.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Product {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String description;
    @NotEmpty
    @NotNull
    private Long price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
