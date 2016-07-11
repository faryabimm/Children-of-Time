package com.childrenOfTime.cgd;

import com.childrenOfTime.exceptions.ExistingUserException;
import com.childrenOfTime.exceptions.InvalidUserNameOrPasswordException;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class User implements Serializable {
    private String userName;
    private String passWord;


    public static ArrayList<User> users = new ArrayList<>();

    static {
        try {
            File userDataFile = new File("src/user_data/all/users.dat");
            if (!userDataFile.exists()) {
                userDataFile.createNewFile();
                ObjectOutputStream objectIO = new ObjectOutputStream(new FileOutputStream(userDataFile));
                objectIO.writeObject(users);
                objectIO.close();
            }

            ObjectInputStream objectIO = new ObjectInputStream(new FileInputStream(userDataFile));

            users = (ArrayList<User>) objectIO.readObject();
            objectIO.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public User(String userName, String passWord) throws InvalidUserNameOrPasswordException, ExistingUserException, IOException {

        Pattern p = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher m = p.matcher(passWord);
        boolean matchFound = m.matches();
        if (!matchFound) throw new InvalidUserNameOrPasswordException("Invalid Password format!");
        p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
        m = p.matcher(userName);
        matchFound = m.matches();
        if (!matchFound) throw new InvalidUserNameOrPasswordException("Invalid Username format!");
        this.userName = userName;
        this.passWord = passWord;
        if (users.contains(this)) throw new ExistingUserException("This Username Already exists!");
        users.add(this);

        ObjectOutputStream objectIO = new ObjectOutputStream(new FileOutputStream(
                new File("src/user_data/all/users.dat")));
        objectIO.writeObject(users);
        objectIO.close();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return (((User)obj).userName.equals(this.userName));
    }



}
