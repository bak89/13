package io;

import Model.UserState;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;

public class Deserializer {


    public static void main(String[] args) {
        Deserializer read = new Deserializer();
        try {
            UserState userState = read.read("config.xml");
            System.out.println(userState.getRecord().getLevel());
            System.out.println(userState.getRecord().getMoves());

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    //quando scrivo delelgo la classe di scrivere se stessa
    //quando leggo devo specificare la classe che si legge da sola
    public UserState read(String configFile) throws IOException, JAXBException {

        JAXBContext context = JAXBContext.newInstance(UserState.class);

        // Output result from XML File
        Unmarshaller um = context.createUnmarshaller();
        return (UserState) um.unmarshal(new FileReader(configFile));
    }
}
