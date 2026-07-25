package bootstrap;

import account.AccountCreator;
import account.AccountInfo;
import account.AccountLogin;
import account.AccountLogout;
import account.AccountQuery;
import account.PasswordChanger;
import account.SessionService;
import account.command.ChangePasswordCommand;
import account.command.CheckCurrentUserCommand;
import account.command.CreateAccountCommand;
import account.command.LoginCommand;
import account.command.LogoutCommand;
import util.OutputWriter;
import util.StringReader;

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
