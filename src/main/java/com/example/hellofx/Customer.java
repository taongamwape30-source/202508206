package com.example.hellofx;

import javafx.beans.property.*;

public class Customer {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty province = new SimpleStringProperty();

    public Customer(String name, String province) {
        this.name.set(name);
        this.province.set(province);
    }
    public StringProperty nameProperty() { return name; }
    public StringProperty provinceProperty() { return province; }
    public String getName() { return name.get(); }
    public String getProvince() { return province.get(); }
}