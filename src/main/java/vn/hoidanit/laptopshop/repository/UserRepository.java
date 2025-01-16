package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

/*
 * This interface serves as a repository component for Spring, 
 * providing CRUD operations for User entities.
 * 
 * As an interface extending JpaRepository, it automatically benefits from the 
 * various built-in CRUD methods and can be used to handle User entities efficiently.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * Save the given User entity to the database.
     * 
     * This method is inherited from JpaRepository and is used to persist a User entity.
     * @param user the User entity to be saved.
     * @return the saved User entity.
     */
    User save(User user);

    /*
     * Delete a User entity by its ID.
     * 
     * This method is inherited from JpaRepository and allows the deletion of a User 
     * entity from the database using its unique ID.
     * @param id the ID of the User to be deleted.
     */
    void deleteById(long id);

    /*
     * Find all Users whose email contains the given string.
     * 
     * This custom method will search for all User entities whose email contains 
     * the specified substring. It is particularly useful for filtering users 
     * based on partial matches for email addresses.
     * 
     * @param email the substring to search for in the email field.
     * @return a list of User entities matching the criteria.
     */
    List<User> findByEmailContaining(String email);

    /*
     * Find a User by its ID.
     * 
     * This method will find and return a User entity based on its unique ID.
     * It is useful for locating a specific user by their identifier.
     * 
     * @param id the ID of the User to be found.
     * @return the User entity matching the provided ID.
     */
    List<User> findAll();

    /*
     * Find a User by its ID.
     * @param id the ID of the User to be found.
     * @return the User entity matching the provided ID.
     */
    User findById(long id);

    /*
     * Check if a User exists with the given email.
     * 
     * This custom method checks if there is a User entity in the database 
     * that has the specified email. It is helpful for validation purposes.
     * 
     * @param email the email to check for existence.
     * @return true if a User exists with the given email, false otherwise.
     */
    boolean existsByEmail(String email);
   /*
     * Find a User by its email address.
     * 
     * This method retrieves the User entity associated with the provided email.
     * It is useful when searching for a user based on their email, such as for login or authentication purposes.
     * 
     * @param email the email of the User to be found.
     * @return the User entity with the given email.
     */
    User findByEmail(String email);
}
