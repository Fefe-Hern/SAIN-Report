package dataModel;

import java.io.Serializable;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public interface Members extends Serializable {
    String getUsername();
    String getPassword();
    String setPassword(String newPassword);
    String getName();
    String setName(String newName);
}
