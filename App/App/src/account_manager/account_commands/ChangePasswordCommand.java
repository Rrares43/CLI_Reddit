package account_manager.account_commands;

import account_manager.PasswordChanger;

public class ChangePasswordCommand implements AccountCommand {
    @Override
    public void execute(){
        PasswordChanger.ChangePassword();
    }
}
