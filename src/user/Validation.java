package src.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Validation {

    // 아이디 예외처리(소문자,대문자,숫자만 허용하며 3~15자 이내)
    public boolean validationId(String id) {
        if (!Pattern.matches("^[a-zA-Z0-9]{1,15}$", id)) {
            System.out.println("아이디는 특수문자를 사용 할 수 없으며 3~15자 이내여햐 합니다.");
            return false;
        }
        return true;
    }

    // 비밀번호 예외처리(소문자,대문자,숫자,특수문자 허용하며 20자 이내)
    public boolean validationPw(String pw) {
        if (!Pattern.matches("^[A-Za-z0-9@#$%^&*!()_+\\-={};':|,.<>/?\"]{1,20}$", pw)) {
            System.out.println("비밀번호는 20자 이내여야합니다.");
            return false;
        }
        return true;
    }

    // 이름 예외처리(숫자, 특수문자를 제외하고 6자 이내)
    public boolean validationName(String name) {
        if (!Pattern.matches("^[a-zA-Z가-힣]{1,6}$", name)) {
            System.out.println("이름은 특수문자를 사용 할 수 없으며 6자이내여야 합니다.");
            return false;
        }
        return true;
    }

    // 성별 예외처리(남, 여만 허용)
    public boolean validationGender(String gender) {
        if (!Pattern.matches("^(남|여)$", gender)) {
            System.out.println("성별은 남/여 만 입력가능합니다.");
            return false;
        }
        return true;
    }

    // 휴대폰 번호 예외처리(숫자만 입력가능하며 11자만 허용)
    public boolean validationPhone(String phone) {
        if (!Pattern.matches("^\\d{11}$", phone)) {
            System.out.println("휴대폰 번호는 11자의 숫자만 입력가능합니다.");
            return false;
        }
        return true;
    }

    // 생년월일 예외 처리(숫자만 입력가능하며 자만 허용)
    public boolean validationBirth(String birth) {
        if (!Pattern.matches("^\\d{8}$", birth)) {
            System.out.println("생년월일은 8자인 숫자만 입력가능합니다.");
            return false;
        }
        return true;
    }

    // 이메일 예외 처리(@필수구분자가 있어야하며 50자 이내)
    public boolean validationEmail(String email) {
        if (!Pattern.matches("^[0-9a-zA-Z._+-=!@#$%^&*(){}:;'<>?/]+@[a-zA-Z.]+$", email)) {
            System.out.println("이메일 형식이 잘못되었습니다.");
            return false;
        }
        return true;
    }

    // 비밀번호 암호화
    public String encodingPwd(String pw) {
        String result = "";
        try {
            // SHA-256 알고리즘을 사용하여 MessageDigest 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 입력된 비밀번호 문자열을 바이트 배열로 변환하여 MessageDigest에 전달
            md.update(pw.getBytes());

            // 해시된 결과를 바이트 배열로 받아서 헥스 문자열로 변환하고 19자리까지만 나오게 설정
            result = bytesToHex(md.digest()).substring(0, 19);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 헥스 문자열로 변환
    public String bytesToHex(byte[] bytes) {
        // 바이트 배열을 헥스 문자열로 변환
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            // 바이트를 2자리 16진수 문자열로 변환
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
