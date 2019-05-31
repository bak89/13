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
        // index in which the score is inserted in the array to keep it sorted
        int index = Collections.binarySearch(records, score,Collections.reverseOrder());
        if (index < 0) {//if the element does not exist
            index = -index - 1;//return index(-(insertion point)-1)
        }
        records.add(index, score);
        while (records.size() > Settings.MAX_RECORDS) {
            records.remove(records.size() - 1);
        }
        return index;
    }


}
