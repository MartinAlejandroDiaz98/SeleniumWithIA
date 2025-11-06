package com.example.strategies.pageStrategie;


import com.example.pages.sauceDemo.LoginSaucePage;

public class SauceDemoStrategy implements OpenPageStrategy {
    LoginSaucePage loginSaucePage;
    String user;
    String pass;
    String url;


    public SauceDemoStrategy(String user, String pass, String url, LoginSaucePage loginSaucePage) {
        this.loginSaucePage = loginSaucePage;
        this.user = user;
        this.pass = pass;
        this.url = url;
    }

    @Override
    public void openPage() {
        // Implementation for opening the SauceDemo page
        loginSaucePage.open(this.url)
        .typeUser(this.user)
        .typePass(this.pass)
        .clickBtnLogin();
    }

}
