/*
작성자 : 윤채민
 */
package cms.ResAssistantPackage;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 윤채민
 */
public class User {
    
    private Confirm confirm;
    ConfirmRes confirmRes = new ConfirmRes();
    ConfirmUnres confirmUnres = new ConfirmUnres();
    
    public User(){
    }
    
    public void iqResUser(DefaultTableModel model){
        confirmRes.inquiry(model);
    }
    
    public void iqUnresUser(DefaultTableModel model){
        confirmUnres.inquiry(model);
    }
    
    public void rnResUser(String id){
        //예약 요청 승인 클래스 호출
        confirmRes.renewal(id);
    }
    
    public void rnUnresUser(String id){
        //예약 승인 취소 클래스 호출
        confirmUnres.renewal(id);
    }
    
}
