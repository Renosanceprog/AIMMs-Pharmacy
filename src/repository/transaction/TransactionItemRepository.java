package repository.transaction;

import java.util.List;
import java.util.Optional;
import model.transaction.TransactionItem;

public class TransactionItemRepository {
    private static final String FILE_PATH = "data/transactions/transactionItemsDB.csv";

    public List<TransactionItem> findAll() { return null; }

    public Optional<TransactionItem> findByID(String transactionItemID) {
        return Optional.empty();
    }

    public List<TransactionItem> findByTransactionID(String transactionID) {
        return null;
    }

    public void save(TransactionItem transactionItem) { /* TODO */ }
}