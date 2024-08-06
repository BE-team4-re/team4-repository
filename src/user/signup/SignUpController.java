package src.user.signup;

import java.util.Scanner;
import src.user.UserDTO;
import src.user.Validation;

public class SignUpController {

    Scanner sc = new Scanner(System.in);
    Validation check = new Validation();
    SignUpDAO sd = new SignUpDAO();

    // 회원가입
    public void signUpMain() {
        String id, pw, name, gender, phone, birth, email;
        System.out.println("\n---------------------------------------------1");
        System.out.println("------------------ 회원 가입 ------------------");
        System.out.println("---------------------------------------------");
        // 아이디 입력
        while (true) {
            System.out.print("아이디를 입력하세요 :");
            id = sc.nextLine();
            // 아이디 중복 체크
            if (sd.duplicateId(id)) {
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
            System.out.print("성별을 입력하세요(ex 남/여) :");
            gender = sc.nextLine();
            // 성별 검증
            if (check.validationGender(gender)) {
                break;
            }
        }
        // 휴대폰번호 입력
        while (true) {
            System.out.print("휴대폰번호를 입력하세요(ex 01012345678) :");
            phone = sc.nextLine();
            // 휴대폰번호 검증
            if (check.validationPhone(phone)) {
                break;
            }
        }
        // 생년월일 입력
        while (true) {
            System.out.print("생년월일을 입력하세요(ex 19920101) :");
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
        int result = sd.signUp(user);
        if (result > 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }

}
