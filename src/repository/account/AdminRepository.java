package repository.account;

import java.util.List;
import java.util.Optional;

import model.account.Admin;

public class AdminRepository {

    private static final String FILE_PATH = "data/accounts/adminDB.csv";

    public List<Admin> findAll() {
        return null;
    }

    public Optional<Admin> findByID(String adminID) {
        return Optional.empty();
    }

    public Optional<Admin> findByUsername(String username) {
        return Optional.empty();
    }

    public void save(Admin admin) {
        // TODO: Implement CSV persistence
    }

    public void update(Admin admin) {
        // TODO: Implement CSV persistence
    }
}