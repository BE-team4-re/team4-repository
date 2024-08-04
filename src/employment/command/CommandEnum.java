package src.employment.command;


import src.employment.command.FrameSet;
import src.employment.frame.buttons.*;
import src.employment.frame.buttons.update.*;
import src.employment.frame.buttons.update.TitleButton;
import src.employment.frame.contents.PrintBoard;
import src.employment.recordDAO.employmentBoard.update.DAO;
import src.util.Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Buttons {
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
}


public enum CommandEnum {
	// 뒤로가기
//	COMMAND_GO_BACK("b", "뒤로가기") {
//		@Override
//		public FrameSet execute() {
//
//
//		};
//	},
//	// CRUD
//	COMMAND_CREATE("c", "생성") {
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		};
//	},
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
				System.out.print("수정을 원하는 채용 공고의 번호를 입력해주세요: ");
				String userInput = sc.nextLine();
				try {
					int userInputNumber = Integer.parseInt(userInput);
					PrintBoard pb = new PrintBoard();
					pb.printOneBoard(userInputNumber); // 한번 보여줌.
					DAO.updateTarget = userInputNumber;
					System.out.println("변경하실 항목을 선택해주세요.");
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
			buttonList.add(Buttons.mainCategory2IdButton);
			buttonList.add(Buttons.subCategory1IdButton);
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
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "t");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_JOBTYPE("j", "근무형태") {
		@Override
		public FrameSet execute() {
			String title = "근무형태 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "j");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_CAREER("c", "요구학력") {
		@Override
		public FrameSet execute() {
			String title = "요구학력 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "c");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_HIRING_PROCESS("h", "채용절차") {
		@Override
		public FrameSet execute() {
			String title = "채용절차 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "h");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_QUALIFICATIONS("q", "자격요건") {
		@Override
		public FrameSet execute() {
			String title = "자격요건 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "q");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_PREFERRED("p", "우대사항") {
		@Override
		public FrameSet execute() {
			String title = "우대사항 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "p");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_MAIN_CATEGORY1_ID("1", "메인카테고리1") {
		@Override
		public FrameSet execute() {
			String title = "메인카테고리1 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "1");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_MAIN_CATEGORY2_ID("2", "메인카테고리2") {
		@Override
		public FrameSet execute() {
			String title = "메인카테고리2 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "2");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_SUB_CATEGORY1_ID("3", "서브카테고리1") {
		@Override
		public FrameSet execute() {
			String title = "서브카테고리1 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "3");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
	COMMAND_SUB_CATEGORY2_ID("4", "서브카테고리2") {
		@Override
		public FrameSet execute() {
			String title = "서브카테고리2 수정하기.";
			Callable callable = () -> {
				DAO dao = new DAO();
				dao.update(DAO.updateTarget, "4");
			};
			List<Button> buttonList = new ArrayList<>();

			FrameSet frameSet = new FrameSet(title, callable, buttonList);
			return frameSet;
		};
	},
//	COMMAND_DELETE("d", "삭제") {
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		};
//	},
//	// 예/아니오
//	COMMAND_YES("y", "예") {
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		};
//	},
//	COMMAND_NO("n", "아니오") {
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		};
//	},
	COMMAND_COMPLETE("P", "완료") {
		@Override
		public FrameSet execute() {
			String title = "메인 게시판 입니다.";
			PrintBoard ab = new PrintBoard();
			Callable method = () -> ab.printAllBoards();

			List<Button> buttonList = new ArrayList<>();

			ReadDetail rd = new ReadDetail();
			Delete d = new Delete();
			Update u = new Update();
			Back b = new Back();
			buttonList.add(rd);
			buttonList.add(u);
			buttonList.add(d);
			buttonList.add(b);

			FrameSet frameSet = new FrameSet(title, method, buttonList);
			return frameSet;
		};
//	},
//	COMMAND_NO("C", "취소") {
//		@Override
//		public FrameSet execute() {
//			// TODO Auto-generated method stub
//
//		}
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
