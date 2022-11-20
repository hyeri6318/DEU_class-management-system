/*
작성자 : 이혜리
기능 : 좌석 조회 
 */
package cms.ClassSearchPackage;

import cms.ConnectDB.ConnectDB;
import cms.ResCanclePackage.Cancle;
import cms.ResCanclePackage.CancledbUpdate;
import cms.ResStudentPackage.ResdbUpdate;
import cms.ResStudentPackage.Reservation;
import cms.UserPackage.LoginPage;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author 이혜리
 */
public class SeatSearchPage extends javax.swing.JFrame {

    /**
     * Creates new form SeatSearchPage
     */
    public SeatSearchPage() {
        initComponents();
    }

    LoginPage lg = new LoginPage();

    String name;
    String id;
    int class_num;
    int seat_num;
    String starttime;
    String endtime;
    String final_day;
    int admin;
    int approve;

    String final_date;

    // 날짜, 요일 추출을 위한 변수
    int year;
    int month;
    int date;
    int day;

    public boolean r_check() {  // 예약 여부 확인
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Reservation where approve=1");

            ArrayList<String> id_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
            }

            for (int i = 0; i < id_list.size(); i++) {
                if (lg.getID().equals(id_list.get(i))) {   // 아이디가 있을 경우 예약 됨.
                    return false;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public boolean w_check() {   // 경고 횟수에 따른 예약 가능여부 확인
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            check_date();

            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from client where id='" + lg.getID() + "'");

            ArrayList<String> warn_list = new ArrayList<String>();
            ArrayList<String> date_list = new ArrayList<String>();

            while (rs.next()) {
                warn_list.add(rs.getString("warning"));
                date_list.add(rs.getString("w_date"));
            }

            for (int i = 0; i < warn_list.size(); i++) {
                if (warn_list.get(i).compareTo("3") >= 0) {   // 아이디가 있을 경우 예약 됨.
                    int w_check = Integer.valueOf(date_list.get(i).substring(8, 10));
                    if (w_check + 7 == date) {  // 경고 받은 날로부터 일주일이 지났는지 확인
                        ps = conn.prepareStatement("update Client set warning=0, w_date=null where id='" + lg.getID() + "'");
                        ps.executeUpdate();
                        return false;
                    } else {
                        return false;
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    String seat = null;

    public boolean r_seat() {   // 좌석 선택 여부 확인
        class_num = Integer.parseInt(class_combobox.getSelectedItem().toString());
        starttime = start_combobox.getSelectedItem().toString();
        endtime = end_combobox.getSelectedItem().toString();

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Reservation where class_num='" + class_num + "' and approve=1");

            ArrayList<String> seat_list = new ArrayList<String>();
            ArrayList<String> starttime_list = new ArrayList<String>();
            ArrayList<String> endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }

            int[] r_time = new int[25];
            int tmp = 0;
            int count = 0;
            int count1 = 0;

            for (int i = 0; i < seat_list.size(); i++) {
                int start = Integer.parseInt(starttime_list.get(i).toString().substring(0, 2));
                int end = Integer.parseInt(endtime_list.get(i).toString().substring(0, 2));
                seat = seat_list.get(i).toString();

                tmp = end - start;

                for (int k = start; k < start + tmp; k++) {
                    r_time[k] = 1;
                }
            }

            int s_start = Integer.valueOf(starttime.substring(0, 2));
            int s_end = Integer.valueOf(endtime.substring(0, 2));
            int temp = s_end - s_start;

            String rbtn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

            JRadioButton r[] = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10,
                r11, r12, r13, r14, r15, r16, r17, r18, r19, r20,
                r21, r22, r23, r24, r25, r26, r27, r28, r29, r30,
                r31, r32, r33, r34, r35, r36, r37, r38, r39, r40};

            for (int i = 0; i < rbtn.length; i++) {
                if (r[i].isSelected()) {
                    if (rbtn[i].equals(seat)) {   // 예약 된 좌석
                        for (int j = s_start; j < s_end + 1; j++) {
                            if (r_time[j] == 1) {   // 예약되어 있는 시간 (예약 불가능)
                                return false;

                            } else if (count == temp) {// 예약되어 있지 않은 시간 (예약 가능)
                                seat_num = Integer.parseInt(seat);
                                return true;
                            }
                            count++;
                        }
                    } else { // 예약되지 않은 좌석
                        seat_num = Integer.parseInt(seat);
                        return true;
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    String k_date = null;

    public void check_date() {  // 날짜, 요일 추출을 위한 메서드

        Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        day = c.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case 1:
                k_date = "일";
                break;
            case 2:
                k_date = "월";
                break;
            case 3:
                k_date = "화";
                break;
            case 4:
                k_date = "수";
                break;
            case 5:
                k_date = "목";
                break;
            case 6:
                k_date = "금";
                break;
            case 7:
                k_date = "토";
                break;
        }

        final_date = year + "년" + month + "월" + date + "일";
        final_day = k_date;
    }

    public boolean timecheck() {   // 16시 30분 이전에 예약하는지 확인
        Date d1 = null;
        Date d2 = null;
        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            d1 = f.parse("16:30:00");
            d2 = f.parse(SimpleDateFormat.format(currentTime));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (d1.compareTo(d2) >= 0) {
            return true;
        }
        return false;
    }

    public void cut_class() {   // 17시 이후 예약일 경우 인원 제한

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        int count915 = 0;
        int count916 = 0;
        int count918 = 0;
        int count911 = 0;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from reservation where approve=1");

            ArrayList id_list = new ArrayList<String>();
            ArrayList classnum_list = new ArrayList<String>();
            ArrayList classnum915_list = new ArrayList<String>();
            ArrayList classnum916_list = new ArrayList<String>();
            ArrayList classnum918_list = new ArrayList<String>();
            ArrayList classnum911_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
                classnum_list.add(rs.getString("class_num"));
            }

            for (int i = 0; i < id_list.size(); i++) {
                if (classnum_list.get(i).equals("915")) {
                    classnum915_list.add(id_list.get(i));
                    count915++;
                } else if (classnum_list.get(i).equals("916")) {
                    classnum916_list.add(id_list.get(i));
                    count916++;
                } else if (classnum_list.get(i).equals("918")) {
                    classnum918_list.add(id_list.get(i));
                    count918++;
                } else if (classnum_list.get(i).equals("911")) {
                    classnum911_list.add(id_list.get(i));
                    count911++;
                }
            }

            if (count915 < 25) {
                Reservation r = new Reservation();
                ResdbUpdate res = new ResdbUpdate(r);
                r.setMeasurements(lg.getName(), lg.getID(), 915, seat_num, starttime, endtime, final_day, 0, 0);
            } else if (count916 < 25) {
                Reservation r = new Reservation();
                ResdbUpdate res = new ResdbUpdate(r);
                r.setMeasurements(lg.getName(), lg.getID(), 916, seat_num, starttime, endtime, final_day, 0, 0);
            } else if (count918 < 25) {
                Reservation r = new Reservation();
                ResdbUpdate res = new ResdbUpdate(r);
                r.setMeasurements(lg.getName(), lg.getID(), 918, seat_num, starttime, endtime, final_day, 0, 0);
            } else if (count911 < 25) {
                Reservation r = new Reservation();
                ResdbUpdate res = new ResdbUpdate(r);
                r.setMeasurements(lg.getName(), lg.getID(), 911, seat_num, starttime, endtime, final_day, 0, 0);
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        s911_button = new javax.swing.JButton();
        s915_button = new javax.swing.JButton();
        s916_button = new javax.swing.JButton();
        s918_button = new javax.swing.JButton();
        reservation_button = new javax.swing.JButton();
        start_combobox = new javax.swing.JComboBox<>();
        end_combobox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        class_combobox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btn33 = new javax.swing.JButton();
        r11 = new javax.swing.JRadioButton();
        r12 = new javax.swing.JRadioButton();
        r13 = new javax.swing.JRadioButton();
        r14 = new javax.swing.JRadioButton();
        search_butoon = new javax.swing.JButton();
        r15 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        r_textfield = new javax.swing.JTextArea();
        r16 = new javax.swing.JRadioButton();
        r17 = new javax.swing.JRadioButton();
        btn24 = new javax.swing.JButton();
        r18 = new javax.swing.JRadioButton();
        r33 = new javax.swing.JRadioButton();
        btn17 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        r34 = new javax.swing.JRadioButton();
        btn9 = new javax.swing.JButton();
        cancle_button = new javax.swing.JButton();
        r35 = new javax.swing.JRadioButton();
        r1 = new javax.swing.JRadioButton();
        r36 = new javax.swing.JRadioButton();
        r37 = new javax.swing.JRadioButton();
        r38 = new javax.swing.JRadioButton();
        btn15 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        r2 = new javax.swing.JRadioButton();
        btn19 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        r3 = new javax.swing.JRadioButton();
        btn7 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        r19 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        btn16 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        r20 = new javax.swing.JRadioButton();
        r5 = new javax.swing.JRadioButton();
        btn37 = new javax.swing.JButton();
        r6 = new javax.swing.JRadioButton();
        r39 = new javax.swing.JRadioButton();
        btn26 = new javax.swing.JButton();
        r21 = new javax.swing.JRadioButton();
        r22 = new javax.swing.JRadioButton();
        r23 = new javax.swing.JRadioButton();
        btn5 = new javax.swing.JButton();
        r24 = new javax.swing.JRadioButton();
        r7 = new javax.swing.JRadioButton();
        r25 = new javax.swing.JRadioButton();
        r40 = new javax.swing.JRadioButton();
        r26 = new javax.swing.JRadioButton();
        btn11 = new javax.swing.JButton();
        r27 = new javax.swing.JRadioButton();
        r8 = new javax.swing.JRadioButton();
        r28 = new javax.swing.JRadioButton();
        btn30 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        r9 = new javax.swing.JRadioButton();
        btn21 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        r10 = new javax.swing.JRadioButton();
        btn22 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        r29 = new javax.swing.JRadioButton();
        r30 = new javax.swing.JRadioButton();
        r31 = new javax.swing.JRadioButton();
        btn28 = new javax.swing.JButton();
        r32 = new javax.swing.JRadioButton();
        warn_button = new javax.swing.JButton();
        warn_textfield = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        s911_button.setText("911 좌석조회");
        s911_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s911_buttonActionPerformed(evt);
            }
        });

        s915_button.setText("915 좌석조회");
        s915_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s915_buttonActionPerformed(evt);
            }
        });

        s916_button.setText("916 좌석조회");
        s916_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s916_buttonActionPerformed(evt);
            }
        });

        s918_button.setText("918 좌석조회");
        s918_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s918_buttonActionPerformed(evt);
            }
        });

        reservation_button.setText("예약");
        reservation_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservation_buttonActionPerformed(evt);
            }
        });

        start_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00" }));
        start_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_comboboxActionPerformed(evt);
            }
        });

        end_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00" }));

        jLabel2.setText("시작시간");

        jLabel4.setText("끝시간");

        class_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        jLabel5.setText("예약할 실습실");

        btn33.setText("33");

        buttonGroup1.add(r11);
        r11.setText("11");

        buttonGroup1.add(r12);
        r12.setText("12");

        buttonGroup1.add(r13);
        r13.setText("13");

        buttonGroup1.add(r14);
        r14.setText("14");

        search_butoon.setText("예약 현황 조회하기");
        search_butoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_butoonActionPerformed(evt);
            }
        });

        buttonGroup1.add(r15);
        r15.setText("15");

        r_textfield.setColumns(20);
        r_textfield.setRows(5);
        r_textfield.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                r_textfieldAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        r_textfield.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                r_textfieldInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(r_textfield);

        buttonGroup1.add(r16);
        r16.setText("16");

        buttonGroup1.add(r17);
        r17.setText("17");

        btn24.setText("24");

        buttonGroup1.add(r18);
        r18.setText("18");

        buttonGroup1.add(r33);
        r33.setText("33");

        btn17.setText("17");

        btn29.setText("29");

        buttonGroup1.add(r34);
        r34.setText("34");

        btn9.setText("9");

        cancle_button.setText("예약 취소하기");
        cancle_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancle_buttonActionPerformed(evt);
            }
        });

        buttonGroup1.add(r35);
        r35.setText("35");

        buttonGroup1.add(r1);
        r1.setText("1");

        buttonGroup1.add(r36);
        r36.setText("36");

        buttonGroup1.add(r37);
        r37.setText("37");

        buttonGroup1.add(r38);
        r38.setText("38");

        btn15.setText("15");

        btn1.setText("1");

        btn25.setText("25");
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });

        btn18.setText("18");

        buttonGroup1.add(r2);
        r2.setText("2");

        btn19.setText("19");

        btn6.setText("6");

        btn20.setText("20");

        buttonGroup1.add(r3);
        r3.setText("3");

        btn7.setText("7");

        btn10.setText("10");

        buttonGroup1.add(r19);
        r19.setText("19");

        buttonGroup1.add(r4);
        r4.setText("4");

        btn16.setText("16");

        btn39.setText("39");

        buttonGroup1.add(r20);
        r20.setText("20");

        buttonGroup1.add(r5);
        r5.setText("5");

        btn37.setText("37");

        buttonGroup1.add(r6);
        r6.setText("6");

        buttonGroup1.add(r39);
        r39.setText("39");

        btn26.setText("26");

        buttonGroup1.add(r21);
        r21.setText("21");

        buttonGroup1.add(r22);
        r22.setText("22");

        buttonGroup1.add(r23);
        r23.setText("23");

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(r24);
        r24.setText("24");

        buttonGroup1.add(r7);
        r7.setText("7");

        buttonGroup1.add(r25);
        r25.setText("25");

        buttonGroup1.add(r40);
        r40.setText("40");

        buttonGroup1.add(r26);
        r26.setText("26");

        btn11.setText("11");

        buttonGroup1.add(r27);
        r27.setText("27");

        buttonGroup1.add(r8);
        r8.setText("8");

        buttonGroup1.add(r28);
        r28.setText("28");

        btn30.setText("30");

        btn40.setText("40");

        btn36.setText("36");

        btn38.setText("38");

        btn4.setText("4");

        btn12.setText("12");

        btn35.setText("35");

        btn31.setText("31");

        btn13.setText("13");
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });

        btn3.setText("3");

        btn34.setText("34");

        btn32.setText("32");
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });

        btn14.setText("14");

        buttonGroup1.add(r9);
        r9.setText("9");

        btn21.setText("21");

        btn2.setText("2");

        btn8.setText("8");

        buttonGroup1.add(r10);
        r10.setText("10");

        btn22.setText("22");

        btn27.setText("27");
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });

        btn23.setText("23");

        buttonGroup1.add(r29);
        r29.setText("29");

        buttonGroup1.add(r30);
        r30.setText("30");

        buttonGroup1.add(r31);
        r31.setText("31");

        btn28.setText("28");

        buttonGroup1.add(r32);
        r32.setText("32");

        warn_button.setText("좌석 경고 횟수 조회");
        warn_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warn_buttonActionPerformed(evt);
            }
        });

        exit.setText("이전");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel3.setText("실습실 좌석 조회 및 예약");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(class_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(start_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(end_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(s915_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(s916_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(s918_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(s911_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(r6)
                                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r9))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r10))
                                    .addGap(65, 65, 65)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(r14)
                                        .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(66, 66, 66)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r15))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(r16)
                                        .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(r7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(r8)
                                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(reservation_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r18))
                            .addGap(65, 65, 65)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r19))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r20))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r21))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r22))
                            .addGap(66, 66, 66)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r23))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(r24)
                                .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r33))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r34))
                            .addGap(65, 65, 65)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r35))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r36))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r37))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r38))
                            .addGap(66, 66, 66)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn39, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r39))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(r40)
                                .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(r25)
                                .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r26))
                            .addGap(65, 65, 65)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r27))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r28))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn29, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r29))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r30))
                            .addGap(66, 66, 66)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(r31))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(r32)
                                .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exit)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(search_butoon)
                                .addGap(18, 18, 18)
                                .addComponent(cancle_button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(warn_button)
                                .addGap(18, 18, 18)
                                .addComponent(warn_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(75, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(523, 523, 523)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(end_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(start_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(class_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(reservation_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s916_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s918_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s911_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s915_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r1)
                            .addComponent(r2)
                            .addComponent(r3)
                            .addComponent(r4)
                            .addComponent(r5)
                            .addComponent(r6)
                            .addComponent(r7)
                            .addComponent(r8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r9)
                            .addComponent(r10)
                            .addComponent(r11)
                            .addComponent(r12)
                            .addComponent(r13)
                            .addComponent(r14)
                            .addComponent(r15)
                            .addComponent(r16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r17)
                            .addComponent(r18)
                            .addComponent(r19)
                            .addComponent(r20)
                            .addComponent(r21)
                            .addComponent(r22)
                            .addComponent(r23)
                            .addComponent(r24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r25)
                            .addComponent(r26)
                            .addComponent(r27)
                            .addComponent(r28)
                            .addComponent(r29)
                            .addComponent(r30)
                            .addComponent(r31)
                            .addComponent(r32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search_butoon)
                            .addComponent(cancle_button))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(warn_button)
                            .addComponent(warn_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r33)
                            .addComponent(r34)
                            .addComponent(r35)
                            .addComponent(r36)
                            .addComponent(r37)
                            .addComponent(r38)
                            .addComponent(r39)
                            .addComponent(r40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn38, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn39, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(exit)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void s911_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s911_buttonActionPerformed
        // TODO add your handling code here:

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        Date start = null;
        Date end = null;
        Date current = null;

        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from reservation where class_num='911'"));

            ArrayList seat_list = new ArrayList<String>();
            ArrayList starttime_list = new ArrayList<String>();
            ArrayList endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }

            String btn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

            JButton b[] = {btn1, btn2, btn3, btn4, btn5,
                btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15,
                btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25,
                btn26, btn27, btn28, btn27, btn28,
                btn29, btn30, btn31, btn32, btn35,
                btn36, btn37, btn38, btn39, btn40};

            for (int i = 0; i < seat_list.size(); i++) {
                start = f.parse(starttime_list.get(i).toString());
                end = f.parse(endtime_list.get(i).toString());
                current = f.parse(SimpleDateFormat.format(currentTime));

                if (current.compareTo(start) >= 0) {
                    if (end.compareTo(current) >= 0) {
                        for (int j = 0; j < btn.length; j++) {
                            if (btn[j].equals(seat_list.get(i))) {
                                System.out.println(btn[j]);
                                b[j].setBackground(Color.yellow);
                            }
                        }
                    }
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_s911_buttonActionPerformed

    private void s915_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s915_buttonActionPerformed
        // TODO add your handling code here:
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        Date start = null;
        Date end = null;
        Date current = null;

        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from reservation where class_num='915'"));

            ArrayList seat_list = new ArrayList<String>();
            ArrayList starttime_list = new ArrayList<String>();
            ArrayList endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }

            String btn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

            JButton b[] = {btn1, btn2, btn3, btn4, btn5,
                btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15,
                btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25,
                btn26, btn27, btn28, btn27, btn28,
                btn29, btn30, btn31, btn32, btn35,
                btn36, btn37, btn38, btn39, btn40};

            for (int i = 0; i < seat_list.size(); i++) {
                start = f.parse(starttime_list.get(i).toString());
                end = f.parse(endtime_list.get(i).toString());
                current = f.parse(SimpleDateFormat.format(currentTime));

                if (current.compareTo(start) >= 0) {
                    if (end.compareTo(current) >= 0) {
                        for (int j = 0; j < btn.length; j++) {
                            if (btn[j].equals(seat_list.get(i))) {
                                System.out.println(btn[j]);
                                b[j].setBackground(Color.red);
                            }
                        }
                    }
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_s915_buttonActionPerformed

    private void s916_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s916_buttonActionPerformed
        // TODO add your handling code here:
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        Date start = null;
        Date end = null;
        Date current = null;

        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from reservation where class_num='916'"));

            ArrayList seat_list = new ArrayList<String>();
            ArrayList starttime_list = new ArrayList<String>();
            ArrayList endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }

            String btn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

            JButton b[] = {btn1, btn2, btn3, btn4, btn5,
                btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15,
                btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25,
                btn26, btn27, btn28, btn27, btn28,
                btn29, btn30, btn31, btn32, btn35,
                btn36, btn37, btn38, btn39, btn40};

            for (int i = 0; i < seat_list.size(); i++) {
                start = f.parse(starttime_list.get(i).toString());
                end = f.parse(endtime_list.get(i).toString());
                current = f.parse(SimpleDateFormat.format(currentTime));

                if (current.compareTo(start) >= 0) {
                    if (end.compareTo(current) >= 0) {
                        for (int j = 0; j < btn.length; j++) {
                            if (btn[j].equals(seat_list.get(i))) {
                                System.out.println(btn[j]);
                                b[j].setBackground(Color.blue);
                            }
                        }
                    }
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_s916_buttonActionPerformed

    private void s918_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s918_buttonActionPerformed
        // TODO add your handling code here:
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        Date start = null;
        Date end = null;
        Date current = null;

        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from reservation where class_num='918'"));

            ArrayList seat_list = new ArrayList<String>();
            ArrayList starttime_list = new ArrayList<String>();
            ArrayList endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }

            String btn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

            JButton b[] = {btn1, btn2, btn3, btn4, btn5,
                btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15,
                btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25,
                btn26, btn27, btn28, btn27, btn28,
                btn29, btn30, btn31, btn32, btn35,
                btn36, btn37, btn38, btn39, btn40};

            for (int i = 0; i < seat_list.size(); i++) {
                start = f.parse(starttime_list.get(i).toString());
                end = f.parse(endtime_list.get(i).toString());
                current = f.parse(SimpleDateFormat.format(currentTime));

                if (current.compareTo(start) >= 0) {
                    if (end.compareTo(current) >= 0) {
                        for (int j = 0; j < btn.length; j++) {
                            if (btn[j].equals(seat_list.get(i))) {
                                System.out.println(btn[j]);
                                b[j].setBackground(Color.green);
                            }
                        }
                    }
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_s918_buttonActionPerformed

    private void reservation_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservation_buttonActionPerformed
        // TODO add your handling code here:

        LoginPage lg = new LoginPage();

        class_num = Integer.parseInt(class_combobox.getSelectedItem().toString());
        starttime = start_combobox.getSelectedItem().toString();
        endtime = end_combobox.getSelectedItem().toString();

        int count = 0;

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            check_date();

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("select l_endtime, l_starttime from schedule where l_day='" + k_date + "' and class_num='" + class_num + "'");

            ArrayList start_list = new ArrayList<String>();
            ArrayList end_list = new ArrayList<String>();

            while (rs.next()) {
                start_list.add(rs.getString("l_starttime"));
                end_list.add(rs.getString("l_endtime"));
            }
            // 배열에 넣을 시간을 임시 저장할 배열
            int[] schedule = new int[25];
            int tmp = 0;

            // 수업이 있는 시간표 추출
            for (int i = 0; i < end_list.size(); i++) {

                int start = Integer.parseInt(start_list.get(i).toString().substring(0, 2));
                int end = Integer.parseInt(end_list.get(i).toString().substring(0, 2));

                tmp = end - start;

                // 시간표 입력 ex)11시~13시: schedule[11] == 1, schedule[12] == 1
                for (int k = start; k < start + tmp; k++) {
                    schedule[k] = 1;
                }
            }

            int s_start = Integer.valueOf(starttime.substring(0, 2));
            int s_end = Integer.valueOf(endtime.substring(0, 2));
            int temp = s_end - s_start;

            boolean final_seat = r_seat();

            for (int j = s_start; j < s_end + 1; j++) { // 수업 시간표 및 세미나 겹치는지 확인
                if (schedule[j] == 1) {
                    JOptionPane.showMessageDialog(null, "수업이 있는 강의실입니다.");
                    break;
                } else if (count == temp) {
                    if (9 <= s_start && s_end < 17) { // 9시~17시 사이에 예약일 경우 (approve=1)
                        if (final_seat) {
                            Reservation r = new Reservation();
                            ResdbUpdate res = new ResdbUpdate(r);
                            r.setMeasurements(lg.getName(), lg.getID(), class_num, seat_num, starttime, endtime, final_day, 0, 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "예약되어 있는 좌석입니다.");
                        }
                    } else { // 9시~17시 사이에 예약이 아닐 경우 (approve=0)
                        if (final_seat) {
                            r_seat();
                            cut_class();
                        } else {
                            JOptionPane.showMessageDialog(null, "예약되어 있는 좌석입니다.");
                        }
                    }
                }
                count++;
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_reservation_buttonActionPerformed

    private void start_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_start_comboboxActionPerformed

    private void search_butoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_butoonActionPerformed
        // TODO add your handling code here:
        // 예약 승인이 된 사람만 조회가 가능하도록 구현 (textArea에 출력되도록 수정 필요)

        String admin = null;

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        check_date();

        try {
            LoginPage lg = new LoginPage();

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from reservation where id ='" + lg.getID() + "'"));

            ArrayList id_list = new ArrayList<String>();
            ArrayList seat_list = new ArrayList<String>();
            ArrayList starttime_list = new ArrayList<String>();
            ArrayList endtime_list = new ArrayList<String>();
            ArrayList day_list = new ArrayList<String>();
            ArrayList admin_list = new ArrayList<String>();
            ArrayList approve_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
                day_list.add(rs.getString("day"));
                admin_list.add(rs.getInt("admin"));
                approve_list.add(rs.getString("approve"));
            }

            for (int k = 0; k < id_list.size(); k++) {

                if ((approve_list.get(k).toString().equals("1"))) {
                    if ((admin_list.get(k).toString().equals("0"))) {
                        r_textfield.setText("아이디 : " + id_list.get(k) + '\n'
                                + "예약 좌석 번호 : " + seat_list.get(k) + '\n'
                                + "예약 시작 시간 : " + starttime_list.get(k) + '\n'
                                + "예약 종료 시간 : " + endtime_list.get(k) + '\n'
                                + "예약 날짜 : " + final_date + '\n'
                                + "관리자 여부 : 관리자가 아닙니다." + '\n'
                                + "강의실 규칙 : 실습실 내에서 물을 제외한 모든 음료 및 음식은 취식 불가입니다." + '\n'
                                + "공지사항 및 주의사항 : 사용 후 컴퓨터 전원 및 멀티탭 전원을 반드시 내려 주십시오."
                        );
                    } else {
                        r_textfield.setText("아이디 : " + id_list.get(k) + '\n'
                                + "예약 좌석 번호 : " + seat_list.get(k) + '\n'
                                + "예약 시작 시간 : " + starttime_list.get(k) + '\n'
                                + "예약 종료 시간 : " + endtime_list.get(k) + '\n'
                                + "예약 날짜 : " + final_date + '\n'
                                + "관리자 여부 : 관리자 입니다." + '\n'
                                + "강의실 규칙 : 실습실 내에서 물을 제외한 모든 음료 및 음식은 취식 불가입니다." + '\n'
                                + "공지사항 및 주의사항 : 사용 후 컴퓨터 전원 및 멀티탭 전원을 반드시 내려 주십시오."
                        );
                    }

                }
            }
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "예약되어있지 않은 학생입니다.");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_search_butoonActionPerformed

    private void r_textfieldAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_r_textfieldAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_r_textfieldAncestorAdded

    private void r_textfieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_r_textfieldInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_r_textfieldInputMethodTextChanged

    private void cancle_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancle_buttonActionPerformed
        // TODO add your handling code here:
        Cancle c = new Cancle();
        CancledbUpdate cancle = new CancledbUpdate(c);
        c.setMeasurements(lg.getID());
    }//GEN-LAST:event_cancle_buttonActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn27ActionPerformed

    private void warn_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warn_buttonActionPerformed
        // TODO add your handling code here:
        String admin = null;

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        check_date();

        try {
            LoginPage lg = new LoginPage();

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery(("select * from client where id ='" + lg.getID() + "'"));

            ArrayList warn_list = new ArrayList<String>();

            while (rs.next()) {
                warn_list.add(rs.getString("warning"));
            }

            for (int i = 0; i < warn_list.size(); i++) {
                warn_textfield.setText((String) warn_list.get(i));
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_warn_buttonActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeatSearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeatSearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeatSearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeatSearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeatSearchPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JButton btn1;
    protected static javax.swing.JButton btn10;
    protected static javax.swing.JButton btn11;
    protected static javax.swing.JButton btn12;
    protected static javax.swing.JButton btn13;
    protected static javax.swing.JButton btn14;
    protected static javax.swing.JButton btn15;
    protected static javax.swing.JButton btn16;
    protected static javax.swing.JButton btn17;
    protected static javax.swing.JButton btn18;
    protected static javax.swing.JButton btn19;
    protected static javax.swing.JButton btn2;
    protected static javax.swing.JButton btn20;
    protected static javax.swing.JButton btn21;
    protected static javax.swing.JButton btn22;
    protected static javax.swing.JButton btn23;
    protected static javax.swing.JButton btn24;
    protected static javax.swing.JButton btn25;
    protected static javax.swing.JButton btn26;
    protected static javax.swing.JButton btn27;
    protected static javax.swing.JButton btn28;
    protected static javax.swing.JButton btn29;
    protected static javax.swing.JButton btn3;
    protected static javax.swing.JButton btn30;
    protected static javax.swing.JButton btn31;
    protected static javax.swing.JButton btn32;
    protected static javax.swing.JButton btn33;
    protected static javax.swing.JButton btn34;
    protected static javax.swing.JButton btn35;
    protected static javax.swing.JButton btn36;
    protected static javax.swing.JButton btn37;
    protected static javax.swing.JButton btn38;
    protected static javax.swing.JButton btn39;
    protected static javax.swing.JButton btn4;
    protected static javax.swing.JButton btn40;
    protected static javax.swing.JButton btn5;
    protected static javax.swing.JButton btn6;
    protected static javax.swing.JButton btn7;
    protected static javax.swing.JButton btn8;
    protected static javax.swing.JButton btn9;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancle_button;
    private javax.swing.JComboBox<String> class_combobox;
    protected static javax.swing.JComboBox<String> end_combobox;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r10;
    private javax.swing.JRadioButton r11;
    private javax.swing.JRadioButton r12;
    private javax.swing.JRadioButton r13;
    private javax.swing.JRadioButton r14;
    private javax.swing.JRadioButton r15;
    private javax.swing.JRadioButton r16;
    private javax.swing.JRadioButton r17;
    private javax.swing.JRadioButton r18;
    private javax.swing.JRadioButton r19;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r20;
    private javax.swing.JRadioButton r21;
    private javax.swing.JRadioButton r22;
    private javax.swing.JRadioButton r23;
    private javax.swing.JRadioButton r24;
    private javax.swing.JRadioButton r25;
    private javax.swing.JRadioButton r26;
    private javax.swing.JRadioButton r27;
    private javax.swing.JRadioButton r28;
    private javax.swing.JRadioButton r29;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r30;
    private javax.swing.JRadioButton r31;
    private javax.swing.JRadioButton r32;
    private javax.swing.JRadioButton r33;
    private javax.swing.JRadioButton r34;
    private javax.swing.JRadioButton r35;
    private javax.swing.JRadioButton r36;
    private javax.swing.JRadioButton r37;
    private javax.swing.JRadioButton r38;
    private javax.swing.JRadioButton r39;
    private javax.swing.JRadioButton r4;
    private javax.swing.JRadioButton r40;
    private javax.swing.JRadioButton r5;
    private javax.swing.JRadioButton r6;
    private javax.swing.JRadioButton r7;
    private javax.swing.JRadioButton r8;
    private javax.swing.JRadioButton r9;
    private javax.swing.JTextArea r_textfield;
    private javax.swing.JButton reservation_button;
    private javax.swing.JButton s911_button;
    private javax.swing.JButton s915_button;
    private javax.swing.JButton s916_button;
    private javax.swing.JButton s918_button;
    private javax.swing.JButton search_butoon;
    protected static javax.swing.JComboBox<String> start_combobox;
    private javax.swing.JButton warn_button;
    private javax.swing.JTextField warn_textfield;
    // End of variables declaration//GEN-END:variables
}
