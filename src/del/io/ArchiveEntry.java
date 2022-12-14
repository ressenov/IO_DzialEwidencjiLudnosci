package del.io;

import java.time.LocalDate;

public class ArchiveEntry {

    private int id;
    private Person oldData;
    private Person newData;
    private LocalDate dateOfChange;
    private String personResponsible;
    private String type;

    public ArchiveEntry(Person oldData, Person newData, LocalDate dateOfChange,
                                  String personResponsible, String type) {
        this.id = DatabaseManager.getLatestID() + 1;
        this.oldData = oldData;
        this.newData = newData;
        this.dateOfChange = dateOfChange;
        this.personResponsible = personResponsible;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Person getOldData() {
        return oldData;
    }

    public Person getNewData() {
        return newData;
    }

    public LocalDate getDateOfChange() {
        return dateOfChange;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public String getType() {
        return type;
    }



}
