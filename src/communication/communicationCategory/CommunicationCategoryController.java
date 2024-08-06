package src.communication.communicationCategory;

import src.communication.communicationCategory.dto.FindCommunicationBoardCategoryDto;
import src.util.Response;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommunicationCategoryController {
    private final CommunicationCategoryService communicationCategoryService = new CommunicationCategoryService();
    private final Scanner sc = new Scanner(System.in);
    public void createCommunicationCategory(){
        while(true){
            System.out.print("추가하려는 커뮤니티 카테고리 ->");
            String category = sc.nextLine();
            if(category.isBlank()){
                System.out.println("공백은 사용 불가합니다.");
            }
            System.out.println("추가하려는 카테고리는 -> " + category + "가 맞습니까?");
            System.out.println("1. 추가 2. 다시 작성");
            String selectNum = sc.nextLine();
            if(selectNum.equals("1")){
                boolean createCheck = communicationCategoryService.create(category);
                if(!createCheck)  createCommunicationCategory();
                break;
            }
        }
    }

    public void updateCommunicationCategory(){
        while(true){
            Response<List<FindCommunicationBoardCategoryDto>> response = communicationCategoryService.findAll();
            AtomicBoolean updateCheck = new AtomicBoolean(true);
            response.getData()
                .forEach(category -> {
                    System.out.println(category.communicationBoardCategoryId() + ". " + category.communicationBoardCategory());
                });
            System.out.print("수정하려는 카테고리의 번호를 입력해주세요 -> ");
            String selectNum = sc.nextLine();
            response.getData()
                .forEach(category -> {
                    if(category.communicationBoardCategoryId() == Integer.parseInt(selectNum)){
                        String updateCategory = sc.nextLine();
                        updateCheck.set(communicationCategoryService.update(category.communicationBoardCategoryId(), updateCategory));
                    }
                });
            if (updateCheck.get()) break;
            else updateCommunicationCategory();
        }

    }
}
