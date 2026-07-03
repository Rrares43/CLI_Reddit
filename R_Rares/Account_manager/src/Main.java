import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("Input 1 for account creation or 2 for loggin in:");
    String choice = sc.nextLine();

    if(choice.equals("1")) {
      System.out.println("Enter Username: ");
      String username = sc.nextLine();
      System.out.println("Enter Email: ");
      String email = sc.nextLine();
      System.out.println("Enter Password: ");
      String password = sc.nextLine();
      Account account = new Account(username, email, password);
      saveAccount(account);
    }

    else if(choice.equals("2")){
      System.out.println("Enter Username: ");
      String username = sc.nextLine();
      System.out.println("Enter Password: (or enter 0 to change password)");
      String password = sc.nextLine();
      if(password.equals("0")){
        System.out.println("Enter New Password: ");
        password = sc.nextLine();
        changePassword(username, password);
      }
      else if(verifyAccount(new Account(username, "", password))){
        System.out.println("Login Successful");
      }
      else{
        System.out.println("Login Failed");
      }
      }
    else{
      System.out.println("Invalid Input");
    }
  }


  public static void saveAccount(Account account){
    try (FileWriter add_account = new FileWriter("accounts.txt", true)){
      add_account.write(account.getUsername() + " " + account.getEmail() + " " +  account.getPassword() + "\n");
      System.out.println("Account Saved");
    }
    catch (IOException e){
      System.out.println("Error");
    }
  }

  public static boolean verifyAccount(Account account){
    File accountFile = new File("accounts.txt");

    try(Scanner fileReader = new Scanner(accountFile)){
      while(fileReader.hasNextLine()){
        String line = fileReader.nextLine();
        String[] data = line.split(" ");
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

  // right now doesn't work, to be changed
  public static void changePassword(String username, String newPassword){
    File accountFile = new File("accounts.txt");
    try(Scanner fileReader = new Scanner(accountFile)){
      while (fileReader.hasNextLine()){
        String line = fileReader.nextLine();
        String[] data = line.split(" ");
        if(data[0].equals(username)){
          data[1] = newPassword;
        }
      }
    }
    catch(IOException e){
      System.out.println("Error");
    }
  }
}