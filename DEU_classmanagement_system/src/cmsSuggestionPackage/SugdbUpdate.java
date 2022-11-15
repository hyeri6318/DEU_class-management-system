/*
작성자 : 이혜리, 윤채민
기능 : SugObserver를 통해 SugSubjet의 이벤트를 감지하도록 구현함.
 */
package cmsSuggestionPackage;

import cms.ConnectDB.ConnectDB;
import static cmsSuggestionPackage.CheckSuggestionPage.suggest_table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 이혜리, 윤채민
 */
public class SugdbUpdate implements SugObserver {

    int suggestNum;
    String id;
    int classNum;
    String title;
    String content;

    public SugSubject suggest;

    public SugdbUpdate(SugSubject suggest) {
        this.suggest = suggest;
        suggest.registerObserver(this);
    }

    @Override
    public void update(int suggestNum, String id, int classNum, String title, String content) {
        this.suggestNum = suggestNum;
        this.id = id;
        this.classNum = classNum;
        this.title = title;
        this.content = content;

        display();
    }

    public void display() {
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.getConnection();

            ps = conn.prepareStatement("insert into suggestion values(?,?,?,?,?)");
            ps.setInt(1, suggestNum);
            ps.setString(2, id);
            ps.setInt(3, classNum);
            ps.setString(4, title);
            ps.setString(5, content);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "불편사항 제보가 완료되었습니다.");

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
