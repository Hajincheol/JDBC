package GOOGLE;

import java.util.Scanner;

public class GoogleMain {
    public static void main(String[] args) {
        //GoogleMember 객체, GoogleSQL 객체, Scanner 객체 생성
        GoogleMember member = new GoogleMember();
        GoogleSQL sql = new GoogleSQL();
        Scanner sc = new Scanner(System.in);

        //프로그램 실행에 필요한 객체, 변수
        boolean run = true;
        int menu = 0;

        boolean runLogin = true;
        int menuLogin = 0;

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
                    System.out.println("회원정보를 입력해 주세요.");
                    System.out.print("아이디 : ");
                    member.setgId(sc.next());

                    System.out.print("비밀번호 : ");
                    member.setgPw(sc.next());

                    System.out.print("이메일 : ");
                    member.setgEmail(sc.next());

                    System.out.print("이름 : ");
                    member.setgName(sc.next());

                    System.out.print("생년월일 : ");
                    member.setgBirth(sc.next());

                    System.out.print("성별 : ");
                    member.setgGender(sc.next());

                    System.out.print("연락처 : ");
                    member.setgPhone(sc.next());

                    sql.memberJoin(member);
                    break;
                case 2 :
                    sql.memberList();
                    break;
                case 3 :
                    System.out.print("아이디 -> ");
                    String gId = sc.next();

                    System.out.print("비밀번호 -> ");
                    String gPw = sc.next();

                    if(sql.memberLogin(gId, gPw)) {
                        runLogin = true;
                        while(runLogin) {
                            System.out.println("==========================");
                            System.out.println("[1]내정보\t\t[2]회원수정");
                            System.out.println("[3]회원삭제\t\t[4]로그아웃");
                            System.out.println("==========================");
                            System.out.print("선택 -> ");
                            menuLogin = sc.nextInt();

                            switch (menuLogin) {
                                case 1 :
                                    sql.myInfo(gId);
                                    break;
                                case 2 :
                                    member.setgId(gId);
                                    System.out.println("수정할 정보를 입력해주세요.");
                                    System.out.print("비밀번호 : ");
                                    member.setgPw(sc.next());

                                    System.out.print("이메일 : ");
                                    member.setgEmail(sc.next());

                                    System.out.print("이름 : ");
                                    member.setgName(sc.next());

                                    System.out.print("생년월일 : ");
                                    member.setgBirth(sc.next());

                                    System.out.print("성별 : ");
                                    member.setgGender(sc.next());

                                    System.out.print("연락처 : ");
                                    member.setgPhone(sc.next());

                                    sql.memberModify(member);
                                    break;
                                case 3 :
                                    System.out.println("진짜로 삭제하시겠습니까? (Y/N)");
                                    switch (sc.next()) {
                                        case "Y" :
                                            sql.memberDelete(gId);
                                            runLogin = false;
                                            System.out.println("다시 로그인해주십시오.");
                                            break;
                                        case "N" :
                                            System.out.println("삭제를 취소합니다.");
                                            break;
                                        default :
                                            System.out.println("잘못 입력하셨습니다.");
                                    }
                                    break;
                                case 4 :
                                    System.out.println("로그아웃 합니다.");
                                    runLogin = false;
                                    break;
                                default :
                                    System.out.println("잘못 입력하셨습니다.");
                            }
                        }
                    }
                    break;
                case 4 :
                    sql.conClose();
                    run = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default :
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}