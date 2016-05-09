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

    /**
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     */
    public Instructor(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        classesTaught = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     *
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param classToAdd
     */
    public void addClassToTaught(Classes classToAdd) {
        classesTaught.add(classToAdd);
    }

    /**
     *
     * @return
     */
    public ArrayList<Classes> getClassesTaught() {
        return classesTaught;
    }

}
