package felixApp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.ExcelUtils;
import utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FelixApp");

        // Haupt-GridPane
        GridPane grid = createGridPane();

        // Eingabefelder für Person
        TextField laufField = new TextField();
        TextField wievielterLaufField = new TextField();
        TextField jahrField = new TextField();
        TextField dienstgradField = new TextField();
        TextField nameVornameField = new TextField();
        TextField einheitField = new TextField();
        TextField platzField = new TextField();
        TextField laufstreckeField = new TextField();
        TextField altersklasseJahrgangField = new TextField();
        TextField zeitField = new TextField();
        TextField ortDatumField = new TextField();

        // Hinzufügen der Person-Eingabefelder zum Grid
        addPersonFieldsToGrid(grid, laufField, wievielterLaufField, jahrField, dienstgradField, nameVornameField, einheitField, platzField, laufstreckeField, altersklasseJahrgangField, zeitField, ortDatumField);

        // Buttons
        Button saveButton = new Button("Speichern");
        Button searchButton = new Button("Suchen");
        Button newExcelButton = new Button("Neue Excel-Datei");
        Button switchToTeamButton = new Button("Mannschaftsurkunden");

        // Hinzufügen der Buttons zum Grid
        HBox buttonBox = new HBox(10, saveButton, searchButton, newExcelButton, switchToTeamButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 11, 2, 1);

        // Event-Handler
        saveButton.setOnAction(e -> {
            Person person = new Person(laufField.getText(), wievielterLaufField.getText(), jahrField.getText(), dienstgradField.getText(), nameVornameField.getText(), einheitField.getText(), platzField.getText(), laufstreckeField.getText(), altersklasseJahrgangField.getText(), zeitField.getText(), ortDatumField.getText());
            ExcelUtils.saveDataToExcel(person);
            clearPersonFields(laufField, wievielterLaufField, jahrField, dienstgradField, nameVornameField, einheitField, platzField, laufstreckeField, altersklasseJahrgangField, zeitField, ortDatumField);
        });

        searchButton.setOnAction(e -> {
            File file = selectExcelFileFromDataFolder(primaryStage);
            if (file != null) {
                String searchTerm = showSearchDialog(primaryStage);
                List<String> results = ExcelUtils.searchDataInExcel(file, searchTerm);
                if (results != null && !results.isEmpty()) {
                    showSearchResults(primaryStage, results);
                } else {
                    showAlert("Suchergebnis", "Keine Daten gefunden.");
                }
            }
        });

        newExcelButton.setOnAction(e -> {
            ExcelUtils.createNewExcelFile("Person");
        });

        switchToTeamButton.setOnAction(e -> {
            primaryStage.setScene(createTeamScene(primaryStage));
        });

        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Erstellt und konfiguriert das GridPane
    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }

    // Hinzufügen der Person-Eingabefelder zum GridPane
    private void addPersonFieldsToGrid(GridPane grid, TextField laufField, TextField wievielterLaufField, TextField jahrField, TextField dienstgradField, TextField nameVornameField, TextField einheitField, TextField platzField, TextField laufstreckeField, TextField altersklasseJahrgangField, TextField zeitField, TextField ortDatumField) {
        grid.add(new Label("Lauf:"), 0, 0);
        grid.add(laufField, 1, 0);
        grid.add(new Label("Wievielter Lauf:"), 0, 1);
        grid.add(wievielterLaufField, 1, 1);
        grid.add(new Label("Jahr:"), 0, 2);
        grid.add(jahrField, 1, 2);
        grid.add(new Label("Dienstgrad:"), 0, 3);
        grid.add(dienstgradField, 1, 3);
        grid.add(new Label("Name, Vorname:"), 0, 4);
        grid.add(nameVornameField, 1, 4);
        grid.add(new Label("Einheit:"), 0, 5);
        grid.add(einheitField, 1, 5);
        grid.add(new Label("Platz:"), 0, 6);
        grid.add(platzField, 1, 6);
        grid.add(new Label("Laufstrecke:"), 0, 7);
        grid.add(laufstreckeField, 1, 7);
        grid.add(new Label("Altersklasse und Jahrgang:"), 0, 8);
        grid.add(altersklasseJahrgangField, 1, 8);
        grid.add(new Label("Zeit:"), 0, 9);
        grid.add(zeitField, 1, 9);
        grid.add(new Label("Ort und Datum:"), 0, 10);
        grid.add(ortDatumField, 1, 10);
    }

    // Leeren der Person-Eingabefelder
    private void clearPersonFields(TextField laufField, TextField wievielterLaufField, TextField jahrField, TextField dienstgradField, TextField nameVornameField, TextField einheitField, TextField platzField, TextField laufstreckeField, TextField altersklasseJahrgangField, TextField zeitField, TextField ortDatumField) {
        laufField.clear();
        wievielterLaufField.clear();
        jahrField.clear();
        dienstgradField.clear();
        nameVornameField.clear();
        einheitField.clear();
        platzField.clear();
        laufstreckeField.clear();
        altersklasseJahrgangField.clear();
        zeitField.clear();
        ortDatumField.clear();
    }

    // Zeigt einen Dialog zum Eingeben des Suchbegriffs an
    private String showSearchDialog(Stage stage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Suchen");
        dialog.setHeaderText("Suchbegriff eingeben");
        dialog.setContentText("Suchbegriff:");
        dialog.initOwner(stage);
        return dialog.showAndWait().orElse("");
    }

    // Zeigt die Suchergebnisse an
    private void showSearchResults(Stage stage, List<String> results) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suchergebnis");
        alert.setHeaderText("Gefundene Daten");
        alert.setContentText(String.join("\n", results));
    
        ButtonType saveButtonType = new ButtonType("Als TXT speichern");
        alert.getButtonTypes().add(saveButtonType);
    
        Optional<ButtonType> result = alert.showAndWait();
    
        result.ifPresent(response -> {
            if (response == saveButtonType) {
                FileUtils.saveDataAsTxt(String.join("\n", results));
            }
        });
    }

    // Zeigt eine Warnung an
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Auswahl einer Excel-Datei aus dem Ordner "datas"
    private File selectExcelFileFromDataFolder(Stage primaryStage) {
        File dir = new File("datas");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx"));
        if (files == null || files.length == 0) {
            showAlert("Keine Dateien", "Es wurden keine Excel-Dateien im Ordner 'datas' gefunden.");
            return null;
        }

        ChoiceDialog<File> dialog = new ChoiceDialog<>(files[0], files);
        dialog.setTitle("Datei auswählen");
        dialog.setHeaderText("Wählen Sie eine Datei aus der Liste");
        dialog.setContentText("Excel-Datei:");
        Optional<File> result = dialog.showAndWait();
        return result.orElse(null);
    }

    // Erstellt die Szene für die Team-Eingabefelder
    private Scene createTeamScene(Stage primaryStage) {
        GridPane grid = createGridPane();

        // Eingabefelder für Team
        TextField laufField = new TextField();
        TextField wievielterLaufField = new TextField();
        TextField jahrField = new TextField();
        TextField einheitField = new TextField();
        TextField teilnehmerzahlField = new TextField();
        TextField teamwertungField = new TextField();
        TextField platzField = new TextField();
        TextField gesamtzeitField = new TextField();
        TextField bester1Field = new TextField();
        TextField bester2Field = new TextField();
        TextField bester3Field = new TextField();
        TextField ortDatumField = new TextField();

        // Hinzufügen der Team-Eingabefelder zum Grid
        addTeamFieldsToGrid(grid, laufField, wievielterLaufField, jahrField, einheitField, teilnehmerzahlField, teamwertungField, platzField, gesamtzeitField, bester1Field, bester2Field, bester3Field, ortDatumField);

        // Buttons
        Button saveButton = new Button("Speichern");
        Button searchButton = new Button("Suchen");
        Button newExcelButton = new Button("Neue Excel-Datei");
        Button switchToPersonButton = new Button("Zurück");

        // Hinzufügen der Buttons zum Grid
        HBox buttonBox = new HBox(10, saveButton, searchButton, newExcelButton, switchToPersonButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 12, 2, 1);

        // Event-Handler
        saveButton.setOnAction(e -> {
            Team team = new Team(laufField.getText(), wievielterLaufField.getText(), jahrField.getText(), einheitField.getText(), teilnehmerzahlField.getText(), teamwertungField.getText(), platzField.getText(), gesamtzeitField.getText(), bester1Field.getText(), bester2Field.getText(), bester3Field.getText(), ortDatumField.getText());
            ExcelUtils.saveDataToExcel(team);
            clearTeamFields(laufField, wievielterLaufField, jahrField, einheitField, teilnehmerzahlField, teamwertungField, platzField, gesamtzeitField, bester1Field, bester2Field, bester3Field, ortDatumField);
        });

        searchButton.setOnAction(e -> {
            File file = selectExcelFileFromDataFolder(primaryStage);
            if (file != null) {
                String searchTerm = showSearchDialog(primaryStage);
                List<String> results = ExcelUtils.searchDataInExcel(file, searchTerm);
                if (results != null && !results.isEmpty()) {
                    showSearchResults(primaryStage, results);
                } else {
                    showAlert("Suchergebnis", "Keine Daten gefunden.");
                }
            }
        });

        newExcelButton.setOnAction(e -> {
            ExcelUtils.createNewExcelFile("Team");
        });

        switchToPersonButton.setOnAction(e -> {
            primaryStage.setScene(createPersonScene(primaryStage));
        });

        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add("style.css");
        return scene;
    }

    // Hinzufügen der Team-Eingabefelder zum GridPane
    private void addTeamFieldsToGrid(GridPane grid, TextField laufField, TextField wievielterLaufField, TextField jahrField, TextField einheitField, TextField teilnehmerzahlField, TextField teamwertungField, TextField platzField, TextField gesamtzeitField, TextField bester1Field, TextField bester2Field, TextField bester3Field, TextField ortDatumField) {
        grid.add(new Label("Lauf:"), 0, 0);
        grid.add(laufField, 1, 0);
        grid.add(new Label("Wievielter Lauf:"), 0, 1);
        grid.add(wievielterLaufField, 1, 1);
        grid.add(new Label("Jahr:"), 0, 2);
        grid.add(jahrField, 1, 2);
        grid.add(new Label("Einheit:"), 0, 3);
        grid.add(einheitField, 1, 3);
        grid.add(new Label("Teilnehmerzahl:"), 0, 4);
        grid.add(teilnehmerzahlField, 1, 4);
        grid.add(new Label("Teamwertung:"), 0, 5);
        grid.add(teamwertungField, 1, 5);
        grid.add(new Label("Platz:"), 0, 6);
        grid.add(platzField, 1, 6);
        grid.add(new Label("Gesamtzeit:"), 0, 7);
        grid.add(gesamtzeitField, 1, 7);
        grid.add(new Label("1. Bester:"), 0, 8);
        grid.add(bester1Field, 1, 8);
        grid.add(new Label("2. Bester:"), 0, 9);
        grid.add(bester2Field, 1, 9);
        grid.add(new Label("3. Bester:"), 0, 10);
        grid.add(bester3Field, 1, 10);
        grid.add(new Label("Ort und Datum:"), 0, 11);
        grid.add(ortDatumField, 1, 11);
    }

    // Leeren der Team-Eingabefelder
    private void clearTeamFields(TextField laufField, TextField wievielterLaufField, TextField jahrField, TextField einheitField, TextField teilnehmerzahlField, TextField teamwertungField, TextField platzField, TextField gesamtzeitField, TextField bester1Field, TextField bester2Field, TextField bester3Field, TextField ortDatumField) {
        laufField.clear();
        wievielterLaufField.clear();
        jahrField.clear();
        einheitField.clear();
        teilnehmerzahlField.clear();
        teamwertungField.clear();
        platzField.clear();
        gesamtzeitField.clear();
        bester1Field.clear();
        bester2Field.clear();
        bester3Field.clear();
        ortDatumField.clear();
    }

    // Erstellt die Szene für die Person-Eingabefelder
    private Scene createPersonScene(Stage primaryStage) {
        GridPane grid = createGridPane();

        // Eingabefelder für Person
        TextField laufField = new TextField();
        TextField wievielterLaufField = new TextField();
        TextField jahrField = new TextField();
        TextField dienstgradField = new TextField();
        TextField nameVornameField = new TextField();
        TextField einheitField = new TextField();
        TextField platzField = new TextField();
        TextField laufstreckeField = new TextField();
        TextField altersklasseJahrgangField = new TextField();
        TextField zeitField = new TextField();
        TextField ortDatumField = new TextField();

        // Hinzufügen der Person-Eingabefelder zum Grid
        addPersonFieldsToGrid(grid, laufField, wievielterLaufField, jahrField, dienstgradField, nameVornameField, einheitField, platzField, laufstreckeField, altersklasseJahrgangField, zeitField, ortDatumField);

        // Buttons
        Button saveButton = new Button("Speichern");
        Button searchButton = new Button("Suchen");
        Button newExcelButton = new Button("Neue Excel-Datei");
        Button switchToTeamButton = new Button("Mannschaftsurkunden");

        // Hinzufügen der Buttons zum Grid
        HBox buttonBox = new HBox(10, saveButton, searchButton, newExcelButton, switchToTeamButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 11, 2, 1);

        // Event-Handler
        saveButton.setOnAction(e -> {
            Person person = new Person(laufField.getText(), wievielterLaufField.getText(), jahrField.getText(), dienstgradField.getText(), nameVornameField.getText(), einheitField.getText(), platzField.getText(), laufstreckeField.getText(), altersklasseJahrgangField.getText(), zeitField.getText(), ortDatumField.getText());
            ExcelUtils.saveDataToExcel(person);
            clearPersonFields(laufField, wievielterLaufField, jahrField, dienstgradField, nameVornameField, einheitField, platzField, laufstreckeField, altersklasseJahrgangField, zeitField, ortDatumField);
        });

        searchButton.setOnAction(e -> {
            File file = selectExcelFileFromDataFolder(primaryStage);
            if (file != null) {
                String searchTerm = showSearchDialog(primaryStage);
                List<String> results = ExcelUtils.searchDataInExcel(file, searchTerm);
                if (results != null && !results.isEmpty()) {
                    showSearchResults(primaryStage, results);
                } else {
                    showAlert("Suchergebnis", "Keine Daten gefunden.");
                }
            }
        });

        newExcelButton.setOnAction(e -> {
            ExcelUtils.createNewExcelFile("Person");
        });

        switchToTeamButton.setOnAction(e -> {
            primaryStage.setScene(createTeamScene(primaryStage));
        });

        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add("style.css");
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
