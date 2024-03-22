package NAVER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NaverSQL {
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
            con.close();        //접속한 DB를 닫다(close)
            //발생할 수 있는 예외 -> 접속을 하지 않은 상태에서 해제를 할 경우
            System.out.println("성공적으로 연동을 해제했습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //회원가입 메소드 : NaverMain에서 입력 받은 정보(NaverMember member)를 매개변수로 사용
    public void memberJoin(NaverMember member) {
        try {
            //(1)sql문 작성 : 입력할 데이터 대신 '?' 작성
            String sql = "INSERT INTO NAVER VALUES(?, ?, ?, ?, ?, ?, ?)";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)sql문에서 '?' 데이터 처리
            //sql문의 1번째 '?'에 member객체의 nId값(getnId()로 불러온)값을 입력
            pstmt.setString(1, member.getnId());
            pstmt.setString(2, member.getnPw());
            pstmt.setString(3, member.getnEmail());
            pstmt.setString(4, member.getnName());
            pstmt.setString(5, member.getnBirth());
            pstmt.setString(6, member.getnGender());
            pstmt.setString(7, member.getnPhone());
            //물음표의 번호, getter로 받아오는 데이터(이름 꼭 확인)

            //(4)실행 : insert, update, delete(int result), select(ResultSet rs)
            int result = pstmt.executeUpdate();

            //(5)결과처리
            if(result > 0) {
                System.out.println("가입 성공!");
            } else {
                System.out.println("가입 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberList() {
        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM NAVER";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력(?가 없을 경우 생략)

            //(4)실행 : select -> rs
            rs = pstmt.executeQuery();

            //(5)결과
            while (rs.next()) {
                System.out.print("아이디 : " + rs.getString(1));
                System.out.print(" | 이름 : " + rs.getString(4));
                System.out.println(" | 연락처 : " + rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean memberLogin(String nId, String nPw) {
        boolean result = false;

        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM NAVER WHERE NID=? AND NPW=?";

            //(2)DB준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력
            pstmt.setString(1, nId);
            pstmt.setString(2, nPw);

            //(4)실행
            rs = pstmt.executeQuery();

            //(5)결과
            result = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void myInfo(String nId) {
        try {
            //(1)sql문 작성
            String sql = "SELECT * FROM NAVER WHERE NID = ?";
            //시간제거      String sql = "SELECT NID, NPW, NEMAIL, NNAME,TO_CHAR(NBIRTH, 'YYYY-MM-DD'), NGENDER, NPHONE FROM NAVER WHERE NID = ?";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력
            pstmt.setString(1, nId);

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
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //회원 수정 메소드(가입 메소드와 매우 비슷하다!)
    public void memberModify(NaverMember member) {
        try {
            //(1)sql문 작성 : 입력할 데이터 대신 '?' 작성
            String sql = "UPDATE NAVER SET NPW=?, NEMAIL=?, NNAME=?, NBIRTH=?, NGENDER=?, NPHONE=? WHERE NID=?";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)sql문에서 '?' 데이터 처리
            //수정할 컬럼들
            pstmt.setString(1, member.getnPw());
            pstmt.setString(2, member.getnEmail());
            pstmt.setString(3, member.getnName());
            pstmt.setString(4, member.getnBirth());
            pstmt.setString(5, member.getnGender());
            pstmt.setString(6, member.getnPhone());

            //기준이 되는 컬럼 NID가 7번째 '?'
            pstmt.setString(7, member.getnId());

            //(4)실행 : insert, update, delete(int result), select(ResultSet rs)
            int result = pstmt.executeUpdate();

            //(5)결과처리
            if(result > 0) {
                System.out.println("수정 성공!");
            } else {
                System.out.println("수정 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberDelete(String nId) {
        try {
            //(1)
            String sql = "DELETE FROM NAVER WHERE NID=?";

            //(2)
            pstmt = con.prepareStatement(sql);

            //(3)
            pstmt.setString(1, nId);

            //(4)
            int result = pstmt.executeUpdate();

            //(5)
            if(result > 0) {
                System.out.println("삭제 성공!");
            } else {
                System.out.println("삭제 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}