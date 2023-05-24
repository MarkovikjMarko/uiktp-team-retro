package uikt.uiktpteamretrobnd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.Item;
import uikt.uiktpteamretrobnd.service.ItemService;
import uikt.uiktpteamretrobnd.web.requests.ItemRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController extends ResponseEntityExceptionHandler {
    private final ItemService service;

    private final CustomResponse response;

    @Autowired
    public ItemController(ItemService itemService, CustomResponse response) {
        this.service = itemService;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<Item>> index() {
        List<Item> items = service.findAll();

        return this.response.success(items);
    }

    @GetMapping("/{id}")
    public ApiResponse<Item> show(@PathVariable("id") Long id) {
        Optional<Item> item = service.find(id);

        if (item.isPresent()) {
            return this.response.success(item);
        } else {
            return this.response.notFound(item);
        }
    }

    @PostMapping
    public ApiResponse<Item> create(@ModelAttribute ItemRequest itemRequest) {
        Item item = service.create(itemRequest);

        return this.response.created(item);
    }

    @PutMapping("/{id}")
    public ApiResponse<Item> update(@PathVariable("id") Long id, @ModelAttribute ItemRequest itemRequest) {
        Item item = service.update(id, itemRequest);

        return this.response.success(item);
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