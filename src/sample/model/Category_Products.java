package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category_Products {
    private IntegerProperty idProperty;
    private StringProperty nameProductProperty;
    private StringProperty nameCategoryProperty;
    private IntegerProperty categoryIdProperty;
    private IntegerProperty productIdProperty;
    public Category_Products() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameCategoryProperty = new SimpleStringProperty();
        this.nameProductProperty = new SimpleStringProperty();
        this.categoryIdProperty = new SimpleIntegerProperty();
        this.productIdProperty = new SimpleIntegerProperty();
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProductProperty(String nameProductProperty) {
        this.nameProductProperty.set(nameProductProperty);
    }

    public void setNameCategoryProperty(String nameCategoryProperty) {
        this.nameCategoryProperty.set(nameCategoryProperty);
    }

    public void setCategoryIdProperty(int categoryIdProperty) {
        this.categoryIdProperty.set(categoryIdProperty);
    }

    public void setProductIdProperty(int productIdProperty) {
        this.productIdProperty.set(productIdProperty);
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public String getNameProductProperty() {
        return nameProductProperty.get();
    }

    public StringProperty nameProductPropertyProperty() {
        return nameProductProperty;
    }

    public String getNameCategoryProperty() {
        return nameCategoryProperty.get();
    }

    public StringProperty nameCategoryPropertyProperty() {
        return nameCategoryProperty;
    }

    public int getCategoryIdProperty() {
        return categoryIdProperty.get();
    }

    public IntegerProperty categoryIdPropertyProperty() {
        return categoryIdProperty;
    }

    public int getProductIdProperty() {
        return productIdProperty.get();
    }

    public IntegerProperty productIdPropertyProperty() {
        return productIdProperty;
    }
}
