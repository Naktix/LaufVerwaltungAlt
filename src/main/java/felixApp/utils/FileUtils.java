package felixApp.utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.print.*;
import javax.print.attribute.AttributeSet;

import java.io.BufferedWriter;
import java.io.Closeable;
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
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Methode zum Drucken von Daten
    public static void printData(String data) {
        try {
            File tempFile = File.createTempFile("data", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                writer.write(data);
            }

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, (AttributeSet) pras);

            if (printServices.length > 0) {
                PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                DocPrintJob job = printService.createPrintJob();
                FileInputStream fis = new FileInputStream();
                Doc doc = new SimpleDoc(fis, flavor, null);
                job.print(doc, null);
                ((Closeable) fis).close();
            }
        } catch (IOException | PrintException e) {
            e.printStackTrace();
        }
    }
}
