package repository.prescription;

import java.util.List;
import java.util.Optional;

import model.prescription.PrescriptionItem;

public class PrescriptionItemRepository {

    private static final String FILE_PATH = "data/prescriptions/prescriptionItemsDB.csv";

    public List<PrescriptionItem> findAll() {
        return null;
    }

    public Optional<PrescriptionItem> findByID(String prescriptionItemID) {
        return Optional.empty();
    }

    public List<PrescriptionItem> findByPrescriptionID(String prescriptionID) {
        return null;
    }

    public void save(PrescriptionItem prescriptionItem) {
        // TODO: Implement CSV persistence
    }

    public void update(PrescriptionItem prescriptionItem) {
        // TODO: Implement CSV persistence
    }
}