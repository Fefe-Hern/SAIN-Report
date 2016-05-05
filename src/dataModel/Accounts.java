package dataModel;

import java.io.Serializable;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public interface Accounts extends Serializable {
    String getUserName();
    String getPassword();
    void setPassword(String newPassword);
    String getFirstName();
    void setFirstName(String newName);
    String getLastName();
    void setLastName(String newName);
    String getFullName();
}
