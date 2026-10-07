package com.example.hellofx;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {
    @Override
    public void start(Stage stage) {TextField nameField = new TextField();
        nameField.setPromptText("Enter customer name");

        ComboBox<String> provinceBox = new ComboBox<>();

        provinceBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );

        provinceBox.setPromptText("Select province");

        Button addButton = new Button("Add");
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");
        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String province = provinceBox.getValue();

            if (name.isEmpty()) {
                message.setText("Please enter a name.");
            } else if (province == null) {
                message.setText("Please select a province.");
            } else {
                customers.add(new Customer(name, province));
                message.setText("");
                nameField.clear();
                provinceBox.setValue(null);
            }
        });
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());

        TableColumn<Customer, String> provCol = new TableColumn<>("Province");
        provCol.setCellValueFactory(c -> c.getValue().provinceProperty());

        table.getColumns().addAll(nameCol, provCol);
        table.setItems(customers);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                message.setText("Select a customer in the table first.");
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete " + selected.getName() + "?", ButtonType.YES, ButtonType.NO);
            confirm.showAndWait().ifPresent(answer -> {
                if (answer == ButtonType.YES) {
                    customers.remove(selected);
                    message.setText("");
                }
            });
        });
        layout.getChildren().addAll(nameField, provinceBox, addButton, message, table, deleteButton);

        Scene scene = new Scene(layout, 500, 450);
        stage.setTitle("My First JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}