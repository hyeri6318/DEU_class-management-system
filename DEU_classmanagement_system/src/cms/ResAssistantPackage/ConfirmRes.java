/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.ResAssistantPackage;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import cms.ConnectDB.ConnectDB;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yooun
 */
public class ConfirmRes implements Confirm {

    ConnectDB db = new ConnectDB();
    Connection conn = null;
    PreparedStatement query = null;
    Statement st = null;
    ResultSet rs = null;
    
    @Override
    //db에서 예약 승인 대기 상태인 사용자 정보 불러오기
    public void inquiry(DefaultTableModel model) {

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            
            ArrayList name = new ArrayList<String>();
            ArrayList id = new ArrayList<String>();
            ArrayList classNum = new ArrayList<String>();
            ArrayList seatNum = new ArrayList<String>();
            ArrayList rStartTime = new ArrayList<String>();
            ArrayList rEndTime = new ArrayList<String>();
            ArrayList admin = new ArrayList<String>();
            ArrayList approve = new ArrayList<String>();
            
            rs = st.executeQuery("SELECT * FROM RESERVATION WHERE APPROVE = 0");

            while (rs.next()) {
                name.add(rs.getString("NAME"));
                id.add(rs.getString("ID"));
                classNum.add(rs.getString("CLASS_NUM"));
                seatNum.add(rs.getString("SEAT_NUM"));
                rStartTime.add(rs.getString("R_STARTTIME"));
                rEndTime.add(rs.getString("R_ENDTIME"));
                admin.add(rs.getString("ADMIN"));
                approve.add(rs.getString("APPROVE"));
            }

            Object[] tableline = name.toArray();

            for (int i = 0; i < tableline.length; i++) {

                ArrayList arr = new ArrayList<>();

                arr.add(name.get(i));
                arr.add(id.get(i));
                arr.add(classNum.get(i));
                arr.add(seatNum.get(i));
                arr.add(rStartTime.get(i));
                arr.add(rEndTime.get(i));
                arr.add(admin.get(i));
                arr.add(approve.get(i));

                model.addRow(new Object[]{arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5), arr.get(6), arr.get(7)});
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    //db에 해당 사용자 check를 0 -> 1 로 변경하기
    public void renewal(String id) {

        try {
            conn = db.getConnection();
            st = conn.createStatement();

            System.out.println(id);
            //rs = st.executeQuery("SELECT * FROM RESERVATION WHERE ID = " + id + ";");

            query = conn.prepareStatement("UPDATE RESERVATION SET APPROVE = 1 WHERE id = '" + id + "'");
            query.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
