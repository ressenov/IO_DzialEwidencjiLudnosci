package del.io;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        DatabaseManager dbM = new DatabaseManager();
        Address testAddress = new Address("ul", "1", "-", "12-345", "wroclaw");
        Person testPerson = new Person("imie", "" ,"nazwisko", 'M',
                                        LocalDate.of(2000,10,7), "miasto", "tata", "mama",
                                        testAddress, testAddress);
        testPerson.setPESEL(dbM.generatePESEL(testPerson));
        dbM.addNewPerson(testPerson);

        Person testPerson2 = new Person("imieee", "" ,"nazwiskoooo", 'M',
                LocalDate.of(2000,10,7), "", "tata", "mama",
                testAddress, testAddress);
        testPerson2.setDateOfDeath(LocalDate.of(2022,12,13));
        ArchiveEntry testEntry1 = new ArchiveEntry(testPerson,testPerson2, LocalDate.of(2022,12,13),"pr", "DEATH");
        dbM.addToArchive(testEntry1);

        testPerson2.setDateOfDeath(LocalDate.of(2032,12,13));
        ArchiveEntry testEntry2 = new ArchiveEntry(testPerson,testPerson2, LocalDate.of(2022,12,13),"pr", "DEATH");
        dbM.addToArchive(testEntry2);

        Report testReport = new Report(LocalDate.of(2021,12,14), LocalDate.of(2022,12,14),LocalDate.of(2022,12,13));
        System.out.println(testReport.getNumberOfBirths());
        System.out.println(testReport.getNumberOfDeaths());
        System.out.println(testReport.getBirthToDeathRatio());
        System.out.println(testReport.getMeanDeathAge());

//        testPerson2.setPESEL(dbM.generatePESEL(testPerson2));
//        dbM.addNewPerson(testPerson2);

//        Person testPerson3 = new Person("imie", "" ,"nazwisko", 'M',
//                LocalDate.of(2000,10,7), "miasto", "tata", "mama",
//                testAddress, testAddress);
//        testPerson3.setPESEL(dbM.generatePESEL(testPerson3));
//        dbM.addNewPerson(testPerson3);
//
//        dbM.printDatabase();
//
//        dbM.printPersonalData(dbM.searchPerson("00300700000"));
        dbM.printDatabase();
        dbM.editPersonalData(dbM.searchPerson("00300700000"), testPerson2);
        dbM.printDatabase();
    }

    public void showDatabase(){

    }

    public void editPersonalData(){

    }

    public void generateReport(){

    }

    public void showReports(){

    }

}
