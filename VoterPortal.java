/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxprojects;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VoterPortal extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Voter Portal");
        showFirstPage();
    }

    // Slide 1: Name and DOB
    private void showFirstPage() {
        Label title = new Label("Voter Portal");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Enter your name:");
        TextField nameField = new TextField();

        Label dobLabel = new Label("Enter DOB:");

        ComboBox<String> dayBox = new ComboBox<>();
        for (int i = 1; i <= 31; i++) dayBox.getItems().add(String.valueOf(i));

        ComboBox<String> monthBox = new ComboBox<>();
        monthBox.getItems().addAll("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        ComboBox<String> yearBox = new ComboBox<>();
        for (int i = 1950; i <= 2025; i++) yearBox.getItems().add(String.valueOf(i));

        HBox dobRow = new HBox(10, dayBox, monthBox, yearBox);
        dobRow.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String selectedYear = yearBox.getValue();
            if (selectedYear == null) {
                showAlert("Please select your birth year.");
                return;
            }

            int birthYear = Integer.parseInt(selectedYear);
            int currentYear = java.time.Year.now().getValue();
            int age = currentYear - birthYear;

            if (age < 18) {
                showAlert("You are not eligible to vote. Minimum age is 18.");
            } else {
                showSecondPage();
            }
        });

        VBox layout = new VBox(15, title, nameLabel, nameField, dobLabel, dobRow, submitBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 350));
        primaryStage.show();
    }

    // Slide 2: Voter ID Entry
    private void showSecondPage() {
        Label eligibilityLabel = new Label("You are eligible for voting");
        eligibilityLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label voterIdLabel = new Label("Enter your Voter ID:");
        TextField voterIdField = new TextField();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> showThirdPage());

        VBox layout = new VBox(15, eligibilityLabel, voterIdLabel, voterIdField, submitBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 250));
    }

    // Slide 3: Vote Selection
    private void showThirdPage() {
        Label voteLabel = new Label("Select Your Vote");
        voteLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ToggleGroup partyGroup = new ToggleGroup();

        RadioButton tvk = new RadioButton("TVK");
        RadioButton dmk = new RadioButton("DMK");
        RadioButton admk = new RadioButton("ADMK");
        RadioButton ntk = new RadioButton("NTK");
        RadioButton bjp = new RadioButton("BJP");
        RadioButton nato = new RadioButton("NOTA");

        tvk.setToggleGroup(partyGroup);
        dmk.setToggleGroup(partyGroup);
        admk.setToggleGroup(partyGroup);
        ntk.setToggleGroup(partyGroup);
        bjp.setToggleGroup(partyGroup);
        nato.setToggleGroup(partyGroup);

        VBox partyBox = new VBox(10, tvk, dmk, admk, ntk, bjp, nato);
        partyBox.setAlignment(Pos.CENTER);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction(e -> showFinalPage());

        VBox layout = new VBox(20, voteLabel, partyBox, confirmBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 350));
    }

    // Slide 4: Confirmation
    private void showFinalPage() {
        Label successLabel = new Label("You have successfully voted!");
        successLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20, successLabel, closeBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 200));
    }

    // Alert helper
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Eligibility Check");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
