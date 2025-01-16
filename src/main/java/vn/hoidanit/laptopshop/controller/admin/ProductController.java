package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

/**
 * This controller handles routing actions for managing products in the admin section.
 * 
 * Responsibilities:
 * - Display, create, update, delete, and show details of products.
 */
@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;
    private final int LIMIT_PRODUCT_PER_PAGE = 4;

    /**
     * Constructor for injecting dependencies.
     * 
     * @param uploadService   Service for handling file uploads.
     * @param productService  Service for handling product-related operations.
     */
    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    /**
     * Displays the product management page.
     * 
     * @param model Spring model for passing attributes to the view.
     * @return String representing the view name for the product management page.
     */
    @GetMapping("/admin/product")
    public String getProduct(
            Model model,
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

        Pageable pageable = PageRequest.of(page - 1, LIMIT_PRODUCT_PER_PAGE);
        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> listProducts = prs.getContent();

        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());

        return "admin/product/show";
    }

    /**
     * Displays the page for creating a new product.
     * 
     * @param model Spring model for passing attributes to the view.
     * @return String representing the view name for creating a product.
     */
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    /**
     * Handles form submission for creating a new product.
     * 
     * @param pr                  Product object bound to the form.
     * @param newProductBindingResult Binding result for validating input data.
     * @param file                Uploaded image file.
     * @return String representing the redirect URL or view name.
     */
    @PostMapping("/admin/product/create")
    public String handleCreateProduct(
            @ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String image = this.uploadService.handleSaveUploadFile(file, "product");
        pr.setImage(image);

        this.productService.createProduct(pr);
        return "redirect:/admin/product";
    }

    /**
     * Displays the page for updating an existing product.
     * 
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the product to update.
     * @return String representing the view name for updating a product.
     */
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Optional<Product> currentProduct = this.productService.fetchProductById(id);
        model.addAttribute("newProduct", currentProduct.orElse(new Product()));
        return "admin/product/update";
    }

    /**
     * Handles form submission for updating an existing product.
     * 
     * @param pr                  Product object bound to the form.
     * @param newProductBindingResult Binding result for validating input data.
     * @param file                Uploaded image file.
     * @return String representing the redirect URL or view name.
     */
    @PostMapping("/admin/product/update")
    public String handleUpdateProduct(@ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }

        Product currentProduct = this.productService.fetchProductById(pr.getId()).orElse(null);
        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }

            currentProduct.setName(pr.getName());
            currentProduct.setPrice(pr.getPrice());
            currentProduct.setQuantity(pr.getQuantity());
            currentProduct.setDetailDesc(pr.getDetailDesc());
            currentProduct.setShortDesc(pr.getShortDesc());
            currentProduct.setFactory(pr.getFactory());
            currentProduct.setTarget(pr.getTarget());

            this.productService.createProduct(currentProduct);
        }

        return "redirect:/admin/product";
    }

    /**
     * Displays the page for deleting a product.
     * 
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the product to delete.
     * @return String representing the view name for deleting a product.
     */
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    /**
     * Handles form submission for deleting a product.
     * 
     * @param model Spring model for passing attributes to the view.
     * @param pr    Product object bound to the form.
     * @return String representing the redirect URL.
     */
    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product pr) {
        this.productService.deleteProduct(pr.getId());
        return "redirect:/admin/product";
    }

    /**
     * Displays the detail page for a specific product.
     * 
     * @param model Spring model for passing attributes to the view.
     * @param id    ID of the product to view.
     * @return String representing the view name for the product detail page.
     */
    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product pr = this.productService.fetchProductById(id).orElse(null);
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }
}