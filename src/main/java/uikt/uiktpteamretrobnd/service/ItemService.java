package uikt.uiktpteamretrobnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.model.Category;
import uikt.uiktpteamretrobnd.model.Item;
import uikt.uiktpteamretrobnd.repository.CategoryRepository;
import uikt.uiktpteamretrobnd.repository.ItemRepository;
import uikt.uiktpteamretrobnd.web.requests.ItemRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> find(Long id) {
        return itemRepository.findById(id);
    }

    public Item create(ItemRequest itemRequest) {
        Category category = this.categoryRepository.findById(itemRequest.getCategoryId()).orElseThrow(ModelNotFoundException::new);

        String body = itemRequest.getBody();

        Item item = new Item(body, category);

        return this.itemRepository.save(item);
    }

    public Item update(Long id, ItemRequest itemRequest) {
        Item item = itemRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String body = itemRequest.getBody();
        Long categoryId = itemRequest.getCategoryId();

        if(body != null){
            item.setBody(body);
        }

        if(categoryId != null){
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(ModelNotFoundException::new);
            item.setCategory(category);
        }

        return this.itemRepository.save(item);
    }

    public boolean delete(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);

            return true;
        }

        return false;
    }

}
