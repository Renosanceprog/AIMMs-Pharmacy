package repository.transaction;

import java.util.List;
import java.util.Optional;
import model.transaction.Transaction;
import model.transaction.TransactionStatus;

public class TransactionRepository {
    private static final String FILE_PATH = "data/transactions/transactionsDB.csv";

    public List<Transaction> findAll() { return null; }

    public Optional<Transaction> findByID(String transactionID) {
        return Optional.empty();
    }

    public List<Transaction> findByCustomerID(String customerID) {
        return null;
    }

    public List<Transaction> findByStatus(TransactionStatus status) {
        return null;
    }

    public void save(Transaction transaction) { /* TODO */ }

    public void update(Transaction transaction) { /* TODO */ }
}