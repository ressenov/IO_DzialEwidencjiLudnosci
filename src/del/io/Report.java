package del.io;

import java.time.LocalDate;
import java.util.List;

public class Report {

    private int numberOfBirths;
    private int numberOfDeaths;
    private String birthToDeathRatio;
    private float meanDeathAge;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate dateOfCreation;

    public Report(LocalDate fromDate, LocalDate toDate, LocalDate dateOfCreation) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.dateOfCreation = dateOfCreation;
        this.numberOfBirths = calculateNumberOfBirths();
        this.numberOfDeaths = calculateNumberOfDeaths();
        this.meanDeathAge = calculateMeanDeathAge();
        this.birthToDeathRatio = calculateBirthToDeathRatio();
    }

    public int getNumberOfBirths() {
        return numberOfBirths;
    }

    public int getNumberOfDeaths() {
        return numberOfDeaths;
    }

    public String getBirthToDeathRatio() {
        return birthToDeathRatio;
    }

    public float getMeanDeathAge() {
        return meanDeathAge;
    }

    private int calculateNumberOfBirths(){
        DatabaseManager dbM = new DatabaseManager();
        List<ArchiveEntry> archive = dbM.getArchive();

        int births = 0;
        for (ArchiveEntry entry:archive) {
            if(entry.getType().equals("BIRTH") && entry.getDateOfChange().isAfter(fromDate) && entry.getDateOfChange().isBefore(toDate))
                births++;
        }
        return births;
    }

    private int calculateNumberOfDeaths(){
        DatabaseManager dbM = new DatabaseManager();
        List<ArchiveEntry> archive = dbM.getArchive();

        int deaths = 0;
        for (ArchiveEntry entry:archive) {
            if(entry.getType().equals("DEATH") && entry.getDateOfChange().isAfter(fromDate) && entry.getDateOfChange().isBefore(toDate))
                deaths++;
        }
        return deaths;
    }

    private float calculateMeanDeathAge(){
        DatabaseManager dbM = new DatabaseManager();
        List<ArchiveEntry> archive = dbM.getArchive();

        int sumOfAge = 0;
        int amount = 0;
        for (ArchiveEntry entry:archive) {
            if(entry.getType().equals("DEATH") && entry.getDateOfChange().isAfter(fromDate) && entry.getDateOfChange().isBefore(toDate)) {
                amount++;
                sumOfAge += ( entry.getNewData().getDateOfDeath().getYear() - entry.getNewData().getDateOfBirth().getYear() );
            }
        }
        float meanDeathAge = sumOfAge/amount;
        return meanDeathAge;
    }

    private String calculateBirthToDeathRatio(){
        String ratio = "";
        ratio = Integer.toString(this.numberOfBirths) + " : " + Integer.toString(this.numberOfDeaths);
        return ratio;
    }

}
