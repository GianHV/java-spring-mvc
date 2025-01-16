package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class that maps to the "order_detail" table in the database.
 * 
 * This class serves the following purposes:
 * 1. Defines the structure of the "order_detail" table, including relationships with other entities.
 * 2. Maps Java objects to rows in the "order_detail" table for CRUD operations.
 * 3. Enables database queries (using JPA/Hibernate) related to order details, such as retrieving,
 *    inserting, updating, or deleting records.
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id; // Primary key of the OrderDetail entity.

    private long quantity; // Quantity of the product in the order.
    private double price; // Price of the product in the order.

    @ManyToOne 
    @JoinColumn(name = "order_id") 
    private Order order; // The order associated with this detail (foreign key to "orders").

    @ManyToOne 
    @JoinColumn(name = "product_id") 
    private Product product; // The product associated with this detail (foreign key to "products").

    /*
     * Gets the ID of the order detail.
     * @return the ID of the order detail.
     */
    public long getId() {
        return id;
    }

    /*
     * Sets the ID of the order detail.
     * @param id the ID to be set for the order detail.
     */
    public void setId(long id) {
        this.id = id;
    }

    /*
     * Gets the quantity of the product in the order detail.
     * @return the quantity of the product.
     */
    public long getQuantity() {
        return quantity;
    }

    /*
     * Sets the quantity of the product in the order detail.
     * @param quantity the quantity to be set.
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /*
     * Gets the price of the product in the order detail.
     * @return the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /*
     * Sets the price of the product in the order detail.
     * @param price the price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * Gets the associated order for this order detail.
     * @return the associated order.
     */
    public Order getOrder() {
        return order;
    }

    /*
     * Sets the associated order for this order detail.
     * @param order the order to be set.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /*
     * Gets the associated product for this order detail.
     * @return the associated product.
     */
    public Product getProduct() {
        return product;
    }

    /*
     * Sets the associated product for this order detail.
     * @param product the product to be set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
