package bootstrap;

import account_manager.AccountCreator;
import account_manager.AccountInfo;
import account_manager.AccountLogin;
import account_manager.AccountLogout;
import account_manager.AccountQuery;
import account_manager.PasswordChanger;
import account_manager.SessionService;
import account_manager.account_commands.ChangePasswordCommand;
import account_manager.account_commands.CheckCurrentUserCommand;
import account_manager.account_commands.CreateAccountCommand;
import account_manager.account_commands.LoginCommand;
import account_manager.account_commands.LogoutCommand;
import posting.OutputWriter;
import posting.StringReader;

final class AccountModule {
    private AccountModule() {
    }

    static AccountQuery create(StringReader stringReader, OutputWriter output, SessionService sessionService) {
        AccountQuery accountQuery = new AccountQuery(stringReader, output, sessionService);
        AccountCreator accountCreator = new AccountCreator(stringReader, output);
        AccountLogin accountLogin = new AccountLogin(stringReader, sessionService, output);
        PasswordChanger passwordChanger = new PasswordChanger();
        AccountInfo accountInfo = new AccountInfo();
        AccountLogout accountLogout = new AccountLogout(sessionService);

        accountQuery.registerCommand("1", new CreateAccountCommand(accountCreator));
        accountQuery.registerCommand("2", new LoginCommand(accountLogin));
        accountQuery.registerCommand("3", new ChangePasswordCommand(passwordChanger));
        accountQuery.registerCommand("4", new CheckCurrentUserCommand(accountInfo, sessionService));
        accountQuery.registerCommand("5", new LogoutCommand(accountLogout));

        return accountQuery;
    }
}
