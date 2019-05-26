package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class UserState {

    @XmlElementWrapper
    @XmlElement
    private List<Score> records = new ArrayList<>();

    @XmlElement
    private Settings settings = new Settings();

    public UserState(Score record) {
        records.add(record);
    }

    public UserState() {
    }

    public Score getRecord() {
        return records.get(0);
    }

    public List<Score> getRecords() {
        return records;
    }

    public Settings getSettings() {
        return settings;
    }

    public int updateRecord(Score score) {
        int index = Collections.binarySearch(records, score,Collections.reverseOrder());//indice in cui è inserito lo score nell'array per mantenerlo ordinato
        if (index < 0) {//se non esiste l'elemento
            index = -index - 1;//ritorna index(-(insertion point)-1)
        }
        records.add(index, score);
        while (records.size() > Settings.MAX_RECORDS) {
            records.remove(records.size() - 1);
        }
        return index;
    }


}
