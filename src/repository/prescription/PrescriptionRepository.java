package repository.prescription;

import java.util.List;
import java.util.Optional;

import model.prescription.Prescription;
import model.prescription.PrescriptionStatus;

public class PrescriptionRepository {

    private static final String FILE_PATH = "data/prescriptions/prescriptionsDB.csv";

    public List<Prescription> findAll() {
        return null;
    }

    public Optional<Prescription> findByID(String prescriptionID) {
        return Optional.empty();
    }

    public List<Prescription> findByCustomerID(String customerID) {
        return null;
    }

    public List<Prescription> findByStatus(PrescriptionStatus status) {
        return null;
    }

    public void save(Prescription prescription) {
        // TODO: Implement CSV persistence
    }

    public void update(Prescription prescription) {
        // TODO: Implement CSV persistence
    }
}