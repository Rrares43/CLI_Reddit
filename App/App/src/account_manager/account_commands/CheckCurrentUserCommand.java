package account_manager.account_commands;

import account_manager.Account;
import account_manager.AccountInfo;
import account_manager.AccountOperations;
import account_manager.SessionService;

public class CheckCurrentUserCommand implements AccountCommand{
    private final AccountInfo accountInfo;
    private final SessionService session;

    public CheckCurrentUserCommand(AccountInfo accountInfo, SessionService session){
        this.accountInfo = accountInfo;
        this.session = session;
    }
    @Override
    public void execute(){
        if(!session.isLoggedIn()) {
            System.out.println("Logged in as GUEST");
        }

        String currentUser = session.getCurrentUsername();
        Account currentAccount = AccountOperations.getAccountByUsername(currentUser);
        if(currentAccount != null) {
            AccountInfo.checkUser(currentAccount);
        }
    }
}
