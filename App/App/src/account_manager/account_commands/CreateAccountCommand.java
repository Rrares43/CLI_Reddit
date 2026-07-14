package account_manager.account_commands;

import account_manager.AccountCreator;

public class CreateAccountCommand implements AccountCommand {
    private final AccountCreator AccountCreator;

    public CreateAccountCommand(AccountCreator AccountCreator) {
        this.AccountCreator = AccountCreator;
    }

    @Override
    public void execute(){
        AccountCreator.createAccount();
    }
}
