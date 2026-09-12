package repository.account;

import java.util.List;
import java.util.Optional;

import model.account.Doctor;
import model.account.StaffStatus;

public class DoctorRepository {

    private static final String FILE_PATH = "data/accounts/doctorDB.csv";

    public List<Doctor> findAll() {
        return null;
    }

    public Optional<Doctor> findByID(String doctorID) {
        return Optional.empty();
    }

    public Optional<Doctor> findByUsername(String username) {
        return Optional.empty();
    }

    public List<Doctor> findByStatus(StaffStatus status) {
        return null;
    }

    public void save(Doctor doctor) {
        // TODO: Implement CSV persistence
    }

    public void update(Doctor doctor) {
        // TODO: Implement CSV persistence
    }
}