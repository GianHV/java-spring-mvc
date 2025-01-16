package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.OrderDetail;
/**
 * Repository interface for managing operations on OrderDetail entities.
 * 
 * This interface leverages Spring Data JPA to provide built-in methods for CRUD operations 
 * and supports custom query definitions when necessary.
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // JpaRepository provides various methods for CRUD operations on the Product entity.
    // The Product entity is managed, and Long is the type of the entity's primary key.
}