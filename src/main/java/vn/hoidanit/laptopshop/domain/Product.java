package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class that maps to the "products" table in the database.
 * 
 * This class provides the following functionalities:
 * 1. Maps Java objects to rows in the "products" table for CRUD operations.
 * 2. Enables data validation for product-related fields using annotations.
 * 3. Facilitates interactions with the database for managing product records.
 */
@Entity
@Table(name = "products")
public class Product {

    // Primary key for the Product entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    // Name of the product (required, cannot be empty).
    @NotNull
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name; 

    // Price of the product (must be greater than 0).
    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Price phải lớn hơn 0")
    private double price; 

    // Image URL of the product.
    private String image; 

    // Detailed description of the product (required, cannot be empty, max length 512).
    @Lob
    @Column(name = "detail_desc", length = 512)
    @NotNull
    @NotEmpty(message = "detailDesc không được để trống")
    private String detailDesc; 

    // Short description of the product (required, cannot be empty).
    @NotNull
    @NotEmpty(message = "shortDesc không được để trống")
    private String shortDesc; 

    // Available quantity of the product (must be at least 1).
    @NotNull
    @Min(value = 1, message = "Số lượng cần lớn hơn hoặc bằng 1")
    private long quantity; 

    // Number of products sold.
    private long sold; 

    // Factory/manufacturer of the product.
    private String factory; 

    // Target audience for the product.
    private String target; 

    /*
     * Gets the ID of the product.
     * @return the ID of the product.
     */
    public long getId() {
        return id;
    }

    /*
     * Sets the ID of the product.
     * @param id the ID to be set for the product.
     */
    public void setId(long id) {
        this.id = id;
    }

    /*
     * Gets the name of the product.
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /*
     * Sets the name of the product.
     * @param name the name to be set for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Gets the price of the product.
     * @return the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /*
     * Sets the price of the product.
     * @param price the price to be set for the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * Gets the image URL of the product.
     * @return the image URL of the product.
     */
    public String getImage() {
        return image;
    }

    /*
     * Sets the image URL of the product.
     * @param image the image URL to be set for the product.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /*
     * Gets the detailed description of the product.
     * @return the detailed description of the product.
     */
    public String getDetailDesc() {
        return detailDesc;
    }

    /*
     * Sets the detailed description of the product.
     * @param detailDesc the detailed description to be set for the product.
     */
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    /*
     * Gets the short description of the product.
     * @return the short description of the product.
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /*
     * Sets the short description of the product.
     * @param shortDesc the short description to be set for the product.
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /*
     * Gets the available quantity of the product.
     * @return the available quantity of the product.
     */
    public long getQuantity() {
        return quantity;
    }

    /*
     * Sets the available quantity of the product.
     * @param quantity the quantity to be set for the product.
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /*
     * Gets the number of products sold.
     * @return the number of products sold.
     */
    public long getSold() {
        return sold;
    }

    /*
     * Sets the number of products sold.
     * @param sold the number of sold products to be set.
     */
    public void setSold(long sold) {
        this.sold = sold;
    }

    /*
     * Gets the factory/manufacturer of the product.
     * @return the factory/manufacturer of the product.
     */
    public String getFactory() {
        return factory;
    }

    /*
     * Sets the factory/manufacturer of the product.
     * @param factory the factory to be set for the product.
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /*
     * Gets the target audience for the product.
     * @return the target audience for the product.
     */
    public String getTarget() {
        return target;
    }

    /*
     * Sets the target audience for the product.
     * @param target the target audience to be set for the product.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /*
     * Returns a string representation of the Product object.
     * @return a string representation of the Product object.
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", detailDesc="
                + detailDesc + ", shortDesc=" + shortDesc + ", quantity=" + quantity + ", sold=" + sold + ", factory="
                + factory + ", target=" + target + "]";
    }

}
