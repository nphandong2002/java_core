package org.example.Entity;

public class Product {
    public int id;
    public String nameProduct;
    public String nameManufacturers;
    public String type;
    public long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameManufacturers() {
        return nameManufacturers;
    }

    public void setNameManufacturers(String nameManufacturers) {
        this.nameManufacturers = nameManufacturers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
