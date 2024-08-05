package src.user;

import src.employment.test.TestMain;

import java.util.Scanner;

public class Controller {

    Scanner sc = new Scanner(System.in);
    UserDAO ud = new UserDAO();
    Validation check = new Validation();

    // 메인
    public void firstMain() {
        while (true) {
            System.out.println("---------------------------------------------");
            System.out.println("------------------ 메인 메뉴 ------------------");
            System.out.println("---------------------------------------------");
            System.out.println("1. 회원가입 | 2. 로그인");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    insert(); // 회원가입
                    break;
                case "2":
                    login(); // 로그인
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 회원가입
    public void insert() {
        String id, pw, name, gender, phone, birth, email;
        System.out.println("---------------------------------------------");
        System.out.println("------------------ 회원 가입 ------------------");
        System.out.println("---------------------------------------------");
        // 아이디 입력
        while (true) {
            System.out.print("아이디를 입력하세요 :");
            id = sc.nextLine();
            // 아이디 중복 체크
            if (ud.duplicateId(id)) {
                continue;
            }
            // 아이디 검증
            if (check.validationId(id)) {
                break;
            }
        }
        // 비밀번호 입력
        while (true) {
            System.out.print("비밀번호를 입력하세요 :");
            pw = sc.nextLine();
            // 비밀번로 검증
            if (check.validationPw(pw)) {
                pw = check.encodingPwd(pw); //암호화
                break;
            }
        }
        // 이름 입력
        while (true) {
            System.out.print("이름을 입력하세요 :");
            name = sc.nextLine();
            // 이름 검증
            if (check.validationName(name)) {
                break;
            }
        }
        // 성별 입력
        while (true) {
            System.out.print("성별을 입력하세요 :");
            gender = sc.nextLine();
            // 성별 검증
            if (check.validationGender(gender)) {
                break;
            }
        }
        // 휴대폰번호 입력
        while (true) {
            System.out.print("휴대폰번호를 입력하세요 :");
            phone = sc.nextLine();
            // 휴대폰번호 검증
            if (check.validationPhone(phone)) {
                break;
            }
        }
        // 생년월일 입력
        while (true) {
            System.out.print("생년월일을 입력하세요 :");
            birth = sc.nextLine();
            // 생년월일 검증
            if (check.validationBirth(birth)) {
                break;
            }
        }
        // 이메일 입력
        while (true) {
            System.out.print("이메일을 입력하세요 :");
            email = sc.nextLine();
            // 이메일 검증
            if (check.validationEmail(email)) {
                break;
            }
        }
        // 회원가입 정보 생성 및 추가
        UserDTO user = new UserDTO(id, pw, name, gender, phone, birth, email);
        int result = ud.createUser(user);
        if (result > 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }

    // 로그인
    public void login() {
        System.out.println("------------------------------------------");
        System.out.println("------------------ 로그인 ------------------");
        System.out.println("------------------------------------------");
        System.out.print("아이디를 입력하세요 :");
        String id = sc.nextLine();

        System.out.print("비밀번호를 입력하세요 :");
        String pw = check.encodingPwd(sc.nextLine());
        // db에서 로그인 아이디, 비밀번호 확인
        boolean result = ud.login(id, pw);
        if (id.equals("admin") && pw.equals("admin")) {
            System.out.println("관리자 페이지 이동"); // 관리자 페이지 이동
        } else {
            pw = check.encodingPwd(pw); // 입력한 pw 암호화
            if (result) {
                System.out.println("로그인 성공");
                UserMain.loginId = id; // 로그인 상태 유지
                userMain(); //로그인 성공 후 메인 메뉴 이동
            } else {
                System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. \n다시 입력하세요");
            }
        }
    }

    // 사용자 메인 화면
    public void userMain() {
        boolean bool = true;
        while (bool) {
            System.out.println("-------------------------------------------------------");
            System.out.println("------------------------- 메인 -------------------------");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. 채용 공고 게시판 | 2. 커뮤니티 | 3. 마이페이지 | 4. 로그아웃");
            System.out.print("메뉴를 선택하세요 :");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    // 채용공고 게시판 이동
                    TestMain.EmploymentBoardMainEntrance();
                    break;
                case "2":
                    // 커뮤니티 이동
                    break;
                case "3":
                    myPage(); // 마이페이지 이동
                    break;
                case "4":
                    bool = false;
                    UserMain.loginId = null; // 로그아웃
                    System.out.println("로그아웃 되셨습니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 마이페이지
    public void myPage() {
        boolean bool = true;
        while (bool) {
            System.out.println("-------------------------------------------------------");
            System.out.println("----------------------- 마이페이지 -----------------------");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. 회원 정보 수정 | 2. 회원 탈퇴 | 3. 스크랩 확인 | 4. 뒤로가기");
            System.out.print("메뉴를 선택하세요 :");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    updateUser(); // 회원 정보 수정
                    break;
                case "2":
                    boolean isdelete = deleteUser(); // 회원 탈퇴
                    if (isdelete) {
                        bool = false;
                        firstMain();
                    }
                    break;
                case "3":
                    // 스크랩 이동
                    break;
                case "4":
                    bool = false; // 뒤로 가기
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 회원 정보 수정
    public void updateUser() {
        System.out.println("-------------------------------------------------");
        System.out.println("------------------ 회원 정보 수정 ------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("1. 비밀번호 | 2. 휴대폰 번호 | 3. 이메일");
        System.out.print("수정 할 항목을 선택하세요 :");
        String menu = sc.nextLine();
        switch (menu) {
            case "1":
                pwUpdate(); // 비밀번호 수정
                break;
            case "2":
                phoneUpdate(); // 휴대폰번호 수정
                break;
            case "3":
                emailUpdate(); // 이메일 수정
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }

    // 회원 탈퇴
    public boolean deleteUser() {
        System.out.print("정말 탈퇴하시겠습니까?(y or n) ");
        String yn = sc.nextLine();

        if (yn.equals("y")) {
            int result = ud.deleteUser(); // 로그인 탈퇴
            UserMain.loginId = null; // 로그아웃
            System.out.println("탈퇴가 완료되었습니다.");
            return true;
        } else {
            System.out.println("탈퇴를 취소하였습니다.");
            return false;
        }
    }

    // 비밀번호 수정
    public void pwUpdate() {
        String pw;
        int result;
        while (true) {
            System.out.print("수정할 비밀번호를 입력하세요:");
            pw = sc.nextLine();
            if (check.validationPw(pw)) { // 비밀번호 검증
                result = ud.updatePw(check.encodingPwd(pw));
                //pw = check.encodingPwd(pw);
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 휴대폰 번호 수정
    public void phoneUpdate() {
        String phone;
        int result;
        while (true) {
            System.out.print("수정할 번호를 입력하세요:");
            phone = sc.nextLine();
            if (check.validationPhone(phone)) { // 휴대폰 번호 검증
                result = ud.updatePhone(phone);
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 이메일 수정
    public void emailUpdate() {
        String email;
        int result;
        while (true) {
            System.out.print("수정할 이메일을 입력하세요:");
            email = sc.nextLine();
            if (check.validationEmail(email)) { // 이메일 검증
                result = ud.updateEmail(email); // 이메일 수정
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }
}
