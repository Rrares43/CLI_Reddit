package account_manager;

import logger.LogLevel;
import logger.Logger;

public class AccountLogout {
    private final SessionService service;

    public AccountLogout(SessionService service) {
        this.service = service;
    }

    public void Logout(){
        Logger logger = Logger.getInstance();
        System.out.println("Logged out of account");
        service.login("guest");
        logger.log(LogLevel.INFO, "User logged out of their account");
    }
}
