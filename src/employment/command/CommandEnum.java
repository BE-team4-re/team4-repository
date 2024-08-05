package src.employment.command;


import src.employment.board.BoardCategoryEnum;
import src.employment.command.FrameSet;
import src.employment.frame.buttons.*;
import src.employment.frame.buttons.read.ByJobDetail;
import src.employment.frame.buttons.read.ByRegionDetail;
import src.employment.frame.buttons.read.ByRegionDetailAndJobDetail;
import src.employment.frame.buttons.update.*;
import src.employment.frame.buttons.update.TitleButton;
import src.employment.frame.contents.PrintBoard;
import src.employment.recordDAO.employmentBoard.update.UpdateDAO;
import src.employment.recordDAO.employmentBoard.read.ReadDAO;
import src.employment.test.TestMain;
import src.util.Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Buttons {
	public static Update update = new Update();

	public static TitleButton titleButton = new TitleButton();
	public static JobTypeButton jobTypeButton = new JobTypeButton();
	public static CareerButton careerButton = new CareerButton();
	public static HiringProcessButton hiringProcessButton = new HiringProcessButton();
	public static QualificationsButton qualificationsButton = new QualificationsButton();
	public static PreferredButton preferredButton = new PreferredButton();
	public static CompanyNameButton companyNameButton = new CompanyNameButton();
	public static MainCategory1IdButton mainCategory1IdButton = new MainCategory1IdButton();
	public static MainCategory2IdButton mainCategory2IdButton = new MainCategory2IdButton();
	public static SubCategory1IdButton subCategory1IdButton = new SubCategory1IdButton();
	public static SubCategory2IdButton subCategory2IdButton = new SubCategory2IdButton();
	public static Complete complete = new Complete();

	public static ByJob byJob = new ByJob();
	public static ByRegion byRegion = new ByRegion();
	public static ByJobDetail byJobDetail = new ByJobDetail();
	public static ByRegionDetail byRegionDetail = new ByRegionDetail();
	public static ByRegionDetailAndJobDetail byRegionDetailAndJobDetail = new ByRegionDetailAndJobDetail();
}


public enum CommandEnum {
//	뒤로가기
	COMMAND_GO_BACK("b", "돌아가기") {
		@Override
		public FrameSet execute() {
			String title = "메인 게시판 입니다.";
			PrintBoard pb = new PrintBoard();
			Callable method = pb::printAllBoards;

			List<Button> buttonList = new ArrayList<>();

			buttonList.add(Buttons.update);
			buttonList.add(Buttons.byRegion);
			buttonList.add(Buttons.byJob);

			FrameSet frameSet = new FrameSet(title, method, buttonList);
			return frameSet;
		};
	},
//	COMMAND_READ("r", "읽기") { // -> 글번호로 들어가는 상세보기와는 다름.
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		};
//	},
	COMMAND_UPDATE("u", "수정") {
		@Override
		public FrameSet execute() {
			Scanner sc = new Scanner(System.in);
			String title = "채용 공고 수정 페이지 입니다.";
			Callable callable = () -> {
				TestMain.allowedCommands = "";
				System.out.print("수정을 원하는 채용 공고의 번호를 입력해주세요: ");
				String userInput = sc.nextLine();
				try {
					int userInputNumber = Integer.parseInt(userInput);
					PrintBoard pb = new PrintBoard();
					pb.printOneBoard(userInputNumber); // 한번 보여줌.
					UpdateDAO.updateTarget = userInputNumber;
					System.out.println("변경하실 항목을 선택해주세요.");
					TestMain.allowedCommands = "tjchqpcn1234";
				} catch	(NumberFormatException e) {
					System.out.println("숫자를 입력하세요.");
				}
			};
			List<Button> buttonList = new ArrayList<>();

			buttonList.add(Buttons.titleButton);
			buttonList.add(Buttons.jobTypeButton);
			buttonList.add(Buttons.careerButton);
			buttonList.add(Buttons.hiringProcessButton);
			buttonList.add(Buttons.qualificationsButton);
			buttonList.add(Buttons.preferredButton);
			buttonList.add(Buttons.companyNameButton);
			buttonList.add(Buttons.mainCategory1IdButton);
			buttonList.add(Buttons.subCategory1IdButton);
			buttonList.add(Buttons.mainCategory2IdButton);
			buttonList.add(Buttons.subCategory2IdButton);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	// 업데이트 하위 COMMAND
	COMMAND_TITLE("t", "제목") {
		@Override
		public FrameSet execute() {
			String title = "제목 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "t");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_JOBTYPE("j", "근무형태") {
		@Override
		public FrameSet execute() {
			String title = "근무형태 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "j");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_CAREER("c", "요구학력") {
		@Override
		public FrameSet execute() {
			String title = "요구학력 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "c");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_HIRING_PROCESS("h", "채용절차") {
		@Override
		public FrameSet execute() {
			String title = "채용절차 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "h");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_QUALIFICATIONS("q", "자격요건") {
		@Override
		public FrameSet execute() {
			String title = "자격요건 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "q");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_PREFERRED("p", "우대사항") {
		@Override
		public FrameSet execute() {
			String title = "우대사항 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "p");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_COMPANY_NAME("n", "회사명") {
		@Override
		public FrameSet execute() {
			String title = "회사명 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "n");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_MAIN_CATEGORY1_ID("1", "메인카테고리1") {
		@Override
		public FrameSet execute() {
			String title = "메인카테고리1 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "1");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_SUB_CATEGORY1_ID("2", "서브카테고리1") {
		@Override
		public FrameSet execute() {
			String title = "서브카테고리1 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "3");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_MAIN_CATEGORY2_ID("3", "메인카테고리2") {
		@Override
		public FrameSet execute() {
			String title = "메인카테고리2 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "2");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_SUB_CATEGORY2_ID("4", "서브카테고리2") {
		@Override
		public FrameSet execute() {
			String title = "서브카테고리2 수정하기.";

			TestMain.allowedCommands = "";

			Callable callable = () -> {
				UpdateDAO dao = new UpdateDAO();
				dao.update(UpdateDAO.updateTarget, "4");
			};
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	// 메인에서 조회 가능.
	COMMAND_BY_REGION("R", "지역별 조회") {
		@Override
		public FrameSet execute() {
			String title = "지역별로 조회하기.";
			PrintBoard pb = new PrintBoard();
			Callable callable = pb::printAllBoardsByRegion;
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.byJob);
			buttonList.add(Buttons.byJobDetail);
			buttonList.add(Buttons.byRegionDetail);
			buttonList.add(Buttons.byRegionDetailAndJobDetail);

			TestMain.allowedCommands = "JDLS";

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_BY_JOB("J", "직무별 조회") {
		@Override
		public FrameSet execute() {
			String title = "직무별로 조회하기.";
			PrintBoard pb = new PrintBoard();
			Callable callable = pb::printAllBoardsByJob;
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.byRegion);
			buttonList.add(Buttons.byJobDetail);
			buttonList.add(Buttons.byRegionDetail);
			buttonList.add(Buttons.byRegionDetailAndJobDetail);

			TestMain.allowedCommands = "RDLS";

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_BY_REGION_DETAIL("L", "상세 지역으로 찾기") {
		@Override
		public FrameSet execute() {
			String title = "상세 지역으로 조회하기.";

			TestMain.allowedCommands = "JDLS";

			Scanner sc = new Scanner(System.in);
			System.out.print("조회할 지역을 입력하세요: ");
			String UserInput = sc.nextLine();
			BoardCategoryEnum[] values = BoardCategoryEnum.values();
			List<Integer> subIdList = new ArrayList<>();
			for (BoardCategoryEnum value: values) {
				if (value.getCategoryName().contains(UserInput)) {
					subIdList.add(value.getSubId());
				}
			}
			final List<Integer> finalSubIdList = subIdList;

			PrintBoard pb = new PrintBoard();
			Callable callable = () -> pb.printAllBoardsByRegionDetail(finalSubIdList);

			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.byRegion);
			buttonList.add(Buttons.byJobDetail);
			buttonList.add(Buttons.byRegionDetail);
			buttonList.add(Buttons.byRegionDetailAndJobDetail);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_BY_JOB_DETAIL("D", "상세 직무로 찾기") {
		@Override
		public FrameSet execute() {
			String title = "상세 직무로 조회하기.";

			TestMain.allowedCommands = "RDLS";

			Scanner sc = new Scanner(System.in);
			System.out.print("조회할 직무를 입력하세요: ");
			String UserInput = sc.nextLine();
			BoardCategoryEnum[] values = BoardCategoryEnum.values();
			List<Integer> subIdList = new ArrayList<>();
			for (BoardCategoryEnum value: values) {
				if (value.getCategoryName().contains(UserInput)) {
					subIdList.add(value.getSubId());
				}
			}
			final List<Integer> finalSubIdList = subIdList;

			PrintBoard pb = new PrintBoard();
			Callable callable = () -> pb.printAllBoardsByJobDetail(finalSubIdList);
			
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.byRegion);
			buttonList.add(Buttons.byJobDetail);
			buttonList.add(Buttons.byRegionDetail);
			buttonList.add(Buttons.byRegionDetailAndJobDetail);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_BY_REGION_DETAIL_AND_JOB_DETAIL("S", "상세 지역과 직무로 찾기") {
		@Override
		public FrameSet execute() {
			String title = "지역과 직무로 조회하기.";

			TestMain.allowedCommands = "";

			Scanner sc = new Scanner(System.in);
			System.out.print("조회할 지역을 입력하세요: ");
			String UserInput1 = sc.nextLine();
			System.out.println();
			System.out.print("조회할 직무를 입력하세요: ");
			String UserInput2 = sc.nextLine();
			BoardCategoryEnum[] values = BoardCategoryEnum.values();
			int subId1 = 1;
			int subId2 = 19;
			for (BoardCategoryEnum value: values) {
				if (value.getCategoryName().equals(UserInput1)) {
					subId1 = value.getSubId();
				}
				if (value.getCategoryName().equals(UserInput2)) {
					subId2 = value.getSubId();
				}
			}
			final int finalSubId1 = subId1;
			final int finalSubId2 = subId2;

			PrintBoard pb = new PrintBoard();
			Callable callable = () -> pb.printAllBoardsByRegionDetailAndJobDetail(finalSubId1, finalSubId2);
			
			List<Button> buttonList = new ArrayList<>();
			buttonList.add(Buttons.complete);

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_COMPLETE("", "확인") {
		@Override
		public FrameSet execute() {
			String title = "메인 게시판 입니다.";
			PrintBoard ab = new PrintBoard();
			Callable method = () -> ab.printAllBoards();

			List<Button> buttonList = new ArrayList<>();

			buttonList.add(Buttons.update);
			buttonList.add(Buttons.byRegion);
			buttonList.add(Buttons.byJob);

			TestMain.allowedCommands = "uRJ";

			FrameSet frameSet = new FrameSet(title, method, buttonList);
			return frameSet;
		};
	};
	
	
	private final String commandKey;
	private final String commandName;
	
	private CommandEnum(String commandKey, String commandName) {
		this.commandKey = commandKey;
		this.commandName = commandName;
	}

	public String getCommandKey() {
		return commandKey;
	}

	public String getCommandName() {
		return commandName;
	}
	public abstract FrameSet execute();
}
