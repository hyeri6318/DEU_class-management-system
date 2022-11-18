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
import javax.swing.table.TableModel;

/**
 *
 * @author yooun
 */
public class ConfirmUnres implements Confirm {

    ConnectDB db = new ConnectDB();
    Connection conn = null;
    PreparedStatement query = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    Statement st = null;
    ResultSet rs = null;

    @Override
    //db에서 예약 승인된 사용자 정보 불러오기
    public void inquiry(DefaultTableModel model) {
        try {
            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("SELECT * FROM RESERVATION WHERE APPROVE = '1'"));
            ArrayList name = new ArrayList<String>();
            ArrayList id = new ArrayList<String>();
            ArrayList classNum = new ArrayList<String>();
            ArrayList seatNum = new ArrayList<String>();
            ArrayList rStartTime = new ArrayList<String>();
            ArrayList rEndTime = new ArrayList<String>();
            ArrayList admin = new ArrayList<String>();
            ArrayList approve = new ArrayList<String>();
            ArrayList day = new ArrayList<String>();

            while (rs.next()) {
                name.add(rs.getString("NAME"));
                id.add(rs.getString("ID"));
                classNum.add(rs.getString("CLASS_NUM"));
                seatNum.add(rs.getString("SEAT_NUM"));
                rStartTime.add(rs.getString("R_STARTTIME"));
                rEndTime.add(rs.getString("R_ENDTIME"));
                admin.add(rs.getString("ADMIN"));
                approve.add(rs.getString("APPROVE"));
                day.add(rs.getString("day"));
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
                arr.add(day.get(i));

                model.addRow(new Object[]{arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5), arr.get(6), arr.get(7), arr.get(8)});
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    //db에서 해당 사용자의 예약 정보를 delete하기
    public void renewal(String id) {
        try {
            conn = db.getConnection();
            st = conn.createStatement();

            TableModel model1 = PageUnres.jTable1.getModel();
            int[] indexs = PageUnres.jTable1.getSelectedRows();

            Object[] row = new Object[9];

            for (int i = 0; i < indexs.length; i++) {
                row[1] = model1.getValueAt(indexs[i], 1);
                row[2] = model1.getValueAt(indexs[i], 2);
            }

            ArrayList id_list = new ArrayList<String>();

            ps3 = conn.prepareStatement("delete reservation where id='" + row[1] + "'");
            ps3.executeUpdate();

            ps1 = conn.prepareStatement("update reservation set admin=0 where class_num='" + row[2] + "' and approve=1");
            ps1.executeUpdate();

            rs = st.executeQuery("select id from reservation where class_num='" + row[2] + "' and approve=1 order by r_endtime desc");

            while (rs.next()) {
                id_list.add(rs.getString("id"));
            }

            String adminStudent = id_list.get(0).toString();
            System.out.println(adminStudent);

            ps2 = conn.prepareStatement("update reservation set admin=1 where id='" + adminStudent + "'");
            ps2.executeUpdate();

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
