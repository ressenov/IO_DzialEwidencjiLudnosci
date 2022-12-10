package del.io;

public class ArchiveEntry {

    protected Person oldData;
    protected Person newData;
    protected String dateOfChange;
    protected String personResponsible;
    protected String type;

    public ArchiveEntry(Person oldData, Person newData, String dateOfChange,
                                  String personResponsible, String type) {
        this.oldData = oldData;
        this.newData = newData;
        this.dateOfChange = dateOfChange;
        this.personResponsible = personResponsible;
        this.type = type;
    }

    public Person getOldData() {
        return oldData;
    }

    public Person getNewData() {
        return newData;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public String getType() {
        return type;
    }

}
