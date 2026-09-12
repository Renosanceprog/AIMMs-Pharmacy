package repository.account;

import java.util.List;
import java.util.Optional;

import model.account.Customer;

public class CustomerRepository {

    private static final String FILE_PATH = "data/accounts/customerDB.csv";

    public List<Customer> findAll() {
        return null;
    }

    public Optional<Customer> findByID(String customerID) {
        return Optional.empty();
    }

    public Optional<Customer> findByUsername(String username) {
        return Optional.empty();
    }

    public void save(Customer customer) {
        // TODO: Implement CSV persistence
    }

    public void update(Customer customer) {
        // TODO: Implement CSV persistence
    }
}