package cms.SchedulePackage;

import cms.SchedulePackage.SearchState;
import cms.ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
작성자: 정수연
기능: 실습실 상태 조회 불가능
 */
public class SearchUnavailable implements SearchState {
    @Override
    public void search(){  // 실습실 상태 조회 불가능
        System.out.println("실습실 상태 조회 불가능합니다");
    }
}
