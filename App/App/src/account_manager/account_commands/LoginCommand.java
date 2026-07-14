package account_manager.account_commands;

import account_manager.AccountLogin;

public class LoginCommand implements AccountCommand {
    private final AccountLogin AccountLogin;

    public LoginCommand(AccountLogin AccountLogin) {
        this.AccountLogin = AccountLogin;
    }

    @Override
    public void execute(){
        AccountLogin.Login();
    }
}
