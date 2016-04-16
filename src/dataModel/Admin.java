package dataModel;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Admin implements Accounts {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String id;

    public Admin(String userName, String password, String firstName, String lastName, String id) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
