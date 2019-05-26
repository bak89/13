package WriteReadScore;
/*
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadScore {
    private static final String NAME = "name";
    private static final String LEVEL = "level";
    private static final String SCORE = "score";

    public static void main(String[] args) {
        ReadScore read = new ReadScore();
        List<Score> readConfig;
        try {
            readConfig = read.readConfig("config.xml");
            for (Score score : readConfig) {
                System.out.println(score);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public List<Score> readConfig(String configFile) throws XMLStreamException,IOException,NumberFormatException{
        List<Score> scores= new ArrayList<Score>();
        try (InputStream in = new FileInputStream(configFile)){

            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            // Setup a new eventReader
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // prepare a Score reference
            Score score = null;

            // loop over all events
            while (eventReader.hasNext()){

                // get the next event
                XMLEvent event = eventReader.nextEvent();

                // process only events of interest
                if (event.isStartElement()){

                    // convert to Start element
                    StartElement startElement = event.asStartElement();

                    // if we have a score element, we create a new score
                    if(startElement.getName().getLocalPart().equals(SCORE)){
                        score = new Score();


                        // We read the attributes from this tag and add
                        // the date attribute to our object
                        // get an iterator to iterate over
                        // the attributes list
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()){
                            // get one after the other
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(NAME)){
                                // set the field in score
                                score.setName(attribute.getValue());
                            }
                        }
                       // else{

                        }


                    }
                }
            }

        }
}



}*/
