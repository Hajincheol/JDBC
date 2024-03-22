package BANK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankSQL {
    //SQL클래스에서 CRUD를 사용하는 3가지 객체
    //DB에 접속하기 위한 연결(Connection) 객체 : con
    Connection con;

    //SQL 쿼리문 전달을 위한 (PreparedStatement)객체 : pstmt
    PreparedStatement pstmt;

    //DB에서 조회(select)한 결과(ResultSet)을 담을 객체 : rs
    ResultSet rs;

    //[1]DB 접속
    public void Connect() {
        con = DBC.DBConnect();
        System.out.println("DB 연동 성공");
    }

    //[2]DB 접속해제
    public void conClose() {
        try {
            con.close();
            System.out.println("DB 접속이 해제되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //고객번호 생성 메소드
    public int clientNumber() {
        int cNum = 0;
        //BANKCLIENT 테이블에서 (1)~(5)단계를 거쳐서 MAX(CNUM)을 구하시오
        try {
            String sql = "SELECT MAX(CNUM) FROM BANKCLIENT";
            pstmt = con.prepareStatement(sql);
            //(3)데이터 입력인데 sql문에 '?'가 없기 때문에 생략

            rs = pstmt.executeQuery();
            if(rs.next()) {
                cNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cNum;
    }

    //계좌번호 생성 메소드
    public String accountNumber() {
        String cAccount = null;

        //신한은행 계좌번호 : 110-xxx(3자리)-xxxxxx(6자리)
        //랜덤숫자 만들기 Math.rancom()
        //Math.random() : 0.000001~0.999999까지
        //(Math.random() * 9) : 0.0000009 ~ 9.99999998
        //(int)(Math.random() * 9) : 0~9 -> 0부터 9사이에 숫자가 무작위로 생성

        //110-
        cAccount = "110-";

        //110-xxx
        //반복문 3번 돌려서 0부터 9상이의 숫자를 추가(누적)
        for(int i=0; i<3; i++) {
            cAccount += (int)(Math.random() * 10);
        }

        //110-xxx-
        cAccount += "-";

        //110-xxx-xxxxxx
        for(int i=0; i<6; i++) {
            cAccount += (int)(Math.random() * 10);
        }

        return cAccount;
    }

    //고객 가입 메소드
    public void joinClient(BankClient client) {
        try {
            //회원가입과 동일
            //(1)sql문 작성
            String sql = "INSERT INTO BANKCLIENT VALUES(?, ?, ?, ?)";

            //(2)DB 준비
            pstmt = con.prepareStatement(sql);

            //(3)데이터 입력
            pstmt.setInt(1, client.getcNum());
            pstmt.setString(2, client.getcName());
            pstmt.setString(3, client.getcAccount());
            pstmt.setInt(4, client.getBalance());

            //(4)실행
            int result = pstmt.executeUpdate();

            //(5)결과
            if(result > 0) {
                System.out.println("계좌생성 성공!");
                System.out.println("고객번호 : " + client.getcNum());
                System.out.println("계좌번호 : " + client.getcAccount());
                System.out.println();
            } else {
                System.out.println("계좌생성 실패!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //계좌 유무 확인 메소드
    public boolean checkAccount(String cAccount) {
        boolean checked = false;
        try {
            //1
            String sql = "SELECT * FROM BANKCLIENT WHERE CACCOUNT=?";

            //2
            pstmt = con.prepareStatement(sql);

            //3
            pstmt.setString(1, cAccount);

            //4
            rs = pstmt.executeQuery();

            //5
            checked = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return checked;
    }

    //계좌 입금 메소드
    public void deposit(String cAccount, int amount) {
        try {
            //1
            String sql = "UPDATE BANKCLIENT SET BALANCE = BALANCE + ? WHERE CACCOUNT = ?";

            //2
            pstmt = con.prepareStatement(sql);

            //3
            pstmt.setInt(1, amount);
            pstmt.setString(2, cAccount);

            //4
            int result = pstmt.executeUpdate();

            //5
//            if(result > 0) {
//                System.out.println("입금 성공");
//            } else {
//                System.out.println("입금 실패");
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //계좌 잔액 확인 메소드
    public int checkBalance(String cAccount) {
        int balance = 0;

        try {
            //1.sql문 작성
            String sql = "SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?";

            //2 작성한 sql문을 db에서 사용할 준비
            // (1) try-catch문 따로 작성하는 것이 아니라
            // pstmt = con.prepareStatement(sql); 을 작성하고 빨간줄이 뜨면 마우스 커서를 올려놓고
            // More actions([Alt]키 + [Enter]키)를 선택
            // Surround with try/catch를 선택
            pstmt = con.prepareStatement(sql);

            // 3단계 부터 try문 안에서 작성
            // '?'에 데이터를 입력하는 단계
            // pstmt.setString(1, cAccount);
            // "SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?" 쿼리문에서
            // '?' 에 들어갈 값이 NVARCHAR2(30), java에서는 String 타입이기 때문에
            // setString을 사용했고, '1'은 첫번째 나오는 물음표(?)에 값을 대입한다는 의미
            // cAccount는 public int checkBalance(String cAccount)메소드에서
            // 매개변수로 받아온 계좌번호(cAccount)를 의미한다.
            pstmt.setString(1, cAccount);

            // 4. 실행하기
            // INSERT, UPDATE, DELETE => pstmt.executeUpdate(); 를 사용
            // int result에 결과 값을 담는다
            // ex) int result = pstmt.executeUpdate();

            // SELECT => pstmt.executeQuery(); 를 사용
            // ResultSet rs에 결과를 담는다.
            // ex) rs = pstmt.executeQuery();
            rs = pstmt.executeQuery();

            // 5. 결과처리
            // (1) 결과가 여러개(여러줄) 일때
            // while(rs.next()) => 반복문 실행해서 여러개 결과를 출력

            // (2) 결과가 한개(한줄) 일때 => WHERE절에 작성한 조건이 PK(기본키)이거나 UNIQUE(중복X)일 때
            // if(rs.next()) => 조건문 사용

            // (2-1) boolean result = rs.next();
//            boolean result = false;
//            if(rs.next()){
//                result = true;
//            } else {
//                result = false;
//            }
            // boolean result = rs.next();


            if(rs.next()){
                balance = rs.getInt(1);
                // rs.getInt(1) : '1'은 조회한 결과의 첫번째 컬럼
                // 컬럼의 타입이 NUMBER이기 때문에 java에서 int타입을 사용해서 getInt()를 사용
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return balance;
    }

    //계좌 출금 메소드
    public void withdraw(String cAccount, int amount) {
        try {
            //1
            String sql = "UPDATE BANKCLIENT SET BALANCE = BALANCE - ? WHERE CACCOUNT = ?";

            //2
            pstmt = con.prepareStatement(sql);

            //3
            pstmt.setInt(1, amount);
            pstmt.setString(2, cAccount);

            //4
            int result = pstmt.executeUpdate();

            //5
//            if(result > 0) {
//                System.out.println("출금 성공");
//            } else {
//                System.out.println("출금 실패");
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}