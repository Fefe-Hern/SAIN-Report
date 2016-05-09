package dataModel;

import java.io.Serializable;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public interface Accounts extends Serializable {

    /**
     *
     * @return
     */
    String getUserName();

    /**
     *
     * @return
     */
    String getPassword();

    /**
     *
     * @param newPassword
     */
    void setPassword(String newPassword);

    /**
     *
     * @return
     */
    String getFirstName();

    /**
     *
     * @param newName
     */
    void setFirstName(String newName);

    /**
     *
     * @return
     */
    String getLastName();

    /**
     *
     * @param newName
     */
    void setLastName(String newName);

    /**
     *
     * @return
     */
    String getFullName();
}
