package io;

import Model.Score;
import Model.UserState;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


public class Serializer {
    /**
     * Main program.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        UserState userState = new UserState(new Score(44, 22));
        try {
            Serializer configFile = new Serializer();
            configFile.save(userState, "config.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a list of Items in a xml file.
     *
     * @param userState  an element to write
     * @param configFile XML file to write
     */
    public void save(UserState userState, String configFile)
            throws JAXBException {

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(UserState.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to File
        m.marshal(userState, new File(configFile));
    }
}
