package account_manager.account_commands;

import account_manager.AccountCreator;

public class CreateAccountCommand implements AccountCommand {
    @Override
    public void execute(){
        AccountCreator.AccountCreate();
    }
}
