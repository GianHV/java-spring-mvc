package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.User;

/**
 * Repository interface for managing operations on Cart entities.
 * 
 * This interface extends JpaRepository, a Spring Data JPA component that provides 
 * a wide range of built-in methods for CRUD operations and allows the creation 
 * of custom query methods for interacting with Cart entities.
 * 
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Retrieves a Cart associated with the specified User.
     * 
     * @param user the User entity whose Cart is to be retrieved.
     * @return the Cart entity associated with the given User, or null if no such Cart exists.
     */
    Cart findByUser(User user);
}
