package vn.hoidanit.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;

/**
 * Repository interface for managing operations on Product entities.
 * 
 * This interface extends JpaRepository, a key abstraction in Spring Data JPA, 
 * which provides built-in methods for standard database operations. 
 * It also supports custom query creation and advanced functionalities such as pagination and sorting.
 * 
 * Key functionalities:
 * - Offers CRUD operations (Create, Read, Update, Delete) for the Product entity.
 * - Facilitates efficient data retrieval through support for pagination and sorting.
*/
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Retrieves a paginated list of all Product entities.
     * 
     * This method leverages Spring Data JPA's built-in support for pagination 
     * by accepting a Pageable object, which specifies the page number, size, and sorting parameters.
     * @param page the Pageable object containing pagination and sorting information.
     * @return a Page of Product entities based on the specified Pageable configuration.
     */
    Page<Product> findAll(Pageable page);

}