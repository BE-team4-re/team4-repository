package src.admin.employment;

import src.employment.recordVO.EmploymentBoardVO;

import java.util.List;
import java.util.Scanner;

public class EmploymentController {
    EmploymentService employmentService = new EmploymentService();
    Scanner scanner = new Scanner(System.in);

    public void askEmploymentMenu(){
        printEmploymentMenu();
        String choice = scanner.nextLine();
        switch(choice){
            case "1":
                System.out.print("기업명을 입력해주세요:" );
                String companyName = scanner.nextLine();
                List<EmploymentBoardVO> empList = employmentService.searchEmpBoard(companyName);
                System.out .println("-------------------------------------------------------");
                System.out .printf("%-6s%-20s%-20s%-40s\n", "no", "제목", "기업명", "채용과정");
                System.out .println("-------------------------------------------------------");

                empList.forEach(emp -> {
                    System.out.printf("%-6s%-20s%-20s%-40s \n",
                            emp.getEmploymentBoardId(),
                            emp.getTitle(),
                            emp.getCompanyName(),
                            emp.getHiringProcess()
                    );

                });

            case "2":
            case "3":
        }
    }

    public void printEmploymentMenu(){
        System.out.println("---------------------------------------------------------");
        System.out.println("--------------------관리자 채용관리 페이지---------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("1.채용 검색 2.채용 추가 3.뒤로 가기");
    }



}
