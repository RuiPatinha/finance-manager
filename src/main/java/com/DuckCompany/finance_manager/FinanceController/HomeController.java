package com.DuckCompany.finance_manager.FinanceController;


import com.DuckCompany.finance_manager.model.Category;
import com.DuckCompany.finance_manager.model.CategoryType;
import com.DuckCompany.finance_manager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable

@Controller //register the class as the Spring MVC controller
public class HomeController {

    @GetMapping("/") //tells Spring to handle GET requests to the homepage Maps the homepage URL.
    public String home(Model model) { //provides the object to the controller method. Send data to the view
        model.addAttribute("title", "Finance Manager");
        model.addAttribute("message", "Manage your personal Finance in on place.");
        model.addAttribute("categories", categoryService.getAllCategories()); //askes the service to retrieve all categories from the database and passes the data to the home.html

        return "home"; //look for home.html  ---> thymeleaft finds home.html and renders it in the browser
    }  //model is a container of data that the controller passes to the view

    private final CategoryService categoryService;//This field will hold the service instance that Spring provides.

    public HomeController(CategoryService categoryService) { //construtor dependency injection
        //handles the request to the homepage
        this.categoryService = categoryService;
    }



    @GetMapping("/categories/new") //tells Spring to execute this method when you visit that URL.
    public String newCategory(Model model) { //new Category() creates an empty category object for the form.
        model.addAttribute("category", new Category());
        model.addAttribute("category type", //CategoryType.values()); CategoryType.values() gets all available enum values: INCOME and EXPENSE.
        return "categories/new"; //return "categories/new" tells Thymeleaf to render the template at templates/categories/new.html.
    }



    @PostMapping("/categories") //handles the form's POST request.
    //saving a category when you submit the form.
    public String saveCategory(@ModelAttribute Category category, //@ModelAttribute Category category takes the submitted form fields and puts them into a Category object.
                               RedirectAttributes redirectAttributes) {
        categoryService.saveCategory(category); //categoryService.saveCategory(category) passes that object to the service, which saves it through the repository into PostgreSQL.
        redirectAttributes.addFlashAttribute("successMessage", "Category created successfully!"); //RedirectAttributes lets us display a one-time success message after redirecting to the home page.
        return "redirect:/"; //return "redirect:/" sends the browser back to the homepage.
    }

    @PostMapping("/categories/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/";
        
    }
}
