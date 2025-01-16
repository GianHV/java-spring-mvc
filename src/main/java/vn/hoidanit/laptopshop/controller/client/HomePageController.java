package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;
/**
 * This controller handles routing actions related to the home page and user authentication.
 * It includes methods for displaying the home page, user registration, and login functionality.
 * 
 * Responsibilities:
 * - Display the list of products on the home page.
 * - Provide user registration functionality, including form validation and password encryption.
 * - Display the login page for user authentication.
 * 
 * Dependencies:
 * - ProductService: For handling product-related operations.
 * - UserService: For handling user-related operations.
 * - PasswordEncoder: For hashing user passwords securely.
 */
@Controller
public class HomePageController {

    // Service for handling product-related operations
    private final ProductService productService;

    // Service for handling user-related operations
    private final UserService userService;

    // Encoder for hashing passwords
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;
    private final int LIMIT_PRODUCT_PER_PAGE = 10;

    public HomePageController(
            ProductService productService,
            UserService userService,
            PasswordEncoder passwordEncoder,
            OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
    }

    /**
     * Handles GET request for the home page.
     * Fetches and displays a list of products.
     * 
     * @param model Spring model for passing attributes to the view.
     * @return String representing the view name for the home page.
     */
    @GetMapping("/")
    public String getHomePage(Model model) {
        Pageable pageable = PageRequest.of(0, LIMIT_PRODUCT_PER_PAGE);
        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> products = prs.getContent();

        model.addAttribute("products", products);

        return "client/homepage/show";
    }

    /**
     * Handles GET request for the registration page.
     * 
     * @param model Spring model for passing attributes to the view.
     * @return String representing the view name for the registration page.
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        // Add a new RegisterDTO object to the model for form binding
        model.addAttribute("registerUser", new RegisterDTO());

        return "client/auth/register";
    }

    /**
     * Handles POST request for user registration.
     * Validates input, hashes the password, assigns roles, and saves the user.
     * 
     * @param registerDTO  DTO for capturing user registration data.
     * @param bindingResult Binding result for validating input data.
     * @return String representing the view name or redirect URL.
     */
    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        // Convert RegisterDTO to User entity
        User user = this.userService.registerDTOtoUser(registerDTO);

        // Hash the user's password
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        // Assign the default role
        user.setRole(this.userService.getRoleByName("USER"));
        // save
        this.userService.handleSaveUser(user);
        return "redirect:/login";

    }

    /**
     * Handles GET request for the login page.
     * 
     * @param model Spring model for passing attributes to the view.
     * @return String representing the view name for the login page.
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {

        return "client/auth/deny";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        List<Order> orders = this.orderService.fetchOrderByUser(currentUser);
        model.addAttribute("orders", orders);

        return "client/cart/order-history";
    }

}