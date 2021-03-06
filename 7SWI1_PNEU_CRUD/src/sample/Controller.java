package sample;

import DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Window;
import sample.models.Alerts;
import sample.models.Clients;
import sample.models.ClientsDao;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    private Alerts alerts = new Alerts();
    @FXML
    private TableView<Clients> tableView;
    @FXML
    private TableColumn<Clients, String> idColumn;
    @FXML
    private TableColumn<Clients, String> firstnameColumn;
    @FXML
    private TableColumn<Clients, String> lastnameColumn;
    @FXML
    private TableColumn<Clients, String> phoneColumn;
    @FXML
    private TableColumn<Clients, String> dateOfOrderColumn;
    @FXML
    private TableColumn<Clients, String> timeColumn;
    @FXML
    private TableColumn<Clients, String> typeColumn;
    @FXML
    private TableColumn<Clients, String> notesColumn;



    @FXML
    private TextField firstNameTxtField;

    @FXML
    private TextField lastNameTxtField;

    @FXML
    private TextField phoneTxtField;

    @FXML
    private DatePicker datePicker;

    @FXML
    //private TextField timeTxtField;
    private ChoiceBox<String> cbTimePickerEdit;

    @FXML
    private TextField typeTxtField;

    @FXML
    private TextArea notesTxtField;

    @FXML
    private MenuItem  deleteClients;

    @FXML
    private TextField filterField;


    //Ond??ej Zeman
    private final ObservableList<String> timeSelectorList = FXCollections.observableArrayList("08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00");


    ObservableList<Clients> oblist = FXCollections.observableArrayList();
    private Statement statement;

    private String id;
    private String firstname;
    private String lastname;
    private String phone;
    private String date;
    private String time;
    private String type;
    private String notes;
    private ClientsDao dao = new ClientsDao();




    //---------------------------------VKL??D??N?? A KONTROLA DAT DO DATAB??ZE----------------------------------------------------------------------------------------------
    @FXML
    public void create(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Pole nesm?? b??t pr??zdn??");
        try {
            if (isValidInput(event)) {
                firstname = firstNameTxtField.getText();
                lastname = lastNameTxtField.getText();
                phone = phoneTxtField.getText();
                date = datePicker.getValue().toString();
                //time = timeTxtField.getText();
                time = cbTimePickerEdit.getValue();
                type = typeTxtField.getText();
                notes = notesTxtField.getText();
                dao.insert(new Clients(id, firstname, lastname, phone,  time, date,  type, notes));

                firstNameTxtField.clear();
                lastNameTxtField.clear();
                datePicker.getEditor().clear();
                datePicker.setValue(null);
                phoneTxtField.clear();
                //timeTxtField.clear();
                cbTimePickerEdit.getValue();
                typeTxtField.clear();
                notesTxtField.setText("");

            }


        } catch (Exception e) {

            alert.show();
        }
        updateData();
    }





    public void initialize(URL location, ResourceBundle resources) {

        cbTimePickerEdit.setItems(timeSelectorList);
        showTable();
        //tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setValueFromTable();
              try {
            search_clients();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateData() throws Exception {
        dao.getClientsList().clear();
        dao.fillClients();
    }

    //---------------------------------AKTUALIZACE TABULKY----------------------------------------------------------------------------------------------

    @FXML
    public void refresh(ActionEvent event) throws Exception {
        dao.getClientsList().clear();
        dao.fillClients();
    }



    //---------------------------------ZOBRAZEN?? DAT DO TABULKY----------------------------------------------------------------------------------------------
    public void showTable() {
        tableView.setEditable(true);
        firstnameColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        lastnameColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        phoneColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        dateOfOrderColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        timeColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());
        notesColumn.setCellFactory(TextFieldTableCell.<Clients>forTableColumn());


        try {
            dao.fillClients();
        } catch (Exception e) {
            alerts.problemAlert("Nepoda??iolo se p??ipojit k DB");
        }

        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("lastname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("phone"));
        dateOfOrderColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("type"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("notes"));
        tableView.setItems(dao.getClientsList());
    }

    private boolean isValidInput(ActionEvent event){
        Boolean validInput = true;

        if(firstNameTxtField == null || firstNameTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyFirstname = new Alert(Alert.AlertType.WARNING,"Varov??n??", ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstname.setContentText("Jm??no je pr??zn??!");
            emptyFirstname.initModality(Modality.APPLICATION_MODAL);
            emptyFirstname.initOwner(window);
            emptyFirstname.show();
            if(emptyFirstname.getResult() == ButtonType.OK){
                emptyFirstname.close();
                firstNameTxtField.requestFocus();
            }
        }

        if(lastNameTxtField == null || lastNameTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyLastname = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastname.setContentText("P????jmen?? je pr??zdn??!");
            emptyLastname.initModality(Modality.APPLICATION_MODAL);
            emptyLastname.initOwner(owner);
            emptyLastname.show();
            if(emptyLastname.getResult() == ButtonType.OK){
                emptyLastname.close();
                lastNameTxtField.requestFocus();
            }
        }

        if(lastnameColumn == null || lastnameColumn.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyLastname = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastname.setContentText("P????jmen?? je pr??zdn??!");
            emptyLastname.initModality(Modality.APPLICATION_MODAL);
            emptyLastname.initOwner(owner);
            emptyLastname.show();
            if(emptyLastname.getResult() == ButtonType.OK){
                emptyLastname.close();
            }
        }

        if(phoneTxtField == null || phoneTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyPhone = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyPhone.setContentText("Telefon je pr??zdn??!");
            emptyPhone.initModality(Modality.APPLICATION_MODAL);
            emptyPhone.initOwner(window);
            emptyPhone.show();
            if(emptyPhone.getResult() == ButtonType.OK){
                emptyPhone.close();
                phoneTxtField.requestFocus();
            }
        }


        if(datePicker == null || datePicker.getEditor().getText().trim().isEmpty()){
            validInput = false;
            Alert emptyDate = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyDate.setContentText("Datum je pr??zdn??!");
            emptyDate.initModality(Modality.APPLICATION_MODAL);
            emptyDate.initOwner(window);
            emptyDate.show();
            if(emptyDate.getResult() == ButtonType.OK){
                emptyDate.close();
                datePicker.requestFocus();
            }
        }

       /* if(timeTxtField == null || timeTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyTime = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyTime.setContentText("Datum je pr??zdn??!");
            emptyTime.initModality(Modality.APPLICATION_MODAL);
            emptyTime.initOwner(window);
            emptyTime.show();
            if(emptyTime.getResult() == ButtonType.OK){
                emptyTime.close();
                timeTxtField.requestFocus();
            }*/

        if(cbTimePickerEdit == null || cbTimePickerEdit.getValue().trim().isEmpty()){
            validInput = false;
            Alert emptyTime = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyTime.setContentText("Datum je pr??zdn??!");
            emptyTime.initModality(Modality.APPLICATION_MODAL);
            emptyTime.initOwner(window);
            emptyTime.show();
            if(emptyTime.getResult() == ButtonType.OK){
                emptyTime.close();
                cbTimePickerEdit.requestFocus();
            }

        }

        if(typeTxtField == null || typeTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyType = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyType.setContentText("Typ auta je pr??zdn??!");
            emptyType.initModality(Modality.APPLICATION_MODAL);
            emptyType.initOwner(window);
            emptyType.show();
            if(emptyType.getResult() == ButtonType.OK){
                emptyType.close();
                typeTxtField.requestFocus();
            }
        }

        if(notesTxtField == null || notesTxtField.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyNotes = new Alert(Alert.AlertType.WARNING,"Varov??n??",ButtonType.OK);
            Window window = ((Node) event.getTarget()).getScene().getWindow();
            emptyNotes.setContentText("Pozn??mky jsou pr??zdn??!");
            emptyNotes.initModality(Modality.APPLICATION_MODAL);
            emptyNotes.initOwner(window);
            emptyNotes.show();
            if(emptyNotes.getResult() == ButtonType.OK){
                emptyNotes.close();
                notesTxtField.requestFocus();
            }
        }
        return validInput;
    }



//---------------------------------??PRAVA DAT V DATAB??ZI----------------------------------------------------------------------------------------------

    public void onEditFirstName(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setFirstname(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getFirstname());
        alerts.fnaneAlert("Jm??no bylo upraveno.");
        dao.edit(tmp);


    }


    public void onEditPhone(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setPhone(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getPhone());
        alerts.phone1Alert("Telefon byl upraven.");
        dao.edit(tmp);
    }

    public void onEditLastName(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setLastname(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getLastname());
        alerts.lnaneAlert("P????jmen?? bylo upraveno.");
        dao.edit(tmp);

    }



    public void onEditTime(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setTime(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getTime());
        alerts.timeAlert("??as byl upraven.");
        dao.edit(tmp);
    }

    public void onEditDate(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setDate(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getDate());
        alerts.dateAlert("Datum bylo upraveno.");
        dao.edit(tmp);
    }

    public void onEditCar(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {



        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setType(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getType());
        alerts.carAlert("V??z byl upraven.");
        dao.edit(tmp);
    }

    public void onEditNotes(TableColumn.CellEditEvent<Clients, String> clientsStringCellEditEvent) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        tmp.setNotes(clientsStringCellEditEvent.getNewValue());
        System.out.println(tmp.getNotes());
        alerts.notesAlert("Pozn??mky byly upraveny.");
        dao.edit(tmp);
    }




    //---------------------------------ODSTRAN??N?? Z??ZNAMU----------------------------------------------------------------------------------------------
    public void delete (ActionEvent event) throws Exception {
        Clients tmp = tableView.getSelectionModel().getSelectedItem();
        dao.delete(tmp);
        updateData();
    }






    //---------------------------------ZAV??EN?? APLIKACE----------------------------------------------------------------------------------------------
    @FXML
    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }


    //---------------------------------VALIDACE-ALERT----------------------------------------------------------------------------------------------

    public boolean validFirstname(KeyEvent event) {
        String PATTERN = "[a-zA-Z ????????????????????????????????????????????.-]+";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher matcher = patt.matcher(firstNameTxtField.getText());
        if(matcher.find() && matcher.group().equals(firstNameTxtField.getText())){
            return true;

        }else{
            alerts.firstnameAlert("Zadejte jm??no ve  spr??vn??m tvaru");
            return false;
        }
    }


    public boolean validLastname(KeyEvent event) {
        String PATTERN = "[a-zA-Z ????????????????????????????????????????????.-]+";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher matcher = patt.matcher(lastNameTxtField.getText());
        if(matcher.find() && matcher.group().equals(lastNameTxtField.getText())){
            return true;

        }else{
           alerts.lastnameAlert("Zadejte spr??vn?? jm??no");
            return false;
        }
    }


    public boolean validPhone(KeyEvent event) {
        String PATTERN = "[0-9]{0,9}$+";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher matcher = patt.matcher(phoneTxtField.getText());
        if(matcher.find() && matcher.group().equals(phoneTxtField.getText())){
            return true;

        }else{
           alerts.phoneAlert("Zadejte pouze ????sla. Max. d??lka tel. ????sla je 9 ????sel.");
            return false;
        }
    }







    //-----------------------------------------------------------FILTROV??N?? Z??KAZN??K??---------------------------------------------------------------------------
    @FXML
    public void search_clients() throws Exception {

        oblist = dao.getClientsList();
        tableView.setItems(oblist);
        FilteredList<Clients> filteredData = new FilteredList<>(oblist, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filtr pro jm??no
                } else if (person.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filtr pro p????jmen??
                }else if (person.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filtr pro tel.????slu
                }
                else if (person.getTime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filtr pro ??as
                }
                else if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filtr pro datum
                }
                else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter pro typ auta
                }
                else if (person.getNotes().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;// Filter pro pozn??mky

                else
                    return false; // Data nebyla nalezena.
            });
        });
        SortedList<Clients> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    //ZEMAN
   private void setValueFromTable(){
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {

                Clients tmp = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

                firstNameTxtField.setText(tmp.getFirstname()+"");
                lastNameTxtField.setText(tmp.getLastname()+"");
                phoneTxtField.setText(tmp.getPhone());
                //timeTxtField.setText(tmp.getTime());
                cbTimePickerEdit.setValue(tmp.getTime());
                datePicker.getEditor().setText(tmp.getDate());
                typeTxtField.setText(tmp.getType());
                notesTxtField.setText(tmp.getNotes());

            }

        });
    }

    //ZEMAN
    @FXML
    private void clear(ActionEvent event){
        firstNameTxtField.clear();
        lastNameTxtField.clear();
        datePicker.getEditor().clear();
        datePicker.setValue(null);
        phoneTxtField.clear();
        // timeTxtField.clear();
        cbTimePickerEdit.setValue(null);
        typeTxtField.clear();
        notesTxtField.clear();
    }
































   //Ond??ej Zeman
    @FXML
    private void update(ActionEvent event) throws Exception {
        try{
            statement = DB.DBConnection.getConnection().createStatement();

            String query = "UPDATE customers SET firstname='"+firstNameTxtField.getText()+"',lastname='"+lastNameTxtField.getText()+"',phone='"+phoneTxtField.getText()+"',time='"+cbTimePickerEdit.getValue()+"',date='"+datePicker.getEditor().getText()+"',type='"+typeTxtField.getText()+"',notes='"+notesTxtField.getText()+"' WHERE phone='"+phoneTxtField.getText()+"'";


            alerts.insertAlert("Z??znam byl ??sp????n?? upraven");


            statement.executeUpdate(query);
            statement.close();
            System.out.println(query);
        }catch (Exception e){
            System.out.println(e);
        }
        updateData();
    }


}


