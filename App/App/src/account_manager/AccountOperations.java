package account_manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logger.Logger;
import logger.LogLevel;

public class AccountOperations {
    private static final String FILE_NAME = "App/data/accounts.txt";

    public static void saveAccount(Account account) {
        Logger logger = Logger.getInstance();
        if(account.getUsername().isBlank() || account.getEmail().isBlank() || account.getPassword().isBlank()){
            System.out.println("Please fill in all fields");
        }
        else if (account.verifyPassword(account.getPassword()) && !verifyAccount(account) && account.verifyEmail(account.getEmail())) {
            try (FileWriter addAccount = new FileWriter(FILE_NAME, true)) {
                addAccount.write(account.getUsername() + "," + account.getEmail() + "," + account.getPassword() + "\n");
                System.out.println("Account Saved");
                logger.log(LogLevel.INFO, "Account Saved");

            } catch (IOException e) {
                System.out.println("Error");
                logger.log(LogLevel.ERROR, "Error");
            }
        }
        else if(!account.verifyEmail(account.getEmail())){
            System.out.println("Email condition not respected");
            logger.log(LogLevel.WARNING, "Email condition not respected");
        }
        else if(!account.verifyPassword(account.getPassword())){
            System.out.println("Password condition not respected");
            logger.log(LogLevel.WARNING, "Password condition not respected");
        }
        else{
            System.out.println("Account already exists");
            logger.log(LogLevel.ERROR, "Account already exists");
        }
    }

    // used for checking if the account already exists
    public static boolean verifyAccount(Account account){
        File accountFile = new File(FILE_NAME);
        Logger logger = Logger.getInstance();

        try(Scanner fileReader = new Scanner(accountFile)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if(data[0].equals(account.getUsername()) && data[2].equals(account.getPassword())){
                    System.out.println("Account Exists");
                    logger.log(LogLevel.ERROR, "Account Exists");
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
            logger.log(LogLevel.ERROR, "Error");
        }
        return false;
    }

    // used for changing the password
    public static void changePassword(String email, String newPassword){
        File accountFile = new File(FILE_NAME);
        List<String> newLines = new ArrayList<>();
        boolean userFound = false;
        Logger logger = Logger.getInstance();

        try(Scanner fileReader = new Scanner(accountFile)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if(data[1].equals(email)){
                    userFound = true;
                    newLines.add(data[0] + "," + email + "," + newPassword);
                }
                else{
                    newLines.add(line);
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
            logger.log(LogLevel.ERROR, "Error");
            return;
        }

        if(!userFound){
            System.out.println("User not found");
            logger.log(LogLevel.ERROR, "User not found");
            return;
        }

        try(FileWriter fw = new FileWriter(accountFile, false)){
            for(String line: newLines){
                fw.write(line + "\n");
            }
            System.out.println("Password Changed");
            logger.log(LogLevel.INFO, "Password Changed");
        }
        catch(IOException e){
            System.out.println("Error");
            logger.log(LogLevel.ERROR, "Error");
        }
    }

    // used for checking if the email already exists
    public static boolean checkEmail(String email){
        File accountFile = new File(FILE_NAME);
        Logger logger = Logger.getInstance();
        try(Scanner fileReader = new Scanner(accountFile)){
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if (data[1].equals(email)) {
                    return true;
                }
            }
            return false;
        }
        catch(IOException e){
            System.out.println("Error");
            logger.log(LogLevel.ERROR, "Error");
        }
        return false;
    }
}
