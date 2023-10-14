package sample;

import java.sql.*;

/**
 * Created by adria on 8/15/2023.
 */
public class DataSource {
    public static final String DB_NAME = "BankAI.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\Software\\Learning_Java\\BankAI\\" + DB_NAME;


    public static final String TABLE_USERS = "Users";
    public static final String USERSACCOUNTNUMBER = "Account_Number";
    public static final String USERSACCOUNTFIRSTNAME = "FirstName";
    public static final String USERSACCOUNTLASTNAME = "LastName";
    public static final String USERSACCOUNTPHONENUMBER = "PhoneNumber";
    public static final String USERSACCOUNTTYPE = "Account_Type";
    public static final String USERSACCOUNTPASSWORD = "Password";


    public static final String INSERT_USERS = "INSERT INTO " + TABLE_USERS + '(' + USERSACCOUNTNUMBER + ", " + USERSACCOUNTFIRSTNAME + ", "
            + USERSACCOUNTLASTNAME + ", " + USERSACCOUNTPHONENUMBER + ", " + USERSACCOUNTTYPE + ", " + USERSACCOUNTPASSWORD + ") VALUES(?, ?, ?, ?, ?, ?)";


    private Connection conn;


    private PreparedStatement insertIntoUsers;

    public boolean open(){
        try{

            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertIntoUsers = conn.prepareStatement(INSERT_USERS);
            return true;
        } catch(SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{

            if(insertIntoUsers != null){
                insertIntoUsers.close();
            }

            if(conn != null){
                conn.close();
            }

        }catch (SQLException e){
            System.out.println("Couldn't close connection: " +e.getMessage());
        }

    }

    public int insertUsers(String accountNumber, String firstName, String lastName, String phoneNumber, String accountType,  String password) throws SQLException{
            int nomRowsInserted = 0;
            insertIntoUsers.setString(1, accountNumber);
            insertIntoUsers.setString(2, firstName);
            insertIntoUsers.setString(3, lastName);
            insertIntoUsers.setString(4, phoneNumber);
            insertIntoUsers.setString(5, accountType);
            insertIntoUsers.setString(6, password);

            nomRowsInserted = insertIntoUsers.executeUpdate();

            if(nomRowsInserted != 1){
                throw new SQLDataException("Couldn't insert into users table");
            }

            return nomRowsInserted;

    }

}
