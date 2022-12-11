package del.io;

import java.time.LocalDate;
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
        newPerson.setPESEL(generatePESEL(newPerson));
        database.add(newPerson);
    }

    public void editPersonalData(Person toBeChanged, Person newData){
        toBeChanged.setFirstName(newData.getFirstName());
        toBeChanged.setSecondName(newData.getSecondName());
        toBeChanged.setSurname(newData.getSurname());
        toBeChanged.setResidenceAddress(newData.getResidenceAddress());
        toBeChanged.setRegisteredAddress(newData.getRegisteredAddress());

    }

    public Person searchPerson(String PESEL){
        for (Person person : database) {
            if(person.getPESEL().equals(PESEL)) return person;
        }
        return null;
    }

    public void printDatabase(){
        for (Person entry : database) {
            System.out.println(entry.getFirstName() + " " + entry.getSecondName() + " " +  entry.getSurname() + " " + entry.getPESEL());
        }
    }


    public String generatePESEL(Person person){
        String PESEL = "";
        LocalDate dateOfBirth = person.getDateOfBirth();
        String year = Integer.toString(dateOfBirth.getYear());
        year = year.substring(year.length() - 2);
        PESEL += year;

        String month;
        if (dateOfBirth.getMonth().getValue() >= 10){
            month = "3" + Integer.toString(dateOfBirth.getMonth().getValue() % 10);
        }
        else {
            month = "2" + Integer.toString(dateOfBirth.getMonth().getValue());
        }

        PESEL += month;

        String day = Integer.toString(dateOfBirth.getDayOfMonth());
        if (dateOfBirth.getDayOfMonth() < 10) PESEL += "0";

        PESEL += day;

        int index = 0;
        for (Person entry : database) {
            if(entry.getDateOfBirth().equals(dateOfBirth)) index++;
        }
        String ending = String.format("%05d", index);
        PESEL += ending;
        return PESEL;
    }

    public void printArchive(String fromDate, String toDate){
        for (ArchiveEntry entry : archive) {
            System.out.println(entry.newData.getPESEL() +" " + entry.getDateOfChange() + " " + entry.getPersonResponsible());
        }

    }

    public void addToArchive (ArchiveEntry entry){
        archive.add(entry);
    }

    public void printPersonalData (Person person){
        System.out.println("Imie: " + person.getFirstName() +
                "\nDrugie imie: " + person.getSecondName() +
                "\nNazwisko: " + person.getSurname() +
                "\nPlec: " + person.getGender() +
                "\nPESEL: " + person.getPESEL() +
                "\nData urodzenia: " + person.getDateOfBirth() +
                "\nMiejsce urodzenia: " + person.getPlaceOfBirth() +
                "\nImie matki: " + person.getMothersName() +
                "\nImie ojca: " + person.getFathersName() +
                "\nAdres zamieszkania:\nNumer domu: " + person.getResidenceAddress().getHouseNumber() +
                "\nNumer lokalu: " + person.getResidenceAddress().getApartamentNumber() +
                "\nKod pocztowy: " + person.getResidenceAddress().getPostalCode() +
                "\nMiejscowosc: " + person.getResidenceAddress().getTown() +
                "\nAdres zameldowania:\nNumer domu: " + person.getRegisteredAddress().getHouseNumber() +
                "\nNumer lokalu: " + person.getRegisteredAddress().getApartamentNumber() +
                "\nKod pocztowy: " + person.getRegisteredAddress().getPostalCode() +
                "\nMiejscowosc: " + person.getRegisteredAddress().getTown());
                if(!(person.getDateOfDeath() == null)) System.out.println("Data smierci:" + person.getDateOfDeath());
    }

}
