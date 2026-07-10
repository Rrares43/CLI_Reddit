package account_manager.account_commands;

import account_manager.AccountLogin;

public class LoginCommand implements AccountCommand {
    @Override
    public void execute(){
        AccountLogin.Login();
    }
}
