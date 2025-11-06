package com.example.tests.sauceDemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.tests.BaseTest;
import com.example.utils.SauceDemoSetup;
import com.example.utils.SetUpFacade;

public class LoginSauceTest extends BaseTest {
    private SauceDemoSetup sauceDemoSetup;
    /* ------------------------------------------------------------------------------------------------- */
    @BeforeMethod
    private void setUp(){
         sauceDemoSetup = SetUpFacade.inicializeSaucePages();
    }
    /* ------------------------------------------------------------------------------------------------- */
    /* Users */
    @DataProvider(name="validUsers")
    public Object[][] validUsers(){
        return new Object[][]{
            {"standard_user","secret_sauce"}
            /* {"problem_user","secret_sauce"},
            {"performance_glitch_user","secret_sauce"},
            {"error_user","secret_sauce"},
            {"visual_user","secret_sauce"} */
        };
    }
    /* ------------------------------------------------------------------------------------------------- */
    /* Test */
    @Test(dataProvider = "validUsers")
    public void usersLoginWithValidCredentials(String user, String pass){
        sauceDemoSetup.loginSaucePage.login(user, pass, config.getSauceDemoUrl())
        .waitHomeDisplayed();
        
    }
    
}
