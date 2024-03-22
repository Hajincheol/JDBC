package JDBCTest;

import java.sql.*;

public class CRUD {
    /*
        C(CREATE) : 생성
        R(READ) : 조회
        U(UPDATE) : 수정
        D(DELETE) ; 삭제
    */
    //DB에 접속하기 위한 Connection 객체 con
    Connection con;

    //Java에서 작성한 sql문을 DB로 전달하기 위한 Statement 객체 stmt
    Statement stmt;

    //DB에서 실행된 결과를 저장하는 Result 객체 rs
    ResultSet rs;

    //[1] DB에 접속하기 위한 메소드
    public void connect() {
        con = DBConnection.DBConnect();
        //DBConnection클래스의 DBConnect() 메소드의 return con 값이다.
    }

    //[2]C : DB에 데이터를 저장하기 위한 메소드
    public void insert() {
        String sql = "INSERT INTO JDBC VALUES('Java', 17)";
        String cud = "입력";
        CUD(sql, cud);
    }

    //[3]R : DB에 있는 데이터를 조회하기 위한 메소드
    public void select() {
        try {
            //(1)화면 준비
            stmt = con.createStatement();

            //(2)sql문 작성
            String sql = "SELECT * FROM JDBC";

            //(3)실행 결과를 담기 위한 변수 rs
            rs = stmt.executeQuery(sql);
            //검색한 결과를 담기 윈한 변수 rs(ResultSet타입)
            //rs에 결과를 담을때는 stmt.executeQuery(sql)을 사용한다.
            //insert, update, delete -> stmt.executeQuery(sql) : int result

            //(4)결과 처리
            while(rs.next()) {
                System.out.println("DATA1 : " + rs.getString(1));
                System.out.println("DATA2 : " + rs.getInt(2));
                System.out.println();
            }
            //rs.next() : boolean 타입, 데이터의 갯수만큼 반복문을 실행
            //데이터가 존재하면 true, 데이터가 존재하지 않으면 false
            //ex) 데이터가 5개 존재하면 5번동안은 true, 6번째부터 false값을 갖게 된다.

            //rs.getString(1) : 첫번째 컬럼의 값, 컬럼의 데이터 타입이 문자열(NVARCHAR2)일때
            //rs.getInt(2)    : 두번째 컬럼의 값, 컬럼의 데이터 타입이 정수형(NUMBER)일때
            //컬럼의 데이터가 실수(소숫점)일땐 rs.getDouble()을 사용

         } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //[4]U : DB에 있는 데이터를 수정하기 위한 메소드
    public void update() {
        String sql = "UPDATE JDBC SET DATA2 = 21 WHERE DATA1 = 'Java'";
        String cud = "수정";
        CUD(sql, cud);
    }

    //[5]D : DB에 있는 데이터를 삭제하기 위한 메소드
    public void delete() {
        String sql = "DELETE FROM JDBC WHERE DATA2 = 21";
        String cud = "삭제";
        CUD(sql, cud);
    }

    public void CUD(String sql, String cud) {
        try {
            //(1)화면준비
            stmt = con.createStatement();

            //(3)실행
            int result = stmt.executeUpdate(sql);

            //(4)결과
            if(result > 0) {
                System.out.println(cud + " 성공");
            } else {
                System.out.println(cud + " 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //[6]DB접속 해제
    public void conClose() {
        try {
            con.close();
            System.out.println("DB접속 해제");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectEMP() {
        try {
            //(1)화면 구현
            stmt = con.createStatement();

            //(2)sql문 작성
            String sql = "SELECT * FROM EMP";

            //(3)실행
            rs = stmt.executeQuery(sql);

            //(4)결과 처리
            /*
            while(rs.next()) {
                System.out.print("EMPNO : " + rs.getInt(1));
                System.out.print(" | ENAME : " + rs.getString(2));
                System.out.print(" | JOB : " + rs.getString(3));
                System.out.print(" | MGR : " + rs.getInt(4));
                System.out.print(" | HIREDATE : " + rs.getDate(5));
                System.out.print(" | SAL : " + rs.getDouble(6));
                System.out.print(" | COMM : " + rs.getDouble(7));
                System.out.println(" | DEPTNO : " + rs.getInt(8));
                System.out.println();
            }
            */

            String[] empTable = {"EMPNO", "ENAME", "JOB", "MGR", "HIREDATE", "SAL", "COMM", "DEPTNO"};

            while(rs.next()) {
                for(int i=0; i<empTable.length; i++) {
                    System.out.println(empTable[i] + " : " + rs.getObject(i+1));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}