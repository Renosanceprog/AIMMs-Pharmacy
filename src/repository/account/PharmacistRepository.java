package repository.account;

import java.util.List;
import java.util.Optional;

import model.account.Pharmacist;
import model.account.StaffStatus;

public class PharmacistRepository {

    private static final String FILE_PATH = "data/accounts/pharmacistDB.csv";

    public List<Pharmacist> findAll() {
        return null;
    }

    public Optional<Pharmacist> findByID(String pharmacistID) {
        return Optional.empty();
    }

    public Optional<Pharmacist> findByUsername(String username) {
        return Optional.empty();
    }

    public List<Pharmacist> findByStatus(StaffStatus status) {
        return null;
    }

    public void save(Pharmacist pharmacist) {
        // TODO: Implement CSV persistence
    }

    public void update(Pharmacist pharmacist) {
        // TODO: Implement CSV persistence
    }
}