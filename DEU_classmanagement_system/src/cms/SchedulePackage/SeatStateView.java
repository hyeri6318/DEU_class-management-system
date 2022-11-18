package cms.SchedulePackage;

import cms.ConnectDB.ConnectDB;
import cms.ResAssistantPackage.PageAss;
import cms.UserPackage.LoginPage;
import cms.WarningPackage.WarndbUpdate;
import cms.WarningPackage.Warning;
import java.awt.Color;
import java.sql.Connection;
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
 * 정수연
 * 실습실 상태 조회(915호) 
 */
public class SeatStateView extends javax.swing.JFrame {

    public SeatStateView() {
        initComponents();
    }
  
    LoginPage lg = new LoginPage();

    String name;
    String id;
    int seat_num;
    int admin;
    int approve;
    String pw;
    String userType;
    String warnNum;
    String telNum;
    String email;

    String final_date;
    String final_day;
    

    public boolean student_check(){  // 학생 정보 체크
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Client where user_type = 1");  // 학생 정보 조회

            ArrayList<String> id_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
            }

            for (int i = 0; i < id_list.size(); i++) {
                if (lg.getID().equals(id_list.get(i))) {   
                    return false;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean warning_check(){  // 경고 횟수 체크
        // 경고 횟수가 3번 이상이면 예약 불가능
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Client where warning>=3");  // 학생 정보 조회

            ArrayList<String> id_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
            }

            for (int i = 0; i < id_list.size(); i++) {
                if (lg.getID().equals(id_list.get(i))) {   
                    return false;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
        
    }
    
    public boolean r_check() {  // 예약 여부 확인
        ConnectDB db = new ConnectDB();
        java.sql.Connection conn = null;
        java.sql.Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Reservation");

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

    public void r_seat() {   // 좌석 선택 여부 확인
        ConnectDB db = new ConnectDB();
        java.sql.Connection conn = null;
        java.sql.Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Reservation");

            ArrayList<String> seat_list = new ArrayList<String>();
            ArrayList<String> starttime_list = new ArrayList<String>();
            ArrayList<String> endtime_list = new ArrayList<String>();

            while (rs.next()) {
                seat_list.add(rs.getString("seat_num"));
                starttime_list.add(rs.getString("r_starttime"));
                endtime_list.add(rs.getString("r_endtime"));
            }
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
                    for (int j = 0; j < seat_list.size(); j++) {
                        if (rbtn[i].equals(seat_list.get(j))) {
                            JOptionPane.showMessageDialog(null, "이미 예약된 좌석입니다.");
                        } else {
                            // 날짜 비교

                        }
                    }
                    seat_num = Integer.parseInt(r[i].getText());
                    System.out.println(seat_num);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void check_date() {  // 날짜, 요일 추출을 위한 메서드

        Calendar c = Calendar.getInstance();
        int year;
        int month;
        int date;
        int day;
        String k_date = null;

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
            System.out.println("16시 30분 이전");
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton6 = new javax.swing.JToggleButton();
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
        jLabel1 = new javax.swing.JLabel();
        s911_button = new javax.swing.JButton();
        s915_button = new javax.swing.JButton();
        s916_button = new javax.swing.JButton();
        s918_button = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        r11 = new javax.swing.JRadioButton();
        r12 = new javax.swing.JRadioButton();
        r13 = new javax.swing.JRadioButton();
        r14 = new javax.swing.JRadioButton();
        r15 = new javax.swing.JRadioButton();
        r16 = new javax.swing.JRadioButton();
        r17 = new javax.swing.JRadioButton();
        btn24 = new javax.swing.JButton();
        r18 = new javax.swing.JRadioButton();
        r33 = new javax.swing.JRadioButton();
        btn17 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        r34 = new javax.swing.JRadioButton();
        btn9 = new javax.swing.JButton();
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
        btn4 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        r9 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        btn7 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        r19 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        btn16 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        r20 = new javax.swing.JRadioButton();
        btn21 = new javax.swing.JButton();
        r5 = new javax.swing.JRadioButton();
        btn2 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        r10 = new javax.swing.JRadioButton();
        btn22 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        r29 = new javax.swing.JRadioButton();
        r30 = new javax.swing.JRadioButton();
        r31 = new javax.swing.JRadioButton();
        r6 = new javax.swing.JRadioButton();
        r39 = new javax.swing.JRadioButton();
        btn26 = new javax.swing.JButton();
        r21 = new javax.swing.JRadioButton();
        r22 = new javax.swing.JRadioButton();
        r23 = new javax.swing.JRadioButton();
        btn5 = new javax.swing.JButton();
        r24 = new javax.swing.JRadioButton();
        btn28 = new javax.swing.JButton();
        r7 = new javax.swing.JRadioButton();
        r32 = new javax.swing.JRadioButton();
        r25 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        warningBtn = new javax.swing.JButton();
        input_id = new javax.swing.JTextField();
        UndoBtn = new javax.swing.JButton();

        jToggleButton6.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        r40.setText("40");

        r26.setText("26");

        btn11.setText("11");

        r27.setText("27");

        r8.setText("8");

        r28.setText("28");

        btn30.setText("30");

        btn40.setText("40");

        btn36.setText("36");

        btn38.setText("38");

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel1.setText("좌석 조회");

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

        btn33.setText("33");

        r11.setText("11");

        r12.setText("12");

        r13.setText("13");

        r14.setText("14");

        r15.setText("15");

        r16.setText("16");

        r17.setText("17");

        btn24.setText("24");

        r18.setText("18");

        r33.setText("33");

        btn17.setText("17");

        btn29.setText("29");

        r34.setText("34");

        btn9.setText("9");

        r35.setText("35");

        r1.setText("1");

        r36.setText("36");

        r37.setText("37");

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

        r2.setText("2");

        btn19.setText("19");

        btn4.setText("4");

        btn6.setText("6");

        btn12.setText("12");

        btn20.setText("20");

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

        r9.setText("9");

        r3.setText("3");

        btn7.setText("7");

        btn10.setText("10");

        r19.setText("19");

        r4.setText("4");

        btn16.setText("16");

        btn39.setText("39");

        r20.setText("20");

        btn21.setText("21");

        r5.setText("5");

        btn2.setText("2");

        btn37.setText("37");

        btn8.setText("8");

        r10.setText("10");

        btn22.setText("22");

        btn27.setText("27");
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });

        btn23.setText("23");

        r29.setText("29");

        r30.setText("30");

        r31.setText("31");

        r6.setText("6");

        r39.setText("39");

        btn26.setText("26");

        r21.setText("21");

        r22.setText("22");

        r23.setText("23");

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        r24.setText("24");

        btn28.setText("28");

        r7.setText("7");

        r32.setText("32");

        r25.setText("25");

        jLabel6.setText("경고받을 학번 입력");

        warningBtn.setText("좌석경고");
        warningBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warningBtnActionPerformed(evt);
            }
        });

        input_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_idActionPerformed(evt);
            }
        });

        UndoBtn.setText("이전");
        UndoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(s915_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(s911_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(s916_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(s918_button, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(input_id, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(warningBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(43, 43, 43)
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
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(r7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(r8)
                                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(66, 66, 66)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(r15))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(r16)
                                                .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(UndoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                            .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(s915_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(s916_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(s918_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(s911_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(input_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(warningBtn)))
                .addGap(36, 36, 36)
                .addComponent(UndoBtn)
                .addContainerGap(22, Short.MAX_VALUE))
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
                                b[j].setBackground(Color.gray);
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
                                b[j].setBackground(Color.gray);
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
                                b[j].setBackground(Color.gray);
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
                                b[j].setBackground(Color.gray);
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

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn27ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void warningBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warningBtnActionPerformed
        // 좌석 경고 버튼

        LoginPage lg = new LoginPage();

        student_check();

        id = input_id.getText();

        Warning r = new Warning();
        WarndbUpdate war = new WarndbUpdate(r);
        r.setMeasurements(name, id, admin, approve, warnNum, pw, userType, telNum,  email);
    }//GEN-LAST:event_warningBtnActionPerformed

    private void input_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_idActionPerformed

    private void UndoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoBtnActionPerformed
        // 취소 버튼 구현 -> 조교 메인화면 이동
        PageAss view = new PageAss();
        view.setVisible(true);
        dispose();

    }//GEN-LAST:event_UndoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(SeatStateView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeatStateView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeatStateView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeatStateView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeatStateView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UndoBtn;
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
    private javax.swing.JTextField input_id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JToggleButton jToggleButton6;
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
    private javax.swing.JButton s911_button;
    private javax.swing.JButton s915_button;
    private javax.swing.JButton s916_button;
    private javax.swing.JButton s918_button;
    private javax.swing.JButton warningBtn;
    // End of variables declaration//GEN-END:variables
}
