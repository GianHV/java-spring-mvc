package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;


/**
 * Repository interface for managing operations on CartDetail entities.
 * 
 * This interface extends JpaRepository, leveraging Spring Data JPA to provide 
 * built-in methods for CRUD operations on CartDetail entities, as well as custom 
 * query methods for interacting with the relationship between Cart and Product.
 * 
 */
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    /**
     * Checks if a CartDetail exists for the given Cart and Product.
     * 
     * @param cart the Cart entity.
     * @param product the Product entity.
     * @return true if the CartDetail exists, false otherwise.
     */
    boolean existsByCartAndProduct(Cart cart, Product product);

    /**
     * Finds the CartDetail for a given Cart and Product.
     * 
     * @param cart the Cart entity.
     * @param product the Product entity.
     * @return the CartDetail matching the provided Cart and Product.
     */
    CartDetail findByCartAndProduct(Cart cart, Product product);
}
