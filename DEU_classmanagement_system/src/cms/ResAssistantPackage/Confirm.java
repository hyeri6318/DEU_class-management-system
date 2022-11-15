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
public interface Confirm {
    public void inquiry(DefaultTableModel model); //db에서 사용자 정보 읽어오기
    public void renewal(String id); //db에 내용 업데이트하기
}
