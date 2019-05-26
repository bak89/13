package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserState {

    @XmlElement
    private Score record;

    public UserState(Score record) {
        this.record = record;
    }

    public UserState() {
    }

    public Score getRecord() {
        return record;
    }

    public void updateRecord(Score score) {
        if (record.compareTo(score) < 0) {
            record = score;
        }
    }

}
