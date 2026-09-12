package repository.inventory;

import java.util.List;
import java.util.Optional;

import model.inventory.Item;
import model.inventory.ItemStatus;

public class ItemRepository {

    private static final String FILE_PATH = "data/inventory/itemsDB.csv";

    public List<Item> findAll() {
        return null;
    }

    public Optional<Item> findByID(String itemID) {
        return Optional.empty();
    }

    public List<Item> findByStatus(ItemStatus status) {
        return null;
    }

    public void save(Item item) {
        // TODO: Implement CSV persistence
    }

    public void update(Item item) {
        // TODO: Implement CSV persistence
    }
}