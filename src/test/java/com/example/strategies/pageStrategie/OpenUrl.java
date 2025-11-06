package com.example.strategies.pageStrategie;

public class OpenUrl {
    private OpenPageStrategy strategy;

    public void setStrategy(OpenPageStrategy strategy) {
        this.strategy = strategy;
    }

    public void open(){
        if(strategy != null){
            strategy.openPage();
        }else{
            throw new IllegalStateException("OpenPageStrategy not set");
        }    
    }

}
