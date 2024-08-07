package src.admin.category;

import src.employment.board.BoardCategoryDTO;

import java.util.List;
import java.util.Scanner;

public class CategoryController {
    CategoryService categoryService = new CategoryService();
    Scanner scanner = new Scanner(System.in);

    public int askCategorySelectMenu(){
        while (true) {
            String categoryChoice = categorySelectMenu();
            switch (categoryChoice) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                default:
                    System.out.println("잘못된 입력입니다");
            }
        }
    }

    //카테고리 메뉴
    public void askCategoryMenu(){
        while (true) {
            int categoryNum = askCategorySelectMenu();
            if (categoryNum == 3) return;
            printCategoryMenu(categoryNum);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    askInsertCategoryMenu();
                    return;
                case "2":
                    askUpdateCategoryMenu();
                    return;
                case "3":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

    public String categorySelectMenu(){
        System.out.println("\n \n \n");
        System.out.println(":::카테고리 선택:::");
        System.out.println("+=============================================================================+");
        System.out.println("|         1. 지역별        |         2. 직무별         |        3. 뒤로 가기       |");
        System.out.println("+=============================================================================+");
        System.out.print("번호를 입력해주세요: ");
        String index = scanner.nextLine();
        return index;
    }

    //카테고리 수정
    public void askUpdateCategoryMenu(){
        while (true) {
                System.out.print("수정하실 카테고리의 id를 골라주세요:");
                String str_categoryId = scanner.nextLine();
                try{
                    int categoryId = Integer.parseInt(str_categoryId);
                    System.out.print("수정하실 카테고리 이름을 적어주세요:");
                    String categoryName = scanner.nextLine();
                    categoryService.updateCategory(categoryId, categoryName);
                    return;
                }
                catch (NumberFormatException e){
                    System.out.println("id는 숫자만 가능합니다.");
                }

        }
    }

    //카테고리 추가
    public void askInsertCategoryMenu(){
        while (true) {

            System.out.println("\n \n \n");
            System.out.println(":::카테고리 추가:::");
            System.out.println("+=============================================================================+");
            System.out.println("|                1. 지역별               |                2. 직무별               |");
            System.out.println("+=============================================================================+");

            System.out.print("번호를 입력해주세요 :");

            String categoryChoice = scanner.nextLine();
            switch (categoryChoice) {
                case "1":
                    while (true) {
                        System.out.print("추가하실 카테고리 지역이름을 작성해주세요: ");
                        String localName = scanner.nextLine();
                        if (localName == null || localName.trim().isEmpty()) {
                            System.out.println("지역이름은 비어있을 수 없습니다.");
                        } else {
                            categoryService.insertCategory(localName, 1);
                            ;
                            return;
                        }
                    }
                case "2":
                    while (true) {
                        System.out.print("추가하실 카테고리 직무이름을 작성해주세요: ");
                        String jobName = scanner.nextLine();
                        if (jobName == null || jobName.trim().isEmpty()) {
                            System.out.println("직무이름은 비어있을 수 없습니다.");
                        }else{
                            categoryService.insertCategory(jobName, 2);
                            return;
                        }
                    }

                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    //전체 카테고리
    public void printCategoryMenu(int categoryNum){
        System.out.println("\n \n \n");
        System.out.println(":::카테고리 리스트:::");
        System.out.println("+=============================================================================+");
        System.out.printf("%-6s\t%-20s\t%-20s\t%-40s \n", "id", "메인카테고리id", "서브카테고리id", "카테고리이름");
        System.out.println("+=============================================================================+");

        List<BoardCategoryDTO> dtolist = categoryService.getDetailCategoryList(categoryNum);
        dtolist.stream()
                .forEach(
                categoryDTO -> {
                    System.out.printf("%-6s\t%-20s\t%-20s\t%-30s \n",
                            categoryDTO.getCategoryId(),
                            categoryDTO.getMainCategoryId(),
                            categoryDTO.getSubCategoryId(),
                            categoryDTO.getCategoryName()
                    );
                }
        );
        System.out.println("+=============================================================================+");
        System.out.println("1. 추가하기 2.수정하기 3.관리자 홈 이동");
        System.out.print("번호를 입력해주세요: ");
    }
}
