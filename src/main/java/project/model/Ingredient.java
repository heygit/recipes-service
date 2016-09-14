package project.model;

/**
 * Created by Green-L on 13.09.2016.
 */
public class Ingredient {

    private String name;
    private String quantity;
    private String measurement;

    public Ingredient(String name, String quantity, String measurement) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", measurement='" + measurement + '\'' +
                '}';
    }
}
