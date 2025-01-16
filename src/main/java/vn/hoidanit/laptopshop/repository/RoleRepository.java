package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Role;

/**
 * Repository interface for managing operations on Role entities.
 * 
 * This interface extends JpaRepository, which provides a wide range of CRUD operations 
 * and additional query capabilities for the Role entity. By extending JpaRepository, 
 * this interface benefits from the integration of Spring Data JPA, simplifying database interactions.
 * 
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a Role entity based on its name.
     * 
     * This method uses Spring Data JPA's query derivation mechanism to generate the required query.
     * It is particularly useful for applications where roles are used to define user permissions 
     * and access control, such as in authentication systems.
     * @param name the name of the Role to be retrieved.
     * @return the Role entity with the specified name, or null if no such Role exists.
     */
    Role findByName(String name);
}