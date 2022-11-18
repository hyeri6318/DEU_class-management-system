/*
작성자 : 정수연
 */
package cms.SchedulePackage;

public class Admin {
    
    RegisterSchedule registerSchedule;
    SearchState searchState;
    
    public void display(){  
        
    }
    
    public void performRegister(){
        registerSchedule.register();
    }
    
    public void setRegisterSchedule(RegisterSchedule r){
        registerSchedule = r;
    }
    
    public void performState(){
        searchState.search();
    }
    
    public void setSearchState(SearchState s){
        searchState = s;
    }
}
