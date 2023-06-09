package uikt.uiktpteamretrobnd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.Category;
import uikt.uiktpteamretrobnd.model.Item;
import uikt.uiktpteamretrobnd.service.CategoryService;
import uikt.uiktpteamretrobnd.web.requests.CategoryRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends ResponseEntityExceptionHandler {
    private final CategoryService service;
    private final CustomResponse response;


    @Autowired
    public CategoryController(CategoryService service, CustomResponse response) {
        this.service = service;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<Category>> index()
    {
        List<Category> category = service.findAll();
        return this.response.success(category);
    }


    @GetMapping("/{id}")
    public ApiResponse<Item> show(@PathVariable("id") Long id) {
        Optional<Category> category = service.find(id);

        if (category.isPresent()) {
            return this.response.success(category);
        } else {
            return this.response.notFound(category);
        }
    }

    @PostMapping
    public ApiResponse<Category> create(@ModelAttribute CategoryRequest categoryRequest) throws IOException {
        Category category = service.create(categoryRequest);
        return this.response.created(category);
    }

    @PutMapping("/{id}")
    public ApiResponse<Item> update(@PathVariable("id") Long id, @ModelAttribute CategoryRequest categoryRequest) throws IOException {
        Category category = service.update(id, categoryRequest);

        return this.response.success(category);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);

        if (deleted) {
            return response.deleted();
        } else {
            return this.response.notFound(null);
        }
    }
}
