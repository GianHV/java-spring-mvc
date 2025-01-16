package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

/*
 * This service class handles business logic related to user management.
 * It interacts with UserRepository and RoleRepository to manage user data and roles.
 */
@Service
public class UserService {

    // UserRepository is injected to perform CRUD operations on User entities.
    private final UserRepository userRepository;
    
    // RoleRepository is injected to manage Role entities.
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Page<User> getUsers(Pageable page) {
        return this.userRepository.findAll(page);
    }

    /*
     * Checks if a user with the given email already exists.
     * @param email the email to check.
     * @return true if the email exists, false otherwise.
     */
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    /*
     * Retrieves a list of users that match the provided email.
     * @param email the email to search for.
     * @return a list of users whose email contains the provided value.
     */
    public List<User> getUsersByEmail(String email) {
        return this.userRepository.findByEmailContaining(email);
    }

    /*
     * Retrieves a user by their ID.
     * @param id the ID of the user to retrieve.
     * @return the user with the given ID.
     */
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    /*
     * Deletes a user by their ID.
     * @param id the ID of the user to delete.
     */
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    /*
     * Saves a new or updated user to the repository.
     * @param user the user entity to save.
     * @return the saved user entity.
     */
    public User handleSaveUser(User user) {
        User saved = this.userRepository.save(user);
        return saved;
    }

    /*
     * Retrieves a role by its name.
     * @param name the name of the role to retrieve.
     * @return the Role entity that matches the provided name.
     */
    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    /*
     * Converts a RegisterDTO object to a User entity.
     * @param registerDTO the DTO containing registration data.
     * @return the User entity populated with the data from the DTO.
     */
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }

    /*
     * Retrieves a user by their email.
     * @param email the email of the user to retrieve.
     * @return the User entity with the given email.
     */
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
