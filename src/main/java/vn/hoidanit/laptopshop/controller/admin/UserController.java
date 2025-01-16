package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller manages user-related actions in the admin section, 
 * including creating, updating, deleting, and displaying user details.
 */
@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final int LIMIT_USER_PER_PAGE = 4;

    /**
     * Constructor for dependency injection.
     *
     * @param userService       Service for user operations.
     * @param uploadService     Service for file uploads.
     * @param passwordEncoder   Service for encoding passwords.
     */
    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Displays the home page.
     *
     * @param model Spring model for passing attributes to the view.
     * @return String representing the home page view name.
     */
    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }

    /**
     * Displays the user management page.
     *
     * @param model Spring model for passing attributes to the view.
     * @return String representing the user management page view name.
     */
    @RequestMapping("/admin/user")
    public String getUserPage(Model model,
            @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                // convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // page = 1
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, LIMIT_USER_PER_PAGE);
        Page<User> usersPage = this.userService.getUsers(pageable);
        List<User> users = usersPage.getContent();
        model.addAttribute("users1", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "admin/user/show";
    }

    /**
     * Displays the user detail page for a specific user.
     *
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the user.
     * @return String representing the user detail page view name.
     */
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    /**
     * Displays the page for creating a new user.
     *
     * @param model Spring model for passing attributes to the view.
     * @return String representing the create user page view name.
     */
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    /**
     * Handles form submission for creating a new user.
     *
     * @param model                  Spring model for passing attributes to the view.
     * @param request                User object bound to the form.
     * @param newUserBindingResult   Binding result for validating input data.
     * @param file                   Uploaded avatar file.
     * @return String representing the redirect URL or view name.
     */
    @PostMapping("/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User hoidanit,
            BindingResult newUserBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {

        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println(">>>>" + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        //
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());

        hoidanit.setAvatar(avatar);
        hoidanit.setPassword(hashPassword);
        hoidanit.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
        // save
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    /**
     * Displays the page for updating an existing user.
     *
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the user to update.
     * @return String representing the update user page view name.
     */
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    /**
     * Handles form submission for updating an existing user.
     *
     * @param model  Spring model for passing attributes to the view.
     * @param user   Updated user data.
     * @return String representing the redirect URL.
     */
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    /**
     * Displays the page for deleting a user.
     *
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the user to delete.
     * @return String representing the delete user page view name.
     */
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    /**
     * Handles form submission for deleting a user.
     *
     * @param model Spring model for passing attributes to the view.
     * @param user  User object with the ID to delete.
     * @return String representing the redirect URL.
     */
    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }
}
