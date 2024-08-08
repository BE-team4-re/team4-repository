package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.*;
import src.communication.communicationCategory.CommunicationCategoryService;
import src.communication.communicationCategory.dto.FindCommunicationBoardCategoryDto;
import src.communication.communicationComment.CommunicationBoardCommentController;
import src.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CommunicationBoardController {
    private final Scanner sc = new Scanner(System.in);
    private final CommunicationBoardService communicationBoardService = new CommunicationBoardService();
    private final CommunicationCategoryService communicationCategoryService = new CommunicationCategoryService();
    private final CommunicationBoardCommentController  communicationBoardCommentController = new CommunicationBoardCommentController();
    private int selectCategoryId = 0;
    private int selPage = 1;
    private int selectPage = 1;
//    MainController mainController = new MainController();


    // 커뮤니케이션 게시물 생성
    public boolean createCommunicationBoard(int id){
        // 커뮤니케이션 카테고리를 가져온다.
        Response<List<FindCommunicationBoardCategoryDto>> category =  communicationCategoryService.findAll();
        boolean result = true;
        // 카테고리 가져오는데 성공 했다면 카테고리 내용을 보여준다.
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

            try{
                System.out.print("카테고리 번호를 입력해주세요 -> ");
                int selectCategory = Integer.valueOf(sc.nextLine()) - 1;
                System.out.print("제목 --> ");
                String title = sc.nextLine();
                System.out.print("내용 --> ");
                String content = sc.nextLine();
                // 커뮤니티 게시물 생성에 필요한 데이터를 담아준다.
                CreateCommunicationBoardDto createCommunicationBoard = new CreateCommunicationBoardDto(title,content,id,categoryList.get(selectCategory).communicationBoardCategoryId());
                // 커뮤니티 게시물을 저장한다.
                Response<Integer> response = communicationBoardService.create(createCommunicationBoard);
                if(response.isSuccess()){
                    System.out.println(response.getMessage());
                    result = response.isSuccess();
                }else{
                    System.out.println(response.getMessage());
                    result =  response.isSuccess();
                }
            }catch (IndexOutOfBoundsException | NumberFormatException e){
                System.out.println("오류가 발생했습니다. 다시 시작합니다.");
                createCommunicationBoard(id);
            }
        }else{
            try{
                throw new Exception(category.getMessage());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return result;
    }
    // 커뮤니티 게시글 하나를 선택해서 보여준다. 글번호, 제목, 작성자, 작성글, 댓글, 대댓글
    public void findOneCommunicationBoard(int communicaionBoardId, int id){
        // 커뮤니티 게시물의 하나의 정보를 가져온다.
        Response<FindOneCommnicationBoardNCommentDto> response = communicationBoardService.findOneCommunicationBoardIdByBoardComment(communicaionBoardId);
        // 가져온 데이터가 있다면 그 정보를 콘솔로 띄워준다.
        if(response.isSuccess()){
            int line = response.getData().communicationBoard().content().length();
            boolean stop = true;
            System.out.println("===========================================");
            System.out.println("글번호 = > " + response.getData().communicationBoard().communicationBoardId());
            System.out.println("제목 => " + response.getData().communicationBoard().title());
            System.out.println("작성자 => " + response.getData().communicationBoard().userId());
            System.out.println("작성글 = > " + response.getData().communicationBoard().content());
            System.out.println("===========================================");
            System.out.println("====================댓글===================");
            response.getData().commentList().stream()
                    .forEach(comment -> {
                        if(comment.parentId() == 0){
                            System.out.println("🐽"+comment.userId());
                            System.out.println("-> " + comment.comment());
                        }else{
                            System.out.println("    🐽"+comment.userId());
                            System.out.println("    -> " + comment.comment());
                        }
                    });

            System.out.println("=============================================================");
            System.out.print("|  1. 댓글  |  2. 뒤로가기  |");

            // 작성한 게시물이 본인이라면 수정, 삭제 보여주기
            if(id == response.getData().communicationBoard().id() || id == 0){
                System.out.println("  3. 게시물 수정 |  4. 게시물 삭제  |");
            }
            System.out.println("=============================================================");
            System.out.print("번호 -> ");
            String selectNum = sc.nextLine();
            // 1번을 눌러 댓글 관련으로 들어간다.
            if(selectNum.equals("1")){
                System.out.println("=============================================================================");
                System.out.println("|  1. 댓글 쓰기  |  2. 대댓글 작성하기  |  3. 댓글, 대댓글 수정  |  4. 댓글, 대댓글 삭제  |");
                System.out.println("=============================================================================");
                System.out.print("번호 -> ");
                selectNum = sc.nextLine();
                // 댓글 생성한다.
                if(selectNum.equals("1")) {
                    boolean checkCreateComment = communicationBoardCommentController.createCommunicationBoardComment(response.getData().communicationBoard().communicationBoardId(), id);
                    if(checkCreateComment) {
                        findOneCommunicationBoard(communicaionBoardId, id);
                    }
                }
                // 먼저 게시글에 있는 댓글을 보여준다. 그 댓글 중 번호를 선택해 대댓글을 작성한다.
                else if(selectNum.equals("2")){
                    while(true){
                        // 게시글에 있는 댓글을 번호와 함께 보여준다.
                        System.out.println("게시물의 댓글 목록");
                        final String RESET = "\033[0m";      // 색상 초기화
                        final String YELLOW = "\033[33m";    // 노란색
                        response.getData().commentList().stream()
                            .forEach(comment -> {
                                if(comment.parentId() == 0){
                                    System.out.println("\033[1;34m 댓글 번호: \033[0m" + comment.commentId());
                                    System.out.println("🐽 " + comment.userId());
                                    System.out.println("-> " + comment.comment());
                                }
                            });
                        System.out.println("===========================================");
                        System.out.println("|         나가시려면 'q'를 입력해 주세요.        |");
                        System.out.println("|   대댓글을 달고 싶은 댓글의 번호를 입력해 주세요. |");
                        System.out.println("===========================================");
                        System.out.print("번호 입력-> ");
                        selectNum = sc.nextLine();
                        if(selectNum.equals("q")){
                            findOneCommunicationBoard(communicaionBoardId, id);
                            break;
                        }
                        AtomicBoolean checkComment = new AtomicBoolean(false);
                        String finalSelectNum = selectNum;
                        // 여기서 내가 선택한 댓글의 아이디(commentId)와 selectNum이 같은지 한번 체크한다.
                        // checkComment가 true이라면 선택한 아이디가 있는것이고 false이라면 잘못 선택한것이다.
                        response.getData().commentList().stream()
                            .forEach(comment -> {
                                if(comment.parentId() == 0){
                                    if(Integer.valueOf(finalSelectNum) == comment.commentId()) checkComment.set(true);
                                }
                            });
                        // true면 대댓글 생성 시작 문제 없이 성공했다면 다시 게시물을 다시 조회하여 최신화를 한다.
                        if(checkComment.get()) {
                            communicationBoardCommentController.createCommunicationBoardReComment(communicaionBoardId, id, Integer.valueOf(selectNum));
                            findOneCommunicationBoard(communicaionBoardId, id);
                        }
                        //false면 다시 시작
                        else System.out.println("없는 댓글입니다.");
                    }
                    // 댓글과 대댓글을 수정한다.
                }else if(selectNum.equals("3")){
                    while(true){
                        AtomicInteger count = new AtomicInteger(0);
                        System.out.println("=====작성한 댓글 대댓글 ======");
                        // 내가 작성한 댓글과 대댓글을 보여주며 없다면 다시 게시물을 보여준다.
                        if(id == 0){
                            response.getData().commentList().stream()
                                .forEach(comment -> {
                                    if(comment.parentId() == 0){
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("🐽"+comment.userId());
                                            System.out.println("-> " + comment.comment());
                                            count.getAndIncrement();
                                    }else{
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("    🐽"+comment.userId());
                                            System.out.println("    -> " + comment.comment());
                                            count.getAndIncrement();
                                    }
                                });

                        }else{
                            response.getData().commentList().stream()
                                .forEach(comment -> {
                                    if(comment.parentId() == 0){
                                        if(comment.id() == id){
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("🐽"+comment.userId());
                                            System.out.println("-> " + comment.comment());
                                            count.getAndIncrement();
                                        }
                                    }else{
                                        if(comment.id() == id){
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("    🐽"+comment.userId());
                                            System.out.println("    -> " + comment.comment());
                                            count.getAndIncrement();
                                        }
                                    }
                                });
                        }

                        System.out.println("======================");
                        // count를 통해 count가 0이 아니라면 내가 쓴 댓글이 있다는것이고 0이라면 내가 작성한 댓글과 대댓글이 없다.
                        if(Integer.valueOf(String.valueOf(count)) == 0) {
                            System.out.println("작성하신 댓글이 없습니다. 그래서 수정할 댓글이 없습니다.");
                            findOneCommunicationBoard(communicaionBoardId, id);
                            break;
                        }
                        // 여기서 댓글과 대댓글을 수정이 시작한다.
                        while(true){
                            // 여기는 수정할 내용을 담을 곳이다.
                            AtomicReference<String> updateComment = new AtomicReference<>("");
                            AtomicInteger commentId = new AtomicInteger();
                            // 여기서 댓글과 대댓글의 아이디를 선택한다.
                            System.out.print("수정을 원하시는 번호를 눌러주세요. 나가시려면 q를 입력해주세요. -> ");
                            String commentNum = sc.nextLine();
                            if(commentNum.equals("q")) findOneCommunicationBoard(communicaionBoardId, id);
                            AtomicInteger updateCount = new AtomicInteger(0);
                            // 내가 선택한 댓글의 아이디와 댓글의 아이디가 같은지 체크하여 맞다면 수정전 내용을 보여주고 수정을 시작한다.
                            response.getData().commentList().stream()
                                .forEach(comment -> {
                                    if(comment.commentId() == Integer.valueOf(commentNum)){
                                        updateCount.getAndIncrement();
                                        System.out.print("수정 전 => " + comment.comment()+ "\n");
                                        System.out.print("수정 내용 => ");
                                        commentId.set(comment.commentId());
                                        updateComment.set(sc.nextLine());
                                    }
                                });
                            // 공백은 입력을 못하게.
                            if(String.valueOf(updateComment).equals("")) System.out.println("공백은 입력이 불가합니다.");
                            // 공백이 아니라면 수정한것 이므로 수정을 한다.
                            else {
                                int updateCommentId = Integer.valueOf(String.valueOf(commentId));
                                boolean success = communicationBoardCommentController.updateComment(updateCommentId, updateComment.get());
                                // 여기서 수정을 성공했다면 다시 게시물을 보여준다.
                                if(success) findOneCommunicationBoard(communicaionBoardId, id);
                            }
                            // 선택한 번호가 틀릴 시
                            if(Integer.valueOf(String.valueOf(updateCount)) == 0) System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해주세요.");
                        }
                    }
                    // 댓글과 대댓글을 삭제를 한다.
                }else if(selectNum.equals("4")) {
                    while (true) {
                        AtomicInteger count = new AtomicInteger(0);
                        System.out.println("=====작성한 댓글 대댓글 ======");

                        if(id ==0 ){
                            // 내가 작성한 댓글들을 보여준다.
                            response.getData().commentList().stream()
                                .forEach(comment -> {
                                    if (comment.parentId() == 0) {
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("🐽" + comment.userId());
                                            System.out.println("-> " + comment.comment());
                                            count.getAndIncrement();
                                    } else {
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("    🐽" + comment.userId());
                                            System.out.println("    -> " + comment.comment());
                                            count.getAndIncrement();
                                    }
                                });
                        }else{
                            // 내가 작성한 댓글들을 보여준다.
                            response.getData().commentList().stream()
                                .forEach(comment -> {
                                    if (comment.parentId() == 0) {
                                        if (comment.id() == id) {
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("🐽" + comment.userId());
                                            System.out.println("-> " + comment.comment());
                                            count.getAndIncrement();
                                        }
                                    } else {
                                        if (comment.id() == id) {
                                            System.out.println(comment.commentId() + "번");
                                            System.out.println("    🐽" + comment.userId());
                                            System.out.println("    -> " + comment.comment());
                                            count.getAndIncrement();
                                        }
                                    }
                                });
                        }
                        System.out.println("======================");
                        // 내가 작성한 댓글이 있는지 체크
                        if (Integer.valueOf(String.valueOf(count)) == 0) {
                            System.out.println("작성하신 댓글이 없습니다. 그래서 삭제할 댓글이 없습니다.");
                            findOneCommunicationBoard(communicaionBoardId, id);
                            break;
                        }
                        System.out.print("삭제하려는 댓글 대댓글의 번호를 입력해주세요. 나가시려면 q를 입력해주세요. ->");
                        String deleteNum = sc.nextLine();
                        if(deleteNum.equals("q")) findOneCommunicationBoard(communicaionBoardId, id);
                        AtomicInteger deleteCount = new AtomicInteger(0);
                        AtomicInteger deleteCommentId = new AtomicInteger();
                        // 선택한 번호와 댓글이 맞는지 한번더 체크
                        response.getData().commentList()
                            .forEach(comment -> {
                                if (comment.commentId() == Integer.parseInt(deleteNum)) {
                                    deleteCount.getAndIncrement();
                                    deleteCommentId.set(comment.commentId());
                                }
                            });
                        // 삭제에 성공했다면 true 아니면 false
                        boolean deleteCheck = communicationBoardCommentController.deleteComment(Integer.parseInt(String.valueOf(deleteCommentId)));
                        // 선택한 번호가 잘못 되었다면.
                        if (Integer.parseInt(String.valueOf(deleteCount)) == 0)
                            System.out.println("작성하신 댓글과 대댓글이 없습니다.");
                        // 삭제에 성공했든 실패했든 다시 게시물 초기화
                        if (deleteCheck) {
                            findOneCommunicationBoard(communicaionBoardId, id);
                            break;
                        } else {
                            findOneCommunicationBoard(communicaionBoardId, id);
                            break;
                        }
                    }
                }
            }else if(selectNum.equals("2")) {
                aaa(id, 1, "", selectCategoryId);
//                searchCommunicationBoard(id); //2뒤 나가기 뒤로가기 다시 카테고리 선택으로 간다.
            }
            else if(selectNum.equals("3")){
                if(updateCommunicationBoard(communicaionBoardId)) {
                    findOneCommunicationBoard(communicaionBoardId,id); // 커뮤니티 게시물 수정
                }
            }else if(selectNum.equals("4")) {
                if(deleteCommunicationBoard(communicaionBoardId)) searchCommunicationBoard(id); // 커뮤니티 게시물 삭제
            }
        }else System.out.println("조회에 실패했습니다.");

    }


    public boolean updateCommunicationBoard(int communicationBoardId){
        // respose에는 communicationBoardId로 불러온 title과 content가 담기고 success에는 불러온 데이터의 상태가 담긴다.
        Response<FindOneCommunicationBoardDto> response = communicationBoardService.findCommunicationIdByCommunicationBoard(communicationBoardId);
        String title;
        String content;
        String selectNum;
        boolean success = true;
        if(response.isSuccess()){ // 불러온 데이터가 있다면 true
            title = response.getData().title(); // 불러온 원본의 title
            content = response.getData().content(); // 불러온 원본의 content
            System.out.println("나가려면 -> 1번 이외의 아무 키를 입력해주세요.");
            System.out.print("제목을 수정하시려면 1번 -> ");
            selectNum = sc.nextLine();
            // 수정하려는 제목
            if(selectNum.equals("1"))  {
                System.out.println("수정 전 제목 -> " + title);
                System.out.print("수정하려는 제목-> ");
                title = sc.nextLine();
                while(true){
                    if(title.isBlank()){ // 수정할때 공백 체크
                        System.out.println("공백은 입력이 불가합니다.");
                        System.out.println("====================");
                        System.out.println("다시 제목을 수정하시려면 1번을 눌러주세요. 아니라면 1번 이외의 아무 키를 입력해주세요.");
                        selectNum = sc.nextLine();
                        if(selectNum.equals("1")){
                            System.out.print("수정하려는 제목-> ");
                            title = sc.nextLine();
                        }else break;
                    }else break;
                }
            }
            System.out.println("나가려면 -> 2번 이외의 아무 키를 입력해주세요.");
            System.out.print("내용을 수정하시려면 2번 -> ");
            selectNum = sc.nextLine();
            // 수정하려는 내용
            if(selectNum.equals("2"))  {
                System.out.println("수정 전 내용 -> " + content);
                System.out.print("수정하려는 내용-> ");
                content = sc.nextLine();
                while(true){
                    if(content.isBlank()){ // 수정하려는 내용 공백 체크
                        System.out.println("공백은 입력이 불가합니다.");
                        System.out.println("====================");
                        System.out.println("다시 내용을 수정하시려면 1번을 눌러주세요. 1번 이외의 아무 키를 입력해주세요.");
                        selectNum = sc.nextLine();
                        if(selectNum.equals("1")){
                            System.out.println("수정 전 내용 -> " + response.getData().content());
                            System.out.print("수정하려는 내용-> ");
                            content = sc.nextLine();
                        }else break;
                    }else break;
                }
            }
            // 수정 시작
            if(!response.getData().title().equals(title) || !response.getData().content().equals(content)){
                Response<Integer> updateResponse = communicationBoardService.updateCommunicationBoard(response.getData().communicationBoardId(), title, content);
                // 수정에 성공했면
                if(updateResponse.isSuccess()) {
                    System.out.println(updateResponse.getMessage());
                }
            }else {
                success =  false;
                System.out.println("수정 하지 않았습니다.");
            }
        }else{
            System.out.println(response.getMessage());
            success =  false;
        }
        return success;


    }

    // 게시물 삭제
    public boolean deleteCommunicationBoard(int communicationBoardId){
        Response<Integer> response = communicationBoardService.delete(communicationBoardId);
        // 게시물 삭제 성공확인
        if(response.isSuccess()) System.out.println(response.getMessage());
        else System.out.println(response.getMessage());
        return response.isSuccess();
    }

    // 커뮤니티 게시물 카테고리로 검색
    public void searchCommunicationBoard(int id){
        // 데이터베이스에서 커뮤니티 카테고리 가져온다.
        Response<List<FindCommunicationBoardCategoryDto>> commnicationBoardCategotyList = communicationCategoryService.findAll();
        AtomicInteger count = new AtomicInteger(1);
        int categoryId = 0;
//        int selectPage = 1;
        String searchWord = "";
        // 커뮤니티 카테고리 보여준다.
        if(commnicationBoardCategotyList.isSuccess()){
            System.out.println("==============================================");
            System.out.print("1. 전체게시물");
            commnicationBoardCategotyList.getData().stream()
                    .forEach(category -> {
                        count.getAndIncrement();
                        System.out.print(" "+ count+". " +category.communicationBoardCategory());
                    });
            System.out.print(" 4. 메인화면");
            System.out.println();
            System.out.println("==============================================");
            System.out.print("보고싶은 게시물의 카테고리를 선택해주세요. -> ");
            try {
                int selectCategoryNum = Integer.valueOf(sc.nextLine());
                if(selectCategoryNum == 1) selectCategoryId = 0;
                else if(selectCategoryNum == 2) selectCategoryId = 1;
                else if(selectCategoryNum == 3) selectCategoryId = 2;
                else if(selectCategoryNum == 4) return;
            }catch (NumberFormatException e){
                searchCommunicationBoard(id);
            }
            // 선택된 카테고리의 게시물을 불러온다. 페이지 네이션을 사용해서 최대 5개의 게시물을 보여준다.
            aaa(id,selectPage,searchWord, selectCategoryId);
        }
    }

    public void aaa(int id, int selectPage, String searchWord, int selectCategoryId){
        // 선택된 카테고리의 게시물을 불러온다. 페이지 네이션을 사용해서 최대 5개의 게시물을 보여준다.
        boolean stop = true;
        while(true){
            List<Integer> page = new ArrayList<>();
            Response<PagenationCommunicationBoardDto> communicationBoard = communicationBoardService.searchCommunicationBoard(selectCategoryId,selectPage,searchWord);
            if(communicationBoard.getData() == null && selectPage == 1 && searchWord.equals("")){
                System.out.println("등록된 게시물이 없습니다. 게시글 작성 하시겠습니까?");
                System.out.println("1. 게시글 작성 2. 메인 화면");
                try {
                    int selectNum = Integer.valueOf(sc.nextLine());
                    if(selectNum == 1) {
                        createCommunicationBoard(id);
                        break;
                    }else if(selectNum == 2) stop = false;
                    if(!stop) break;
                }catch (NumberFormatException e){
                    System.out.println("숫자를 입력해주세요. 뒤로 갑니다.");
                    searchCommunicationBoard(id);
                }
            }else{
                // 페이지 번호를 찍기위해서
                try{
                    for(int i = selectPage; i <= communicationBoard.getData().totalPage(); i++) page.add(i);
                    if(selectCategoryId == 0) System.out.println("===================== 전체 게시물 =====================");
                    else if(selectCategoryId == 1) System.out.println("===================== 기업평가 =====================");
                    else if(selectCategoryId == 2) System.out.println("===================== 현직자 인터뷰 =====================");
                    System.out.printf("%-10s %-30s %-10s%n", "번호", "내용", "작성자");
                    // 받아온 게시물을 보여준다.
                    communicationBoard.getData().communicationBoardList().stream()
                        .forEach(commu -> {
                            System.out.printf("%-5d |%-30s| %-10s%n", commu.communicationBoardId(), commu.title(), commu.userId());
//                            System.out.println(commu.communicationBoardId()+". "+ commu.title() + "  " + commu.userId());
                        });
                    int pageCount = 0;
                    System.out.print("페이지 번호 -> ");
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
                }catch(NullPointerException err){
                    System.out.println("페이지가 존재하지 않습니다.");
                    aaa(id, 1,  "", selectCategoryId);
                    break;
                }
                System.out.println("1. 다른 페이지 2. 검색 3. 게시물 선택 4. 게시물 작성 5. 뒤로가기");
                try{
                    int selectNums = Integer.parseInt(sc.nextLine());
                    if(selectNums == 5){
                        searchCommunicationBoard(id);
                        break;
                    }// 다른 페이지를 볼 수 있다. 번호 입력해서
                    else if(selectNums == 1){
                        System.out.print("페이지를 번호를 입력해주세요.");
                        selectPage = Integer.valueOf(sc.nextLine());
                    }else if(selectNums == 2){ // 검색어를 입력해서 검색을 할 수 있다.
                        selectPage = 1;
                        System.out.print("검색하실 단어를 입력해주세요. -> ");
                        searchWord = sc.nextLine();
                    }else if(selectNums == 3){ // 게시물 선택하여 볼 수있다.
                        System.out.print("보고 싶은 게시물을 선택해주세요. -> ");
                        int communicationBoardId = Integer.valueOf(sc.nextLine());
                        findOneCommunicationBoard(communicationBoardId, id);
                        break;
                    }else if(selectNums == 4){
                        if(createCommunicationBoard(id)) {
                            searchCommunicationBoard(id);
                            break;
                        }
                        else throw new RuntimeException();
                    }
                }catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요. 뒤로갑니다.");
                    searchCommunicationBoard(id);
                    break;
                }catch (RuntimeException e){
                    System.out.println("게시물 등록에 실패했습니다. 뒤로갑니다.");
                    break;
                }
            }
        }
    }
}
