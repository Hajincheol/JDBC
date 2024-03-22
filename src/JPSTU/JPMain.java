package JPSTU;

import java.util.Scanner;

public class JPMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        //JPMember 객체
        JPMember member = new JPMember();

        //JPSQL 객체
        JPSQL sql  = new JPSQL();

        while(run) {
            System.out.println("=====================");
            System.out.println("[1]DB접속\t\t[2]접속해제\t\t[3]학생등록\t\t[4]학생목록");
            System.out.println("[5]학생수정\t\t[6]학생삭제\t\t[7]종료");
            System.out.println("=====================");
            System.out.print("메뉴 선택 -> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:         //DB접속
                    sql.connect();
                    break;
                case 2:         //접속해제
                    sql.conClose();
                    break;
                case 3:         //학생등록
                    System.out.println("학생정보를 입력하세요.");
                    System.out.print("학생번호 -> ");
                    member.setjNum(sc.nextInt());

                    System.out.print("비밀번호 -> ");
                    member.setjPw(sc.next());

                    System.out.print("이름 -> ");
                    member.setjName(sc.next());

                    System.out.print("나이 -> ");
                    member.setjAge(sc.nextInt());

                    System.out.print("성별 -> ");
                    member.setjGender(sc.next());

                    System.out.print("이메일 -> ");
                    member.setjEmail(sc.next());

                    System.out.print("연락처 -> ");
                    member.setjPhone(sc.next());

                    sql.memberJoin(member);
                    break;
                case 4:         //학생목록
                    sql.memberList();
                    break;
                case 5:         //학생수정
                    System.out.print("수정할 학생 번호 -> ");
                    int jNum = sc.nextInt();

                    System.out.print("비밀번호 -> ");
                    String jPw = sc.next();

                    //학생번호와 비밀번호가 일치하는지 확인
                    boolean checked = sql.idCheck(jNum, jPw);

                    if(checked) {
                        member.setjNum(jNum);
                        System.out.println("학생정보가 일치합니다.");
                        System.out.println("수정할 회원정보를 입력해주세요.");
                        System.out.print("비밀번호 -> ");
                        member.setjPw(sc.next());

                        System.out.print("이름 -> ");
                        member.setjName(sc.next());

                        System.out.print("나이 -> ");
                        member.setjAge(sc.nextInt());

                        System.out.print("성별 -> ");
                        member.setjGender(sc.next());

                        System.out.print("이메일 -> ");
                        member.setjEmail(sc.next());

                        System.out.print("연락처 -> ");
                        member.setjPhone(sc.next());

                        sql.memberModify(member);
                    } else {
                        System.out.println("학생정보가 일치하지 않습니다.");
                    }
                    break;
                case 6:         //학생삭제
                    System.out.print("수정할 학생 번호 -> ");
                    jNum = sc.nextInt();

                    System.out.print("비밀번호 -> ");
                    jPw = sc.next();

                    //학생번호와 비밀번호가 일치하는지 확인
                    checked = sql.idCheck(jNum, jPw);
                    if(checked) {
                        sql.memberDelete(jNum);
                    } else {
                        System.out.println("학생정보가 일치하지 않습니다.");
                    }
                    break;
                case 7:         //DB종료
                    run = false;
                    System.out.println("프로그램을 끝냅니다.");
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }
}