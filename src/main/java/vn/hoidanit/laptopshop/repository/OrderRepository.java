package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.User;

import java.util.List;

/**
 * Repository interface for managing operations on Order entities.
 * 
 * This interface extends JpaRepository, a core abstraction in Spring Data JPA, 
 * providing pre-implemented methods for common database operations. 
 * It also allows the creation of custom query methods to address specific business requirements.
 * 
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves a list of Order entities associated with a specific User.
     * 
     * This custom query method leverages Spring Data JPA's method naming conventions 
     * to automatically implement the logic for fetching all orders placed by a given User.
     * 
     * @param user the User entity whose orders are to be retrieved.
     * @return a List of Order entities associated with the specified User, 
     *         or an empty list if the User has no orders.
     */
    List<Order> findByUser(User user);
}
