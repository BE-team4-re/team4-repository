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

    public void askEmploymentMenu() {
        printEmploymentMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.print("기업명을 입력해주세요:");
                String companyName = scanner.nextLine();
                List<BoardDTO> empList = employmentService.searchEmpBoard(companyName);
                System.out.println("-------------------------------------------------------");
                System.out.printf("%-6s%-30s%-16s%-80s \n", "no", "제목", "기업명", "채용과정");
                System.out.println("-------------------------------------------------------");

                empList.forEach(emp -> {
                    System.out.printf("%-6s%-30s%-16s%-80s \n",
                            emp.getEmploymentBoardId(),
                            emp.getTitle(),
                            emp.getCompanyName(),
                            emp.getHiringProcess()
                    );
                });
                System.out.println("------------------------------------------------------");
                System.out.println("1. 수정하기 2.삭제하기 3.돌아가기");
                String choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        System.out.print(" 채용공고의 번호를 입력해주세요: ");
                        String strID = scanner.nextLine();
                        int board_id = Integer.parseInt(strID);
                        if (employmentService.containsEmploymentBoardId(empList,board_id)){
                            BoardDTO boardDTO = employmentService.viewEmpBoard(board_id);
                            System.out.println(
                                    "번호: " + boardDTO.getEmploymentBoardId() + "\n" +
                                    "제목: " + boardDTO.getTitle() + "\n" +
                                    "근무형태: " + boardDTO.getJobType() + "\n" +
                                    "요구학력: " + boardDTO.getCareer() + "\n" +
                                    "전형절차: " + boardDTO.getHiringProcess() + "\n" +
                                    "자격요건: " + boardDTO.getQualifications() +"\n" +
                                    "우대사항: " + boardDTO.getPreferred() + "\n" +
                                    "지역: "  + boardDTO.getLocalName() + "\n" +
                                    "직무: " + boardDTO.getJobName() + "\n"
                            );
                            System.out.println("---------------------------------------------------------");
                            System.out.println("1.제목 2.근무형태 3.요구학력 4.전형절차 5.자격요건 6.우대사항 7.지역 8.직무");
                            System.out.println("수정하실 항목을 선택해주세요.");
                            String columnName = "";
                            String updateContent = "";
                            String category = scanner.nextLine();
                                    switch (category){
                                        case "1":
                                            System.out.print("제목을 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "title";
                                            break;
                                        case "2":
                                            System.out.print("근무 형태를 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "job_type";

                                            break;
                                        case "3":
                                            System.out.print("요구 학력를 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "career";

                                            break;
                                        case "4":
                                            System.out.print("전형 절차를 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "hiring_process";

                                            break;
                                        case "5":
                                            System.out.print("자격 요건를 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "qualifications";

                                            break;
                                        case "6":
                                            System.out.print("우대 사항을 입력해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "preferred";

                                            break;
                                        case "7":
                                            List<BoardCategoryDTO> dto = categoryService.getCategoryList();
                                            printCategoryMenu(dto,1);
                                            System.out.print("지역을 선택해주세요:");
                                            updateContent = scanner.nextLine();
                                            columnName = "sub_category1_id";
                                            break;
                                        case "8":
                                            List<BoardCategoryDTO> dto2 = categoryService.getCategoryList();
                                            printCategoryMenu(dto2,19);
                                            System.out.print("직무를 선택해주세요:");
                                            updateContent = scanner.nextLine();;
                                            columnName = "sub_category2_id";

                                            break;
                                        default:
                                            System.out.println("잘못된 입력입니다.");
                                            break;
                                    }

                            if (!columnName.equals("") && !updateContent.equals("")) {
                                employmentService.updateEmpBoard(columnName, updateContent, boardDTO.getEmploymentBoardId());
                            }
                        }else{
                            System.out.println("검색하신 기업의 채용공고가 아닙니다. ");
                        }
                        break;
                    case "2":
                        System.out.println("삭제하실 채용 공고의 번호를 입력해주세요:");
                        String strEmpNo = scanner.nextLine();
                        int empNo = Integer.parseInt(strEmpNo);
                        if (employmentService.containsEmploymentBoardId(empList,empNo)){
                            employmentService.deleteEmpBoard(empNo);
                        }
                        else{
                            System.out.println("검색하신 기업의 채용공고 번호가 아닙니다.");
                        }
                        return;
                    case "3":
                        return;
                }
                break;

            case "2":
                System.out.print("기업명을 입력해주세요:");
                String company_Name = scanner.nextLine();

                System.out.print("채용 제목을 입력해주세요:");
                String title = scanner.nextLine();

                System.out.print("근무형태를 입력해주세요:");
                String jobType = scanner.nextLine();

                System.out.print("요구학력을 입력해주세요:");
                String career = scanner.nextLine();

                System.out.print("전형절차를 입력해주세요:");
                String hiringProcess = scanner.nextLine();

                System.out.print("자격요건을 입력해주세요:");
                String qualifications = scanner.nextLine();

                System.out.print("우대사항 입력해주세요:");
                String preferred = scanner.nextLine();

                List<BoardCategoryDTO> dto = categoryService.getCategoryList();

                printCategoryMenu(dto,1);
                System.out.print("해당하는 지역의 번호를 입력해주세요:");
                String strLocation = scanner.nextLine();

                printCategoryMenu(dto,19);
                System.out.print("해당하는 직무의 번호를 입력해주세요:");
                String strEmpNo = scanner.nextLine();

                int location = Integer.parseInt(strLocation);
                int empNo = Integer.parseInt(strEmpNo);


                System.out.println("작성하신 내용은 다음과 같습니다.");
                System.out.println("1.회사명: " + company_Name);
                System.out.println("2.채용명: " + title);
                System.out.println("3.근무형태: " + jobType);
                System.out.println("4.요구학력: " + career);
                System.out.println("5.전형절차: " + hiringProcess);
                System.out.println("6.자격요건: " + qualifications);
                System.out.println("7.우대사항: " + preferred);
                System.out.println("8.회사명: " + dto.get(location-1).getCategoryName());
                System.out.println("9.회사명: " + dto.get(empNo-1).getCategoryName());
                System.out.println("=======================================================");
                System.out.println("추가하시겠습니까?");
                System.out.println("1.예  2.아니오(입력하신 정보 모두 삭제)");

                String choice2 = scanner.nextLine();
                switch (choice2) {
                    case "1":
                        employmentService.insertEmpBoard(company_Name,title,jobType,career,hiringProcess,qualifications,preferred,location,empNo);
                        break;
                    case "2":
                        System.out.println("추가하기가 취소되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
                break;

            case "3":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public void printEmploymentMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("--------------------관리자 채용관리 페이지---------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1.채용 검색 2.채용 추가 3.뒤로 가기");
    }

    public void printCategoryMenu(List<BoardCategoryDTO> dto, int categoryNum){
        dto.forEach(dto1 -> {
            if (dto1.getMainCategoryId() == categoryNum) {
                System.out.println(dto1.getCategoryId() + ". " + dto1.getCategoryName());
            }
        });
    }

}
