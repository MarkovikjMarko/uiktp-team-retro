package uikt.uiktpteamretrobnd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.Category;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.CategoryRepository;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;
import uikt.uiktpteamretrobnd.web.requests.CategoryRequest;

import java.util.List;
import java.util.Optional;



@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RetrospectiveRepository retrospective;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, RetrospectiveRepository retrospective) {
        this.categoryRepository = categoryRepository;
        this.retrospective = retrospective;
    }


    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }

    public Optional<Category> find(Long id)
    {
        return categoryRepository.findById(id);
    }


    public Category create(CategoryRequest categoryRequest)
    {
        Retrospective retro = this.retrospective.findById(categoryRequest.getRetrospectiveId()).orElseThrow(ModelNotFoundException::new);
        String name = categoryRequest.getName();
        String description = categoryRequest.getDescription();
        Category category = new Category(name,description,retro);
        categoryRepository.save(category);
        return category;
    }


    public Category update(Long id, CategoryRequest categoryRequest) {
        Category category = this.categoryRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String name = categoryRequest.getName();
        String descripiton = categoryRequest.getDescription();

        if(name != null){
            category.setName(name);
        }

        if(descripiton != null){
            category.setDescription(descripiton);
        }

        categoryRepository.save(category);
        return category;
    }

    public boolean delete(Long id)
    {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
