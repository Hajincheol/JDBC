package GOOGLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoogleSQL {
    //DB연동 3객체
    Connection con;             //접속
    PreparedStatement pstmt;    //SQL문
    ResultSet rs;               //결과

    //DB접속 메소드
    public void connect() {
        //DB접속에 필요한 계정, 주소 정보를 가지고 오는 메소드
        con = DBC.DBConnect();
    }

    //DB해제 메소드
    public void conClose() {
        try {
            con.close();
            System.out.println("성공적으로 연동을 해제했습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberJoin(GoogleMember member) {
        try {
            //(1)sql문 작성 : 입력할 데이터 대신 '?' 작성
            String sql = "INSERT INTO GOOGLE VALUES(?, ?, ?, ?, ?, ?, ?)";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)sql문에서 '?' 데이터 처리
            pstmt.setString(1 , member.getgId());
            pstmt.setString(2 , member.getgPw());
            pstmt.setString(3 , member.getgEmail());
            pstmt.setString(4 , member.getgName());
            pstmt.setString(5 , member.getgBirth());
            pstmt.setString(6 , member.getgGender());
            pstmt.setString(7 , member.getgPhone());

            //(4)실행 및 결과
            if(pstmt.executeUpdate() > 0) {
                System.out.println("생성 성공!");
            } else {
                System.out.println("생성 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberList() {
        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM GOOGLE";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력(?가 없을 경우 생략)

            //(4)실행 : select -> rs
            rs = pstmt.executeQuery();

            //(5)결과
            while(rs.next()) {
                System.out.print("아이디 : " + rs.getString(1));
                System.out.print(" | 이름 : " + rs.getString(4));
                System.out.println(" | 연락처 : " + rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean memberLogin(String gId, String gPw) {
        boolean result = false;
        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM GOOGLE WHERE GID=? AND GPW=?";

            //(2)DB준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력
            pstmt.setString(1, gId);
            pstmt.setString(2, gPw);

            //(4)실행 및 결과
            if(pstmt.executeUpdate() > 0) {
                System.out.println("로그인 성공!");
                result = true;
            } else {
                System.out.println("로그인 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void myInfo(String gId) {
        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM GOOGLE WHERE GID=?";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력
            pstmt.setString(1, gId);

            //(4)실행
            rs = pstmt.executeQuery();

            //(5)결과
            if(rs.next()) {
                System.out.println("아이디 : " + rs.getString(1));
                System.out.println("비밀번호 : " + rs.getString(2));
                System.out.println("이메일 : " + rs.getString(3));
                System.out.println("이름 : " + rs.getString(4));
                System.out.println("생년월일 : " + rs.getString(5));
                System.out.println("성별 : " + rs.getString(6));
                System.out.println("연락처 : " + rs.getString(7));
            } else {
                System.out.println("실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberModify(GoogleMember member) {
        try {
            //(1)sql문 작성 : 입력할 데이터 대신 '?' 작성
            String sql = "UPDATE GOOGLE SET GPW=?, GEMAIL=?, GNAME=?, GBIRTH=?, GGENDER=?, GPHONE=? WHERE GID=?";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)sql문에서 '?' 데이터 처리
            pstmt.setString(1 , member.getgPw());
            pstmt.setString(2 , member.getgEmail());
            pstmt.setString(3 , member.getgName());
            pstmt.setString(4 , member.getgBirth());
            pstmt.setString(5 , member.getgGender());
            pstmt.setString(6 , member.getgPhone());
            pstmt.setString(7 , member.getgId());

            //(4)실행 및 결과
            if(pstmt.executeUpdate() > 0) {
                System.out.println("수정 성공!");
            } else {
                System.out.println("수정 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void memberDelete(String gId) {
        try {
            //(1)sql문 작성 : 입력할 데이터 대신 '?' 작성
            String sql = "DELETE FROM GOOGLE WHERE GID=?";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)sql문에서 '?' 데이터 처리
            pstmt.setString(1, gId);

            //(4)실행 및 결과
            if(pstmt.executeUpdate() > 0) {
                System.out.println("삭제 성공!");
            } else {
                System.out.println("삭제 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}