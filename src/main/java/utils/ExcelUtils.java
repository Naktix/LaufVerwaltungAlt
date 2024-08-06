package utils;

import felixApp.Person;
import felixApp.Team;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

        // Methode zum Speichern von Personendaten in die zuletzt erstellte Person.xlsx
        public static void saveDataToExcel(Person person) {
            String filePath = getLastCreatedFilePath("Person");
            if (filePath == null) {
                System.err.println("Keine Person.xlsx-Datei gefunden.");
                return;
            }
    
            try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
                Sheet sheet = workbook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                Row row = sheet.createRow(++lastRowNum);
                row.createCell(0).setCellValue(person.getLauf());
                row.createCell(1).setCellValue(person.getWievielterLauf());
                row.createCell(2).setCellValue(person.getJahr());
                row.createCell(3).setCellValue(person.getDienstgrad());
                row.createCell(4).setCellValue(person.getNameVorname());
                row.createCell(5).setCellValue(person.getEinheit());
                row.createCell(6).setCellValue(person.getPlatz());
                row.createCell(7).setCellValue(person.getLaufstrecke());
                row.createCell(8).setCellValue(person.getAltersklasseJahrgang());
                row.createCell(9).setCellValue(person.getZeit());
                row.createCell(10).setCellValue(person.getOrtDatum());
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        // Methode zum Speichern von Teamdaten in die zuletzt erstellte Team.xlsx
        public static void saveDataToExcel(Team team) {
            String filePath = getLastCreatedFilePath("Team");
            if (filePath == null) {
                System.err.println("Keine Team.xlsx-Datei gefunden.");
                return;
            }
    
            try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
                Sheet sheet = workbook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                Row row = sheet.createRow(++lastRowNum);
                row.createCell(0).setCellValue(team.getLauf());
                row.createCell(1).setCellValue(team.getWievielterLauf());
                row.createCell(2).setCellValue(team.getJahr());
                row.createCell(3).setCellValue(team.getEinheit());
                row.createCell(4).setCellValue(team.getTeilnehmerzahl());
                row.createCell(5).setCellValue(team.getTeamwertung());
                row.createCell(6).setCellValue(team.getPlatz());
                row.createCell(7).setCellValue(team.getGesamtzeit());
                row.createCell(8).setCellValue(team.getBester1());
                row.createCell(9).setCellValue(team.getBester2());
                row.createCell(10).setCellValue(team.getBester3());
                row.createCell(11).setCellValue(team.getOrtDatum());
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        // Hilfsmethode zum Abrufen des Pfads der zuletzt erstellten Datei eines bestimmten Typs
        private static String getLastCreatedFilePath(String type) {
            File dir = new File("datas");
            File[] files = dir.listFiles((d, name) -> name.startsWith(type) && name.endsWith(".xlsx"));
            if (files == null || files.length == 0) {
                return null;
            }
            File lastFile = files[0];
            for (File file : files) {
                if (file.lastModified() > lastFile.lastModified()) {
                    lastFile = file;
                }
            }
            return lastFile.getPath();
        }


    // Methode zur Suche von Daten in Excel
    public static List<String> searchDataInExcel(File file, String searchTerm) {
        List<String> results = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(file))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().contains(searchTerm)) {
                        // Ganze Reihe zurückgeben
                        StringBuilder rowResult = new StringBuilder();
                        for (Cell currentCell : row) {
                            rowResult.append(currentCell.toString()).append(" ");
                        }
                        results.add("Zeile " + (row.getRowNum() + 1) + ": " + rowResult.toString().trim());
                        // Wenn Übereinstimmung gefunden, nächste Zeile prüfen
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }


    // Methode zum Erstellen einer neuen Excel-Datei
    public static File createNewExcelFile(String type) {
        try (Workbook workbook = new XSSFWorkbook()) {
            workbook.createSheet("Data");
            try {
                File dir = new File("datas");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                int count = 1;
                String filePath;
                File file;
                do {
                    filePath = "datas/" + type + count + ".xlsx";
                    file = new File(filePath);
                    count++;
                } while (file.exists());
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    workbook.write(fileOut);
                }
                return file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
