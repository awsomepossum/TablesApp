package com.msushanth.tablesapp.BusinessLogicLayer.DispatchClasses;

import com.msushanth.tablesapp.BusinessLogicLayer.ManagerClasses.AccountManager;

/**
 * Created by Sushanth on 11/10/17.
 */

public class AccountDispatch {
    public void logout(){
        AccountManager ma = new AccountManager();
        ma.logout();
    }

    public boolean passwordRecovery(String email){
        AccountManager ma = new AccountManager();
        return ma.passwordRecovery(email);
    }
}
