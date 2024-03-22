package NAVER;

import java.util.Scanner;

public class NaverMain {
    public static void main(String[] args) {
        //NaverMember 객체
        NaverMember member = new NaverMember();

        //NaverSQL 객체
        NaverSQL sql = new NaverSQL();

        //프로그램 실행에 필요한 객체, 변수
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;
        boolean run1 = true;
        int menu1 = 0;

        //프로그램을 처음 실행할 경우 DB접속
        sql.connect();

        while(run) {
            System.out.println("==========================");
            System.out.println("[1]회원가입\t\t[2]회원목록");
            System.out.println("[3]로그인\t\t[4]종료");
            System.out.println("==========================");
            System.out.print("선택 -> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1 :
                    System.out.println("회원정보를 입력해주세요.");
                    System.out.print("아이디 -> ");
                    member.setnId(sc.next());

                    System.out.print("비밀번호 -> ");
                    member.setnPw(sc.next());

                    System.out.print("이메일 -> ");
                    member.setnEmail(sc.next());

                    System.out.print("이름 -> ");
                    member.setnName(sc.next());

                    System.out.print("생년월일 -> ");
                    member.setnBirth(sc.next());

                    System.out.print("성별 -> ");
                    member.setnGender(sc.next());

                    System.out.print("연락처 -> ");
                    member.setnPhone(sc.next());

                    //NaverSQL클래스에 memberJoin()메소드를 만들고
                    //정보를 담은 member 객체
                    sql.memberJoin(member);
                    break;
                case 2 :
                    sql.memberList();
                    break;
                case 3 :
                    System.out.print("아이디 -> ");
                    String nId = sc.next();

                    System.out.print("비밀번호 -> ");
                    String nPw = sc.next();

                    boolean login = sql.memberLogin(nId, nPw);

                    if(login) {
                        System.out.println("로그인 성공!");
                        run1 = true;        //작성하지 않을 경우 로그인 성공하더라도 메뉴가 나오지 않는다.
                        while(run1) {
                            System.out.println("==========================");
                            System.out.println("[1]내정보\t\t[2]회원수정");
                            System.out.println("[3]회원삭제\t\t[4]로그아웃");
                            System.out.println("==========================");
                            System.out.print("선택 -> ");
                            menu1 = sc.nextInt();

                            switch (menu1) {
                                case 1:
                                    //nId를 사용해서 내 정보보기
                                    sql.myInfo(nId);
                                    break;
                                case 2:
                                    member.setnId(nId);
                                    System.out.println("수정할 정보를 입력해주세요.");
                                    System.out.print("비밀번호 -> ");
                                    member.setnPw(sc.next());

                                    System.out.print("이메일 -> ");
                                    member.setnEmail(sc.next());

                                    System.out.print("이름 -> ");
                                    member.setnName(sc.next());

                                    System.out.print("생년월일 -> ");
                                    member.setnBirth(sc.next());

                                    System.out.print("성별 -> ");
                                    member.setnGender(sc.next());

                                    System.out.print("연락처 -> ");
                                    member.setnPhone(sc.next());

                                    //NaverSQL클래스에 memberJoin()메소드를 만들고
                                    //정보를 담은 member 객체
                                    sql.memberModify(member);
                                    break;
                                case 3:
                                    System.out.println("정말 삭제하시겠습니까? (Y/N)");
                                    String checkDelete = sc.next();

                                    switch (checkDelete) {
                                        case "Y" :
                                            sql.memberDelete(nId);
                                            run1 = false;
                                            System.out.println("다시 로그인 해주십시오.");
                                            break;
                                        case "N" :
                                            System.out.println("삭제를 취소합니다.");
                                            break;
                                        default :
                                            System.out.println("잘못 입력하셨습니다.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("로그아웃 합니다.");
                                    run1 = false;
                                    break;
                                default:
                                    System.out.println("다시 입력해 주세요.");
                            }
                        }
                    } else {
                        System.out.println("로그인 실패!");
                    }
                    break;
                case 4 :
                    sql.conClose();     //DB접속 해제
                    run = false;        //반복문 종료
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default :
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}