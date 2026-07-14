package account_manager.account_commands;

import account_manager.PasswordChanger;

public class ChangePasswordCommand implements AccountCommand {
    private final PasswordChanger passwordChanger;

    public ChangePasswordCommand(PasswordChanger passwordChanger) {
        this.passwordChanger = passwordChanger;
    }


    @Override
    public void execute(){
        passwordChanger.ChangePassword();
    }
}
