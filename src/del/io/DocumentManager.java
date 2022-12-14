package del.io;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {

    private static List<Report> reports = new ArrayList<>();
    private static List<AuthenticationDocument> authenticationDocuments = new ArrayList<>();


    public DocumentManager() { }

    public Report generateReport(LocalDate fromDate, LocalDate toDate, LocalDate dateOfCreation){
       Report report = new Report(fromDate, toDate, dateOfCreation);
       reports.add(report);
       return report;
    }

    public void showReport (LocalDate dateOfCreation){
        for (Report report: reports) {
            if (report.getDateOfCreation().equals(dateOfCreation)) {
                System.out.println("\nData utworzenia: " + report.getDateOfCreation() +
                        "\nData poczatkowa: " + report.getFromDate() +
                        "\nData koncowa: " + report.getToDate() +
                        "\nIlosc urodzen: " + report.getNumberOfBirths() +
                        "\n Ilosc zgonow: " + report.getNumberOfDeaths() +
                        "\n Sredni wiek zgonow: " + report.getMeanDeathAge() +
                        "\n Stosunek urodzen do zgonow (format-> ilosc urodzen:ilosc zgonow): " + report.getBirthToDeathRatio() + "\n");
            }
        }
    }

    public void showReport (){
        for (Report report: reports) {

                System.out.println("\nData utworzenia: " + report.getDateOfCreation() +
                        "\nData poczatkowa: " + report.getFromDate() +
                        "\nData koncowa: " + report.getToDate() + "\n");

        }
    }

    public AuthenticationDocument generateAuthenticationDocument(ArchiveEntry entry){
        AuthenticationDocument authenticationDocument = new AuthenticationDocument(entry.getOldData(), entry.getNewData(), entry.getDateOfChange(), entry.getPersonResponsible(), entry.getType());
        authenticationDocuments.add(authenticationDocument);
        return authenticationDocument;
    }

    private void printEntryDetails(){}
}
