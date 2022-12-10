package del.io;

public class AuthenticationDocument extends ArchiveEntry {

    private boolean isAuthorized;

    public AuthenticationDocument(Person oldData, Person newData, String dateOfChange,
                                  String personResponsible, String type) {
        super(oldData, newData, dateOfChange, personResponsible, type);
        this.isAuthorized = false;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
