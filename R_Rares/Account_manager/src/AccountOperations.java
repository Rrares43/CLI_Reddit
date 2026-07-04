import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class AccountOperations {
    private static final String FILE_NAME = "accounts.txt";

    public static void saveAccount(Account account) {
        if (account.verifyPassword(account.getPassword()) && !verifyAccount(account) && account.verifyEmail(account.getEmail())) {
            try (FileWriter addAccount = new FileWriter(FILE_NAME, true)) {
                addAccount.write(account.getUsername() + "," + account.getEmail() + "," + account.getPassword() + "\n");
                System.out.println("Account Saved");
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        else{
            System.out.println("Password condition not respected");
        }
    }

    public static boolean verifyAccount(Account account){
        File accountFile = new File(FILE_NAME);

        try(Scanner fileReader = new Scanner(accountFile)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if(data[0].equals(account.getUsername()) && data[2].equals(account.getPassword())){
                    System.out.println("Account Exists");
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
        }
        return false;
    }

    public static void changePassword(String username, String newPassword){
        File accountFile = new File(FILE_NAME);
        List<String> newLines = new ArrayList<>();
        boolean userFound = false;

        try(Scanner fileReader = new Scanner(accountFile)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if(data[0].equals(username)){
                    userFound = true;
                    newLines.add(username + "," + data[1] + "," + newPassword);
                }
                else{
                    newLines.add(line);
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
            return;
        }

        if(!userFound){
            System.out.println("User not found");
            return;
        }

        try(FileWriter fw = new FileWriter(accountFile, false)){
            for(String line: newLines){
                fw.write(line + "\n");
            }
            System.out.println("Password Changed");
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    public static boolean checkEmail(String email){
        File accountFile = new File(FILE_NAME);
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
        }
        return false;
    }
}
