package application;

import dataElements.Address;
import dataElements.AuthenticationDocument;
import dataElements.Person;
import dataElements.Report;
import systemManagement.DatabaseManager;
import systemManagement.DocumentManager;

import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        boolean loop = true;
        while (loop) {
            System.out.println("1.Pokaz baze danych\n2.Zarzadzaj baza danych\n3.Wygeneruj raport\n4.Pokaz baze raportow\nDowlny klawisz aby wyjsc");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    showDatabase();
                    break;
                case 2:
                    editPersonalData();
                    break;
                case 3:
                    generateReport();
                    break;
                case 4:
                    showReports();
                    break;
                case 5:
                    loop = false;
            }
        }

    }

    public static void showDatabase(){
        DatabaseManager databaseManager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Cała baza danych\n2.Dane osoby z historia edycji");
        int choice = scanner.nextInt();
        if (choice == 1){
            databaseManager.printDatabase();
        }
        else if (choice == 2){
            System.out.println("Podaj numer PESEL osoby: ");
            String PESEL = scanner.nextLine();
            databaseManager.printPersonalData(databaseManager.searchPerson(PESEL));
        }
    }

    public static void editPersonalData(){
        DatabaseManager databaseManager = new DatabaseManager();
        DocumentManager documentManager = new DocumentManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1.Zglos zgon osoby\n2.Zmien dane osoby\n3.Urodzenie\n");
        int choice = scanner.nextInt();
        if (choice == 1){
            System.out.println("Podaj numer PESEL osoby, której zgon chcesz zarejestrowac:");
            String PESEL = scanner.nextLine();
            Person personToChange = databaseManager.searchPerson(PESEL);

            Person editedData = new Person(personToChange.getFirstName(), personToChange.getSecondName(),
                    personToChange.getSurname(), personToChange.getGender(),
                    personToChange.getDateOfBirth(), personToChange.getPlaceOfBirth(),
                    personToChange.getFathersName(), personToChange.getMothersName(),
                    personToChange.getResidenceAddress(), personToChange.getRegisteredAddress());

            System.out.println("\nWprowadz rok smierci:");
            int year = scanner.nextInt();
            System.out.println("Wprowadz miesiac smierci:");
            int month = scanner.nextInt();
            System.out.println("Wprowadz dzien smierci:");
            int day = scanner.nextInt();

            editedData.setDateOfDeath(LocalDate.of(year, month, day));

            System.out.println("\nWprowadz aktualny rok:");
            year = scanner.nextInt();
            System.out.println("Wprowadz aktualny miesiac:");
            month = scanner.nextInt();
            System.out.println("Wprowadz aktualny dzien:");
            day = scanner.nextInt();

            System.out.println("Wprowadz swoje imie i nazwisko");
            String employee = scanner.nextLine();

            AuthenticationDocument authenticationDocument = documentManager.generateAuthenticationDocument(personToChange, editedData, LocalDate.of(year, month, day), employee, "DEATH" );

            System.out.println("\nCzy petent zaakcepotwal zmiany?");
            boolean isAccepted = scanner.nextBoolean();

            if (isAccepted){
                databaseManager.editPersonalData(personToChange, editedData, LocalDate.of(year, month, day), employee, "DEATH");
                authenticationDocument.setAuthorized(true);
            }
            else  authenticationDocument.setAuthorized(false);

        }
        else if (choice == 2) {
            System.out.println("Podaj numer PESEL osoby, której dane chcesz edytować:");
            String PESEL = scanner.nextLine();
            Person personToChange = databaseManager.searchPerson(PESEL);

            System.out.println("Wprowadz nowe dane:\nImie:");
            String name = scanner.nextLine();
            System.out.println("Drugie imie:");
            String secondName = scanner.nextLine();
            System.out.println("Nazwisko:");
            String surname = scanner.nextLine();

            System.out.println("\nAdres zamieszkania\nUlica:");
            String resaStreet = scanner.nextLine();
            System.out.println("Numer domu:");
            String resaHouseNumber = scanner.nextLine();
            System.out.println("Numer mieszkania:");
            String resaApartamentNumber = scanner.nextLine();
            System.out.println("Kod pocztowy:");
            String resaPostalCode = scanner.nextLine();
            System.out.println("Miejscowosc:");
            String resaTown = scanner.nextLine();

            System.out.println("\nAdres zameldowania\nUlica:");
            String regaStreet = scanner.nextLine();
            System.out.println("Numer domu:");
            String regaHouseNumber = scanner.nextLine();
            System.out.println("Numer mieszkania:");
            String regaApartamentNumber = scanner.nextLine();
            System.out.println("Kod pocztowy:");
            String regaPostalCode = scanner.nextLine();
            System.out.println("Miejscowosc:");
            String regaTown = scanner.nextLine();

            Address registeredAddress = new Address(regaStreet, regaHouseNumber, regaApartamentNumber, regaPostalCode, regaTown);
            Address residenceAddress = new Address(resaStreet, resaHouseNumber, resaApartamentNumber, resaPostalCode, resaTown);

            Person editedData = new Person(name, secondName, surname, personToChange.getGender(),
                    personToChange.getDateOfBirth(), personToChange.getPlaceOfBirth(),
                    personToChange.getFathersName(), personToChange.getMothersName(),
                    residenceAddress, registeredAddress);

            System.out.println("\nWprowadz aktualny rok:");
            int year = scanner.nextInt();
            System.out.println("Wprowadz aktualny miesiac:");
            int month = scanner.nextInt();
            System.out.println("Wprowadz aktualny dzien:");
            int day = scanner.nextInt();

            System.out.println("Wprowadz swoje imie i nazwisko");
            String employee = scanner.nextLine();

            AuthenticationDocument authenticationDocument = documentManager.generateAuthenticationDocument(personToChange, editedData, LocalDate.of(year, month, day), employee, "DEATH" );

            System.out.println("\nCzy petent zaakcepotwal zmiany?");
            boolean isAccepted = scanner.nextBoolean();

            if (isAccepted){
                databaseManager.editPersonalData(personToChange, editedData, LocalDate.of(year, month, day), employee, "CHANGE");
                authenticationDocument.setAuthorized(true);
            }
            else  authenticationDocument.setAuthorized(false);
        }

        else if (choice == 3){
            System.out.println("Wprowadz dane urodzonej osoby:\nImie:");
            String name = scanner.nextLine();
            System.out.println("Drugie imie:");
            String secondName = scanner.nextLine();
            System.out.println("Nazwisko:");
            String surname = scanner.nextLine();
            System.out.println("Plec:");
            char gender = scanner.next().charAt(0);
            System.out.println("Rok urodzenia:");
            int year = scanner.nextInt();
            System.out.println("Miesiac urodzenia:");
            int month = scanner.nextInt();
            System.out.println("Rok urodzenia:");
            int day = scanner.nextInt();
            System.out.println("Miejsce urodzenia:");
            String placeOfBirth = scanner.nextLine();
            System.out.println("Imie ojca:");
            String fatherName = scanner.nextLine();
            System.out.println("Imie matki:");
            String motherName = scanner.nextLine();

            System.out.println("\nAdres zamieszkania\nUlica:");
            String resaStreet = scanner.nextLine();
            System.out.println("Numer domu:");
            String resaHouseNumber = scanner.nextLine();
            System.out.println("Numer mieszkania:");
            String resaApartamentNumber = scanner.nextLine();
            System.out.println("Kod pocztowy:");
            String resaPostalCode = scanner.nextLine();
            System.out.println("Miejscowosc:");
            String resaTown = scanner.nextLine();

            System.out.println("\nAdres zameldowania\nUlica:");
            String regaStreet = scanner.nextLine();
            System.out.println("Numer domu:");
            String regaHouseNumber = scanner.nextLine();
            System.out.println("Numer mieszkania:");
            String regaApartamentNumber = scanner.nextLine();
            System.out.println("Kod pocztowy:");
            String regaPostalCode = scanner.nextLine();
            System.out.println("Miejscowosc:");
            String regaTown = scanner.nextLine();

            Address registeredAddress = new Address(regaStreet, regaHouseNumber, regaApartamentNumber, regaPostalCode, regaTown);
            Address residenceAddress = new Address(resaStreet, resaHouseNumber, resaApartamentNumber, resaPostalCode, resaTown);

            Person newPerson = new Person(name, secondName, surname, gender, LocalDate.of(year, month, day),
                    placeOfBirth, fatherName, motherName, residenceAddress, registeredAddress);

            System.out.println("\nWprowadz aktualny rok:");
            year = scanner.nextInt();
            System.out.println("Wprowadz aktualny miesiac:");
            month = scanner.nextInt();
            System.out.println("Wprowadz aktualny dzien:");
            day = scanner.nextInt();

            System.out.println("Wprowadz swoje imie i nazwisko");
            String employee = scanner.nextLine();

            AuthenticationDocument authenticationDocument = documentManager.generateAuthenticationDocument(newPerson, newPerson, LocalDate.of(year, month, day), employee, "DEATH" );

            System.out.println("\nCzy petent zaakcepotwal zmiany?");
            boolean isAccepted = scanner.nextBoolean();

            if (isAccepted){
                databaseManager.addNewPerson(newPerson, LocalDate.of(year, month, day), employee);
                authenticationDocument.setAuthorized(true);
            }
            else  authenticationDocument.setAuthorized(false);
        }

    }

    public static void generateReport(){
        DocumentManager documentManager = new DocumentManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCzy nastapil czas na wygenerowanie kolejnego raportu?");
        boolean didTimePass = scanner.nextBoolean();

        if (didTimePass) {

            System.out.println("\nWprowadz aktualny rok:");
            int year = scanner.nextInt();
            System.out.println("Wprowadz aktualny miesiac:");
            int month = scanner.nextInt();
            System.out.println("Wprowadz aktualny dzien:");
            int day = scanner.nextInt();

            System.out.println("\nWprowadz rok poczatkowy:");
            int startYear = scanner.nextInt();
            System.out.println("Wprowadz miesiac poczatkowy:");
            int startMonth = scanner.nextInt();
            System.out.println("Wprowadz dzien poczatkowy:");
            int startDay = scanner.nextInt();

            System.out.println("\nWprowadz rok poczatkowy:)");
            int endYear = scanner.nextInt();
            System.out.println("Wprowadz miesiac poczatkowy:");
            int endMonth = scanner.nextInt();
            System.out.println("Wprowadz dzien poczatkowy:");
            int endDay = scanner.nextInt();

            Report report = documentManager.generateReport(LocalDate.of(startYear, startMonth, startDay),
                    LocalDate.of(endYear, endMonth, endDay), LocalDate.of(year, month, day));

            documentManager.showReport(report.getDateOfCreation());
        }
    }

    public static void showReports(){
        DocumentManager documentManager = new DocumentManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Wszystkie raporty\n2.Pełny raport z określonego dnia");
        int choice = scanner.nextInt();
        if (choice == 1){
            documentManager.showReport();
        }
        else if (choice == 2){
            System.out.println("\nWprowadz rok wygenerowania raportu:");
            int year = scanner.nextInt();
            System.out.println("Wprowadz miesiac wygenerowania raportu:");
            int month = scanner.nextInt();
            System.out.println("Wprowadz dzien wygenerowania raportu:");
            int day = scanner.nextInt();

            documentManager.showReport(LocalDate.of(year, month, day));
        }

    }

}
