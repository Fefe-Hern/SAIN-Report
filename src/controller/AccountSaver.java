package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import dataModel.Accounts;
import dataModel.Admin;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AccountSaver {
    private static HashMap<String, Accounts> accountMap;
    private static File file;
    public static void initialize() {
        file = new File("AccountInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                accountMap = (HashMap<String, Accounts>) ois.readObject();
                ois.close();
                System.out.println("Accounts loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Accounts file created.");
                    accountMap = initializeAccountMap();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void save() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accountMap);
            oos.close();
            System.out.println("Accounts saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HashMap<String, Accounts> initializeAccountMap() {
        HashMap<String, Accounts> map = new HashMap<>();
        map.put("hernf07", new Admin("hernf07", "test", "Fernando", "Hernandez", "00268"));
        return map;
    }
    
    public static Accounts getAccount(String userName, String password) {
        if(accountMap.containsKey(userName)) {
            if(password.equals(accountMap.get(userName).getPassword())){
                return accountMap.get(userName);
            }
        }
        return null;
    }
    
    public static boolean addAccount(Accounts newAccount) {
        if(!accountMap.containsKey(newAccount.getUserName())) {
            accountMap.put(newAccount.getUserName(), newAccount);
            return true;
        }
        return false;
    }
}
