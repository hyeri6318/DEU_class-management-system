/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.ResAssistantPackage;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yooun
 */
public class User {
    
    private Confirm confirm;
    ConfirmRes confirmRes = new ConfirmRes();
    ConfirmUnres confirmUnres = new ConfirmUnres();
    
//    private String name; //이름
//    private int id; //학번
//    private int class_num; //호실
//    private int seat_num; //좌석 번호
//    private int r_starttime; //완료 시간
//    private int r_endtime; //종료 시간
//    private int admin; //관리자 여부
//    private int approve; //승인 여부
    
    public User(){
    }
    
    public void iqResUser(DefaultTableModel model){
        confirmRes.inquiry(model);
    }
    
    public void iqUnresUser(DefaultTableModel model){
        confirmUnres.inquiry(model);
    }
    public void selectID(String id){
        //id 선택
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
