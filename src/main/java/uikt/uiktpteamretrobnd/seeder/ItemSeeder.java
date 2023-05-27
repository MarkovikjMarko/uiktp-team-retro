package uikt.uiktpteamretrobnd.seeder;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.model.Category;
import uikt.uiktpteamretrobnd.model.Item;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.repository.CategoryRepository;
import uikt.uiktpteamretrobnd.repository.ItemRepository;
import uikt.uiktpteamretrobnd.repository.UserRepository;

@Component
public class ItemSeeder {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ItemSeeder(ItemRepository itemRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public void seed(){
        Faker faker = new Faker();
        for(Category category: categoryRepository.findAll()){
            User user = userRepository.randomUser();

            String body = faker.lorem().paragraph();

            Item item = new Item(body, category, user);
            itemRepository.save(item);
        }
    }
}
