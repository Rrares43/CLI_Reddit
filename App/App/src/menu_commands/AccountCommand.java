package menu_commands;

import account_manager.AccountQuery;

public class AccountCommand implements MenuCommand {
    private final AccountQuery accountQuery;

    public AccountCommand(AccountQuery accountQuery) {
        this.accountQuery = accountQuery;
    }
    @Override
    public void execute(){
        accountQuery.startAccountMenu();
    }
}

