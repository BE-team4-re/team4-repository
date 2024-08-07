package src.admin.employment;

import src.admin.category.CategoryService;
import src.employment.board.BoardCategoryDTO;
import src.employment.board.BoardDTO;

import java.util.List;
import java.util.Scanner;

public class EmploymentController {
    EmploymentService employmentService = new EmploymentService();
    CategoryService categoryService = new CategoryService();

    Scanner scanner = new Scanner(System.in);

    //채용공고 메뉴 출력
    public void askEmploymentMenu() {
        while (true) {
            printEmploymentMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleSearchEmployment();
                    break;
                case "2":
                    handleAddEmployment();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

    //채용 공고 검색
    private void handleSearchEmployment() {
        System.out.print("기업명을 입력해주세요:");
        String companyName = scanner.nextLine();
        List<BoardDTO> empList = employmentService.searchEmpBoard(companyName);
        printEmploymentList(empList);

        if (empList.size() == 0) {
            System.out.println("채용공고가 없습니다.");
            return;
        }

        System.out.println("1. 수정하기 2.삭제하기 3.돌아가기");
        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                handleUpdateEmployment(empList);
                break;
            case "2":
                handleDeleteEmployment(empList);
                break;
            case "3":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    //채용 정보 수정
    private void handleUpdateEmployment(List<BoardDTO> empList) {
        System.out.print("채용공고의 번호를 입력해주세요: ");
        String strID = scanner.nextLine();
        int board_id = Integer.parseInt(strID);
        if (employmentService.containsEmploymentBoardId(empList, board_id)) {
            BoardDTO boardDTO = employmentService.viewEmpBoard(board_id);
            printBoardDetails(boardDTO);
            updateEmployment(boardDTO);
        } else {
            System.out.println("검색하신 기업의 채용공고가 아닙니다.");
        }
    }

    //수정부분 사용자 입력
    private void updateEmployment(BoardDTO boardDTO) {
        String columnName = "";
        String updateContent = "";
        while (true) {
            System.out.println("1.제목 2.근무형태 3.요구학력 4.전형절차 5.자격요건 6.우대사항 7.지역 8.직무");
            System.out.println("수정하실 항목을 선택해주세요.");
            String category = scanner.nextLine();
            switch (category) {
                case "1":
                    columnName = "title";
                    updateContent = askForInput("제목을 입력해주세요:");
                    break;
                case "2":
                    columnName = "job_type";
                    updateContent = askForInput("근무 형태를 입력해주세요:");
                    break;
                case "3":
                    columnName = "career";
                    updateContent = askForInput("요구 학력을 입력해주세요:");
                    break;
                case "4":
                    columnName = "hiring_process";
                    updateContent = askForInput("전형 절차를 입력해주세요:");
                    break;
                case "5":
                    columnName = "qualifications";
                    updateContent = askForInput("자격 요건을 입력해주세요:");
                    break;
                case "6":
                    columnName = "preferred";
                    updateContent = askForInput("우대 사항을 입력해주세요:");
                    break;
                case "7":
                    columnName = "sub_category1_id";
                    updateContent = askForCategorySelection(1);
                    break;
                case "8":
                    columnName = "sub_category2_id";
                    updateContent = askForCategorySelection(2);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }

            if (!columnName.equals("") && !updateContent.equals("")) {
                employmentService.updateEmpBoard(columnName, updateContent, boardDTO.getEmploymentBoardId());
                return;
            }
        }
    }

    //채용공고 삭제
    private void handleDeleteEmployment(List<BoardDTO> empList) {
        System.out.println("삭제하실 채용 공고의 번호를 입력해주세요:");
        String strEmpNo = scanner.nextLine();
        try {
            int empNo = Integer.parseInt(strEmpNo);
            if (employmentService.containsEmploymentBoardId(empList, empNo)) {
                employmentService.deleteEmpBoard(empNo);
            } else {
                System.out.println("검색하신 기업의 채용공고 번호가 아닙니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 번호를 입력해주세요.");
        }
    }


    //채용공고 추가
    private void handleAddEmployment() {
        System.out.print("기업명을 입력해주세요:");
        String companyName = scanner.nextLine();

        String title = askForInput("채용 제목을 입력해주세요:");
        String jobType = askForInput("근무형태를 입력해주세요:");
        String career = askForInput("요구학력을 입력해주세요:");
        String hiringProcess = askForInput("전형절차를 입력해주세요:");
        String qualifications = askForInput("자격요건을 입력해주세요:");
        String preferred = askForInput("우대사항을 입력해주세요:");

        List<BoardCategoryDTO> dto = categoryService.getCategoryList();
        int location = askForCategorySelectionWithValidation(1);
        int empNo = askForCategorySelectionWithValidation(2);

        System.out.println("작성하신 내용은 다음과 같습니다.");
        printEmploymentSummary(companyName, title, jobType, career, hiringProcess, qualifications, preferred, dto, location, empNo);

        while (true) {
            System.out.println("추가하시겠습니까?");
            System.out.println("1.예  2.아니오(입력하신 정보 모두 삭제)");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    employmentService.insertEmpBoard(companyName, title, jobType, career, hiringProcess, qualifications, preferred, location, empNo);
                    return;
                case "2":
                    System.out.println("추가하기가 취소되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }


    //채용 카테고리 숫자 검증
    private int askForCategorySelectionWithValidation(int categoryNum) {
        while (true) {
            try {
                return Integer.parseInt(askForCategorySelection(categoryNum));
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 번호를 입력해주세요.");
            }
        }
    }

    //사용자 입력요청
    private String askForInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    //카테고리 선택 처리
    private String askForCategorySelection(int categoryNum) {
        List<BoardCategoryDTO> dto = categoryService.getCategoryList();
        printCategoryMenu(dto, categoryNum);
        System.out.print("해당하는 번호를 입력해주세요:");
        return scanner.nextLine();
    }

    //채용공고 목록 출력
    private void printEmploymentList(List<BoardDTO> empList) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-6s%-30s%-16s%-80s \n", "no", "제목", "기업명", "채용과정");
        System.out.println("--------------------------------------------------------------------------------");
        empList.stream()
                .forEach(emp -> {
                    System.out.printf("%-6s%-30s%-16s%-80s \n",
                            emp.getEmploymentBoardId(),
                            emp.getTitle(),
                            emp.getCompanyName(),
                            emp.getHiringProcess()
                    );
                });
        System.out.println("-------------------------------------------------------------------------------");
    }

    //채용 상세 정보 출력
    private void printBoardDetails(BoardDTO boardDTO) {
        System.out.println(
                "번호: " + boardDTO.getEmploymentBoardId() + "\n" +
                        "제목: " + boardDTO.getTitle() + "\n" +
                        "근무형태: " + boardDTO.getJobType() + "\n" +
                        "요구학력: " + boardDTO.getCareer() + "\n" +
                        "전형절차: " + boardDTO.getHiringProcess() + "\n" +
                        "자격요건: " + boardDTO.getQualifications() + "\n" +
                        "우대사항: " + boardDTO.getPreferred() + "\n" +
                        "지역: " + boardDTO.getLocalName() + "\n" +
                        "직무: " + boardDTO.getJobName() + "\n"
        );
        System.out.println("----------------------------------------------------------------------------------");
    }

    //추가시 작성한 채용 정보 출력
    private void printEmploymentSummary(String companyName, String title, String jobType, String career, String hiringProcess, String qualifications, String preferred, List<BoardCategoryDTO> dto, int location, int empNo) {
        System.out.println("1.회사명: " + companyName);
        System.out.println("2.채용명: " + title);
        System.out.println("3.근무형태: " + jobType);
        System.out.println("4.요구학력: " + career);
        System.out.println("5.전형절차: " + hiringProcess);
        System.out.println("6.자격요건: " + qualifications);
        System.out.println("7.우대사항: " + preferred);
        System.out.println("8.지역: " + dto.get(location - 1).getCategoryName());
        System.out.println("9.직무: " + dto.get(empNo - 1).getCategoryName());
        System.out.println("=======================================================");
    }

    //채용메뉴 출력
    public void printEmploymentMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("--------------------관리자 채용관리 페이지---------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1.채용 검색 2.채용 추가 3.뒤로 가기");
    }

    //카테고리메뉴 출력
    public void printCategoryMenu(List<BoardCategoryDTO> dto, int categoryNum) {
        dto.forEach(dto1 -> {
            if (dto1.getMainCategoryId() == categoryNum) {
                System.out.println(dto1.getCategoryId() + ". " + dto1.getCategoryName());
            }
        });
    }
}
