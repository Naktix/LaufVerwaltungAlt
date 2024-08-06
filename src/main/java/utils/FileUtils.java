package utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    // Methode zum Auswählen einer Excel-Datei
    public static File selectExcelFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        return fileChooser.showOpenDialog(stage);
    }

    // Methode zum Speichern von Daten als TXT-Datei
    public static void saveDataAsTxt(String data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File dir = new File("datas");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        fileChooser.setInitialDirectory(dir);

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
