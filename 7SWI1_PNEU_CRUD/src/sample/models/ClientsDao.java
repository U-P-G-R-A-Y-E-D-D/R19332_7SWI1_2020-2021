package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


public class ClientsDao {
    private Alerts alerts = new Alerts();
    ObservableList<Clients> oblist = FXCollections.observableArrayList();

    private Statement statement;
    public ObservableList<Clients> getClientsList(){
        return oblist;
    }

    public ObservableList<Clients> fillClients()throws Exception{
        statement = DB.DBConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from customers");
        while(resultSet.next()){
            oblist.add(new Clients(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
        }
        return oblist;

    }

    public void insert(Clients clients) throws Exception{
        try{
            String firstname = clients.getFirstname();
            String lastname = clients.getLastname();
            String phone = clients.getPhone();
            String date = clients.getDate();
            String time = clients.getTime();
            String type = clients.getType();
            String notes = clients.getNotes();
            System.out.println(firstname+"   " +lastname);

            statement = DB.DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customers where date = '"+clients.getDate()+"' and time = '"+clients.getTime()+"'");
            int ct = 0;
            while (resultSet.next()){
                ct++;
            }
            if(ct>1){

                alerts.checkTimeAlert("Pro tento čas je již obsazen. Vyberte jiný");
                throw new SQLException("Duplicita" );

            } else
            {


                String query = "insert into customers values(null,'"+firstname+"','"+lastname+"','"+phone+"','"+time+"','"+date+"','"+type+"','"+notes+"')" ;
                System.out.println(query);

                alerts.insertAlert("Vložení záznamu");
                statement.executeUpdate(query);
                statement.close();
            }


        } catch(Exception e)
        {
            String errorMessage = "Error : ";
            System.out.println(errorMessage + e.toString());
        }


    }



    public void edit(Clients clients)throws Exception{
        try{
            String firstname = clients.getFirstname();
            String lastname = clients.getLastname();
            String phone = clients.getPhone();
            String date = clients.getDate();
            String time = clients.getTime();
            String type = clients.getType();
            String notes = clients.getNotes();


            statement = DB.DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customers where date = '"+clients.getDate()+"' and time = '"+clients.getTime()+"'");
            int ct = 0;
            while (resultSet.next()){
                ct++;
            }
            if(ct>1){

                alerts.checkTimeAlert("Pro tento čas je již obsazen. Vyberte jiný");
                throw new SQLException("Duplicate" + time );

            }
            else {
                statement = DB.DBConnection.getConnection().createStatement();
                String query = "update customers set firstname =  '"+firstname+"',lastname = '"+lastname+"', phone ='"+phone+"', time ='"+time+"',date ='"+date+"',  type ='"+type+"', notes ='"+notes+"' where id = '"+clients.getId()+"'   ";

                alerts.insertAlert("Záznam byl úspěšně vložen");

                statement.executeUpdate(query);
                statement.close();
                System.out.println(query);

            }


        } catch(Exception e)
        {
            String errorMessage = "Error : ";
            System.out.println(errorMessage + e.toString());

        }

    }

    @FXML
    public void delete(Clients clients) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Smazat");
        alert.setHeaderText(null);
        alert.setContentText("Přejete si záznam vymazat?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get()==ButtonType.OK){
            statement = DB.DBConnection.getConnection().createStatement();
            String query = "delete from customers WHERE id = '"+clients.getId()+"' ";
            statement.executeUpdate(query);
            statement.close();
            System.out.println(query);
        }


    }






}
