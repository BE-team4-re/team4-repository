package src.employment.test;

import src.employment.recordDAO.employmentBoard.create.CreateDAO;

public class TestInsertEmploymentBoard {

    public static void main(String[] args) {
        // 테스트용 더미 데이터를 넣는 코드.
        CreateDAO createDao = new CreateDAO();
        // 제목, 근무형태, 요구학력, 채용절차, 자격요건, 우대사항, 메인1, 서브1, 메인2, 서브2, 회사명
        createDao.create("제목입니다.", "정규직", "무관", "협의", "무관", "없음", 1, 2, 19, 21, "KOSA");
        createDao.create("구합니다...", "정규직", "무관", "협의", "무관", "없음", 1, 3, 19, 24, "KOSA");
        createDao.create("채용공고 입니다.", "인턴", "대졸", "서류 후 면접", "무관", "없음", 1, 3, 19, 23, "KOSA");
        createDao.create("서울 근무자 구직", "인턴직", "무관", "서류 후 면접", "데이터 석사", "없음", 1, 2, 19, 22, "KOSA");
        createDao.create("대전 근무자 구직", "수습개발자", "초대졸", "서류 후 면접", "무관", "없음", 1, 6, 19, 24, "KOSA");
        createDao.create("부산 백엔드 개발자 구합니다.", "정규직", "무관", "코딩테스트 이후 면접", "무관", "없음", 1, 3, 19, 23, "KOSA");
        createDao.create("강원도 풀스택 개발자 구인", "연구원", "대졸", "코딩테스트 후 면접", "관련 학위 학사", "자격증 우대", 1, 11, 19, 27, "KOSA");
    }
}
