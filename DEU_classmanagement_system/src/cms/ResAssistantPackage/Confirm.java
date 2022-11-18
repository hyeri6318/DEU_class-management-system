/*
작성자 : 윤채민
 */
package cms.ResAssistantPackage;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 윤채민
 */
public interface Confirm {
    public void inquiry(DefaultTableModel model); //db에서 사용자 정보 읽어오기
    public void renewal(String id); //db에 내용 업데이트하기
}
