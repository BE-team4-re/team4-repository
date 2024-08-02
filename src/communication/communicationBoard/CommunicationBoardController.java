package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.CreateCommunicationBoardDto;
import src.communication.communicationBoard.dto.PagenationCommunicationBoardDto;
import src.communication.communicationBoard.dto.UpdateCommunicationBoardDto;
import src.communication.communicationCategory.CommunicationCategoryService;
import src.communication.communicationCategory.dto.FindCommunicationBoardCategoryDto;
import src.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicationBoardController {
    private final Scanner sc = new Scanner(System.in);
    private final CommunicationBoardService communicationBoardService = new CommunicationBoardService();
    private final CommunicationCategoryService communicationCategoryService = new CommunicationCategoryService();
    public void createCommunicationBoard(int id){
        Response<List<FindCommunicationBoardCategoryDto>> category =  communicationCategoryService.findAll();
        if(category.isSuccess()){
            List<FindCommunicationBoardCategoryDto> categoryList = category.getData();
            AtomicInteger count = new AtomicInteger(0);
            System.out.println("===================================");
            System.out.println("======== 커뮤니티 카테고리 선택 ========");
            System.out.print("======");
            categoryList.stream()
                    .forEach(c -> {
                        count.getAndIncrement();
                        System.out.print(" "+count +". " +c.communicationBoardCategory());
                    });
            System.out.print("======");
            System.out.println();
            System.out.println("====================================");
            System.out.print("카테고리 번호를 입력해주세요 -> ");
            int selectCategory = Integer.valueOf(sc.nextLine()) - 1;
            System.out.print("제목 --> ");
            String title = sc.nextLine();
            System.out.print("내용 --> ");
            String content = sc.nextLine();
            try{
                CreateCommunicationBoardDto createCommunicationBoard = new CreateCommunicationBoardDto(title,content,id,categoryList.get(selectCategory).communicationBoardCategoryId());
                Response<Integer> response = communicationBoardService.create(createCommunicationBoard);
                if(response.isSuccess()){
                    System.out.println(response.getMessage());
                }else{
                    System.out.println(response.getMessage());
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("오류가 발생했습니다. 다시 시작합니다.");
                createCommunicationBoard(id);
            }
        }else{
            try{
                throw new Exception(category.getMessage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void updateCommunicationBoard(int communicationBoardId){
        // respose에는 communicationBoardId로 불러온 title과 content가 담기고 success에는 불러온 데이터의 상태가 담긴다.
        Response<UpdateCommunicationBoardDto> response = communicationBoardService.findCommunicationIdByCommunicationBoard(communicationBoardId);
        System.out.println(response.isSuccess());
        String title;
        String content;
        int selectNum;
        if(response.isSuccess()){ // 불러온 데이터가 있다면 true
            title = response.getData().title(); // 불러온 원본의 title
            content = response.getData().content(); // 불러온 원본의 content
            System.out.println("나가려면 -> 아무 숫자를 입력해주세요.");
            System.out.println("수정 전 내용 -> " + title);
            System.out.print("제목을 수정하시려면 1번 -> ");
            selectNum = Integer.valueOf(sc.nextLine());
            if(selectNum == 1)  {
                System.out.println("수정 전 제목 -> " + title);
                System.out.print("수정하려는 제목-> ");
                title = sc.nextLine();
                while(true){
                    if(title.isBlank()){ // 수정할때 공백 체크
                        System.out.println("공백은 입력이 불가합니다.");
                        System.out.println("====================");
                        System.out.println("다시 제목을 수정하시려면 1번을 눌러주세요. 아니라면 아무 숫자를 입력해주세요.");
                        selectNum = Integer.valueOf(sc.nextLine());
                        if(selectNum == 1){
                            System.out.print("수정하려는 제목-> ");
                            title = sc.nextLine();
                        }else break;
                    }else break;
                }
            }

            System.out.print("내용을 수정하시려면 2번 -> ");
            selectNum = Integer.valueOf(sc.nextLine());
            if(selectNum == 2)  {
                System.out.print("수정하려는 내용-> ");
                System.out.println("수정 전 내용 -> " + content);
                content = sc.nextLine();
                while(true){
                    if(content.isBlank()){ // 수정하려는 내용 공백 체크
                        System.out.println("공백은 입력이 불가합니다.");
                        System.out.println("====================");
                        System.out.println("다시 내용을 수정하시려면 1번을 눌러주세요. 아니라면 아무 숫자를 입력해주세요.");
                        selectNum = Integer.valueOf(sc.nextLine());
                        if(selectNum == 1){
                            System.out.print("수정하려는 내용-> ");
                            System.out.println("수정 전 내용 -> " + content);
                            content = sc.nextLine();
                        }else break;
                    }else break;
                }
            }
            // 수정 시작
            if(!response.getData().title().equals(title) && !response.getData().content().equals(content)){
                System.out.println("수정 시작");
                Response<Integer> updateResponse = communicationBoardService.updateCommunicationBoard(communicationBoardId, title, content);
                if(updateResponse.isSuccess()) System.out.println(updateResponse.getMessage());
            }else System.out.println("수정 하지 않았습니다.");
        }else{
            System.out.println(response.getMessage());
        }

    }

    public void deleteCommunicationBoard(int communicationBoardId){
        Response<Integer> response = communicationBoardService.delete(communicationBoardId);
        if(response.isSuccess()) System.out.println(response.getMessage());
        else System.out.println(response.getMessage());
    }

    public void searchCommunicationBoard(){
        Response<List<FindCommunicationBoardCategoryDto>> commnicationBoardCategotyList = communicationCategoryService.findAll();
        AtomicInteger count = new AtomicInteger(1);
        int categoryId = 0;
        int selectPage = 1;
        if(commnicationBoardCategotyList.isSuccess()){
            System.out.println("===================================");
            System.out.print("1. 전체게시물");
            commnicationBoardCategotyList.getData().stream()
                    .forEach(category -> {
                        count.getAndIncrement();
                        System.out.print(" "+ count+". " +category.communicationBoardCategory());
                    });
            System.out.println();
            System.out.println("===================================");
            System.out.print("보고싶은 게시물을 선택해주세요. -> ");
            int selectNum = Integer.valueOf(sc.nextLine());
            if(selectNum == 1) categoryId = 0;
            else if(selectNum == 2) categoryId = 1;
            else if(selectNum == 3) categoryId = 2;
            while(true){
                Response<PagenationCommunicationBoardDto> communicationBoard = communicationBoardService.searchCommunicationBoard(categoryId,selectPage,"");
                List<Integer> page = new ArrayList<>();
                for(int i = 1; i <= communicationBoard.getData().totalPage(); i++) page.add(i);
                if(selectNum == 1) System.out.println("========= 전체 게시물 =========");
                else System.out.println("=====" + commnicationBoardCategotyList.getData().get(categoryId - 1).communicationBoardCategory() + "=========");
                System.out.println("번호       내용           작성자 ");
                communicationBoard.getData().communicationBoardList().stream()
                    .forEach(commu -> System.out.println(commu.communicationBoardId()+". "+ commu.title() + "  " + commu.userId()));
                int pageCount = 0;
                for(int i = page.get(0); i <= page.size(); i++){
                    pageCount++;
                    if(pageCount == 5) {
                        System.out.println(i + "   [다음]");
                        break;
                    }
                    if(i == page.size()) System.out.println(i);
                    else if(i == page.get(0)) System.out.print("["+i+"], ");
                    else System.out.print(i+", ");
                }
                System.out.println("1. 다른 페이지 2. 검색 3. 나가기");
                int nextInt = Integer.valueOf(sc.nextLine());
                if(nextInt == 3) break;
            }
        }
    }

}
