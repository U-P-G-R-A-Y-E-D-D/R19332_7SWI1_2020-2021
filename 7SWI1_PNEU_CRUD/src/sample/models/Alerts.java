package sample.models;

import javafx.scene.control.Alert;

public class Alerts {

    Alert alert;

    public void problemAlert(String headerText){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void phoneAlert(String headerText){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kontrola tel. čísla");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void lastnameAlert(String headerText){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kontrola příjmení");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void firstnameAlert(String headerText){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kontrola jména");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void deleteAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Odstranění záznamu");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void insertAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vložení záznamu");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void checkTimeAlert(String headerText){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Kontola času objednání");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void carAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava vozu");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void notesAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava poznámek");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void dateAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava data");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void timeAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava času");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void lnaneAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava příjmení");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void fnaneAlert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava jména");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void phone1Alert(String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úprava telefonu");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
