package src.employment.command;

public enum CommandEnum implements CommandMethod {

	// 뒤로가기
	COMMAND_GO_BACK("b", "뒤로가기") {
		@Override
		public void goBack() {
			// TODO Auto-generated method stub
			
		}
	},
	// CRUD
	COMMAND_CREATE("c", "생성") {
		@Override
		public void create() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_READ("r", "읽기") { // -> 글번호로 들어가는 상세보기와는 다름.
		@Override
		public void read() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_UPDATE("u", "수정") {
		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
	},
	// 업데이트 하위 COMMAND
	COMMAND_TITLE("t", "제목") {
		@Override
		public void updateTitle() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_JOBTYPE("j", "근무형태") {
		@Override
		public void updateJobType() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_CAREER("c", "요구학력") {
		@Override
		public void updateCareer() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_HIRING_PROCESS("h", "채용절차") {
		@Override
		public void updateHiringProcess() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_QUALIFICATIONS("q", "자격요건") {
		@Override
		public void updateQualifications() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_PREFERRED("p", "우대사항") {
		@Override
		public void updatePreferred() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_MAIN_CATEGORY1_ID("1", "메인카테고리1") {
		@Override
		public void updateMainCategory1Id() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_MAIN_CATEGORY2_ID("2", "메인카테고리2") {
		@Override
		public void updateMainCategory2Id() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_SUB_CATEGORY1_ID("3", "서브카테고리1") {
		@Override
		public void updateSubCategory1Id() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_SUB_CATEGORY2_ID("4", "서브카테고리2") {
		@Override
		public void updateSubCategory2Id() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_DELETE("d", "삭제") {
		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}
	},
	// 예/아니오
	COMMAND_YES("y", "예") {
		@Override
		public void sayYes() {
			// TODO Auto-generated method stub
			
		}
	},
	COMMAND_NO("n", "아니오") {
		@Override
		public void sayNo() {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	private String commandKey;
	private String commandName;
	
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

	// 뒤로가기
	public void goBack() {};
	// CRUD
	public void create() {};
	public void read() {};
	public void update() {};
	public void updateTitle() {};
	public void updateJobType() {};
	public void updateCareer() {};
	public void updateHiringProcess() {};
	public void updateQualifications() {};
	public void updatePreferred() {};
	public void updateMainCategory1Id() {};
	public void updateMainCategory2Id() {};
	public void updateSubCategory1Id() {};
	public void updateSubCategory2Id() {};
	public void delete() {};
	// 예/아니오
	public void sayYes() {};
	public void sayNo() {};
	
}
