package del.io;

public class Report {

    private int numberOfBirths;
    private int numberOfDeaths;
    private String birthToDeathRatio;
    private float meanDeathAge;
    private String fromDate;
    private String toDate;
    private String dateOfCreation;

    public Report(String fromDate, String toDate, String dateOfCreation) {
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
    return 0;
    }

    private int calculateNumberOfDeaths(){
        return 0;
    }

    private float calculateMeanDeathAge(){
        return (float)0.0;
    }

    private String calculateBirthToDeathRatio(){
        return "0:0";
    }

}
