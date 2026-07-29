package account.verification;

import account.Account;
import account.repository.AccountRepository;

import java.util.List;

public class NameVerification implements AccountVerifier{
    public NameVerification(){}

    public static boolean verify(String name){
        if(name.length() < 3 || name.length() > 20){
            System.out.println("Name must be between 3 and 20 characters");
            return false;
        }
        List<Account> accounts = AccountRepository.loadAccounts();
        for(Account acc : accounts){
            if(acc.getUsername().equals(name)){
                System.out.println("Name already exists");
                return false;
            }
        }
        return true;
    }
}
