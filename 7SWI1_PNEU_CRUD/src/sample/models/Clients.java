package sample.models;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

//Ondřej Zenam

public class Clients {

    private SimpleStringProperty id;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty phone;
    private SimpleStringProperty time;
    private SimpleStringProperty date;
    private SimpleStringProperty type;
    private SimpleStringProperty notes;

    public Clients(String id, String firstname, String lastname, String phone, String time, String  date,String  type, String  notes) {
        this.id = new SimpleStringProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phone = new SimpleStringProperty(phone);
        this.time =new SimpleStringProperty(time);
        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
        this.notes = new SimpleStringProperty(notes);
    }




    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(id, clients.id) &&
                Objects.equals(firstname, clients.firstname) &&
                Objects.equals(lastname, clients.lastname) &&
                Objects.equals(phone, clients.phone) &&
                Objects.equals(time, clients.time) &&
                Objects.equals(date, clients.date) &&
                Objects.equals(type, clients.type) &&
                Objects.equals(notes, clients.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, phone, time, date, type, notes);
    }

    @Override
    public String toString() {
        return "Clients{" +

                " firstname=" + getFirstname() +
               ", lastname=" + getLastname() +
                ", phone=" + getPhone() +
                ", time=" + getTime() +
                ", date=" + getDate() +
                ", type=" + getType() +
                ", notes=" + getNotes() +
                '}';
        //return String.format("%s %s %s %s %s %s %s",
          //       getFirstname(), getLastname(), getPhone(), getTime(),getDate(),getType(),getNotes());
    }
}
