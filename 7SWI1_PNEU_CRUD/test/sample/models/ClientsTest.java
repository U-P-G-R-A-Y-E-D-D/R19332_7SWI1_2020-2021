package sample.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientsTest {

    @Test
    public void ConstructorClients(){
        String id = "1";
        String firstname = "Jan";
        String lastname = "Sonnek";
        String phone = "732876556";
        String time = "13:00";
        String date = "28.05.2021";
        String type = "ŠKODA KAROQ";
        String notes = "výměna oleje + přezutí";

        Clients clients = new Clients(id, firstname,lastname,phone,time,date,type,notes);

        assertEquals(id, clients.getId());
        assertEquals(firstname, clients.getFirstname());
        assertEquals(lastname, clients.getLastname());
        assertEquals(phone, clients.getPhone());
        assertEquals(time, clients.getTime());
        assertEquals(date, clients.getDate());
        assertEquals(type, clients.getType());
        assertEquals(notes, clients.getNotes());

    }

}