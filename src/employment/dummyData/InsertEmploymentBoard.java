package src.employment.dummyData;

import src.employment.recordDAO.employmentBoard.create.CreateDAO;

public class InsertEmploymentBoard {

    public static void main(String[] args) {
        // 테스트용 더미 데이터를 넣는 코드.
        CreateDAO createDao = new CreateDAO();
        // 제목, 근무형태, 요구학력, 채용절차, 자격요건, 우대사항, 메인1, 서브1, 메인2, 서브2, 회사명
        createDao.create("제목입니다.", "정규직", "무관", "협의", "무관", "없음", 1, 1, 2, 21, "KOSA");
        createDao.create("구합니다...", "정규직", "무관", "협의", "무관", "없음", 1, 2, 2, 24, "KOSA");
        createDao.create("채용공고 입니다.", "인턴", "대졸", "서류 후 면접", "무관", "없음", 1, 2, 2, 23, "KOSA");
        createDao.create("서울 근무자 구직", "인턴직", "무관", "서류 후 면접", "데이터 석사", "없음", 1, 1, 2, 22, "KOSA");
        createDao.create("대전 근무자 구직", "수습개발자", "초대졸", "서류 후 면접", "무관", "없음", 1, 5, 2, 24, "KOSA");
        createDao.create("부산 백엔드 개발자 구합니다.", "정규직", "무관", "코딩테스트 이후 면접", "무관", "없음", 1, 2, 2, 21, "KOSA");
        createDao.create("강원도 풀스택 개발자 구인", "연구원", "대졸", "코딩테스트 후 면접", "관련 학위 학사", "자격증 우대", 1, 10, 2, 25, "KOSA");
        createDao.create("서울 지역 백엔드 개발자 채용", "개발자", "경력 5년 이상", "서류 전형 후 면접", "컴퓨터공학 학사", "Java, Spring 우대", 1, 1, 2, 21, "Naver");
        createDao.create("서울 프론트엔드 개발자 채용", "개발자", "신입", "온라인 코딩테스트 후 면접", "컴퓨터공학 전공", "Vue.js, React 우대", 1, 1, 2, 22, "Kakao");
        createDao.create("서울 데이터 사이언티스트 모집", "연구원", "석사 이상", "1차 면접 후 최종 면접", "데이터 분석 관련 전공", "Python, R 우대", 1, 1, 2, 20, "Samsung");
        createDao.create("서울 인공지능 연구원 채용", "연구원", "박사", "서류 전형 후 2차 면접", "AI 관련 전공 박사", "논문 다수 게재자 우대", 1, 1, 2, 24, "LG");
        createDao.create("서울 모바일 앱 개발자 채용", "개발자", "3년 이상", "포트폴리오 제출 후 면접", "모바일 개발 경험자", "Swift, Kotlin 우대", 1, 1, 2, 24, "Hyundai");
        createDao.create("서울 시스템 엔지니어 구인", "엔지니어", "경력 2년 이상", "기술 면접 후 임원 면접", "시스템 아키텍처 설계 경험", "네트워크 자격증 우대", 1, 1, 2, 26, "SK");
        createDao.create("서울 DevOps 엔지니어 모집", "엔지니어", "경력 4년 이상", "실무 테스트 후 면접", "CI/CD 경험자", "Docker, Kubernetes 우대", 1, 1, 2, 27, "LG");
        createDao.create("서울 클라우드 엔지니어 채용", "엔지니어", "경력 6년 이상", "화상 면접 후 대면 면접", "클라우드 플랫폼 경험자", "AWS, Azure 자격증 우대", 1, 1, 2, 28, "KT");
        createDao.create("서울 데이터베이스 관리자 구인", "DBA", "경력 5년 이상", "기술 테스트 후 면접", "DBMS 경험자", "Oracle, SQL 우대", 1, 1, 2, 20, "NCSOFT");
        createDao.create("서울 네트워크 엔지니어 채용", "엔지니어", "경력 3년 이상", "서류 전형 후 기술 면접", "네트워크 설계 경험자", "CCNA, CCNP 우대", 1, 1, 2, 29, "KISA");
        createDao.create("부산 지역 백엔드 개발자 채용", "개발자", "경력 5년 이상", "서류 전형 후 면접", "컴퓨터공학 학사", "Java, Spring 우대", 1, 2, 2, 21, "Naver");
        createDao.create("부산 프론트엔드 개발자 채용", "개발자", "신입", "온라인 코딩테스트 후 면접", "컴퓨터공학 전공", "Vue.js, React 우대", 1, 2, 2, 22, "Kakao");
        createDao.create("부산 데이터 사이언티스트 모집", "연구원", "석사 이상", "1차 면접 후 최종 면접", "데이터 분석 관련 전공", "Python, R 우대", 1, 2, 2, 20, "Samsung");
        createDao.create("부산 인공지능 연구원 채용", "연구원", "박사", "서류 전형 후 2차 면접", "AI 관련 전공 박사", "논문 다수 게재자 우대", 1, 2, 2, 18, "LG");
        createDao.create("부산 모바일 앱 개발자 채용", "개발자", "3년 이상", "포트폴리오 제출 후 면접", "모바일 개발 경험자", "Swift, Kotlin 우대", 1, 2, 2, 24, "Hyundai");
        createDao.create("부산 시스템 엔지니어 구인", "엔지니어", "경력 2년 이상", "기술 면접 후 임원 면접", "시스템 아키텍처 설계 경험", "네트워크 자격증 우대", 1, 2, 2, 26, "SK");
        createDao.create("부산 DevOps 엔지니어 모집", "엔지니어", "경력 4년 이상", "실무 테스트 후 면접", "CI/CD 경험자", "Docker, Kubernetes 우대", 1, 2, 2, 27, "LG");
        createDao.create("부산 클라우드 엔지니어 채용", "엔지니어", "경력 6년 이상", "화상 면접 후 대면 면접", "클라우드 플랫폼 경험자", "AWS, Azure 자격증 우대", 1, 2, 2, 28, "KT");
        createDao.create("부산 데이터베이스 관리자 구인", "DBA", "경력 5년 이상", "기술 테스트 후 면접", "DBMS 경험자", "Oracle, SQL 우대", 1, 2, 2, 20, "NCSOFT");
        createDao.create("부산 네트워크 엔지니어 채용", "엔지니어", "경력 3년 이상", "서류 전형 후 기술 면접", "네트워크 설계 경험자", "CCNA, CCNP 우대", 1, 2, 2, 29, "KISA");
    }
}
