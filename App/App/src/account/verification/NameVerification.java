package account.verification;

import account.Account;
import account.repository.AccountRepository;

import java.util.List;

public class NameVerification implements AccountVerifier{
    public NameVerification(){}

    public static boolean verify(String name){
        if(name.length() < 3 || name.length() > 20){
            return false;
        }
        List<Account> accounts = AccountRepository.loadAccounts();
        for(Account acc : accounts){
            if(acc.getUsername().equals(name)){
                return false;
            }
        }
        return true;
    }
}
