package dataModel;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Instructor implements Accounts {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    
    private ArrayList<Classes> classesTaught;

    public Instructor(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        classesTaught = new ArrayList<>();
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
    public String getFullName() {
        return firstName + " " + lastName;
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

    public void addClassToTaught(Classes classToAdd) {
        classesTaught.add(classToAdd);
    }

    public ArrayList<Classes> getClassesTaught() {
        return classesTaught;
    }

}
