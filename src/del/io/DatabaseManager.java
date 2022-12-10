package del.io;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private List<Person> database;
    private List<ArchiveEntry> archive;

    public DatabaseManager() {
        this.database = new ArrayList<>();
        this.archive = new ArrayList<>();
    }

    public List<ArchiveEntry> getArchive() {
        return archive;
    }

    public void addNewPerson(Person newPerson){

    }

    public void editPersonalData(String PESEL, Person newData){

    }

    public void printDatabase(){

    }

    private Person searchPerson(String PESEL){
    //return;
    }

    private String generatePESEL(Person person){
        return "";
    }

    public void printArchive(String fromDate, String toDate){

    }

    public void addToArchive (ArchiveEntry entry){
        archive.add(entry);
    }

}
