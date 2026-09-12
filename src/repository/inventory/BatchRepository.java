package repository.inventory;

import java.util.List;
import java.util.Optional;

import model.inventory.Batch;
import model.inventory.BatchStatus;

public class BatchRepository {

    private static final String FILE_PATH = "data/inventory/batchDB.csv";

    public List<Batch> findAll() {
        return null;
    }

    public Optional<Batch> findByID(String batchID) {
        return Optional.empty();
    }

    public List<Batch> findByItemID(String itemID) {
        return null;
    }

    public List<Batch> findByStatus(BatchStatus status) {
        return null;
    }

    public void save(Batch batch) {
        // TODO: Implement CSV persistence
    }

    public void update(Batch batch) {
        // TODO: Implement CSV persistence
    }
}