import java.io.IOException;

public interface SkinConsultationManager {
    void addDoctor();
    void deleteDoctor();
    void printListOfDoctors();
    void saveInAFile() throws IOException;
}
