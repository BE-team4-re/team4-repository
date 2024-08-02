package src.employment.command;

public enum CommandEnum {
	
	COMMAND_CREATE("c", "생성"),
	COMMAND_READ("r", "읽기"),
	COMMAND_UPDATE("u", "수정"),
	COMMAND_DELETE("d", "삭제"),
	COMMAND_BACK("b", "뒤로가기"),
	COMMAND_NO("n", "아니오"),
	COMMAND_YES("y", "예");
	
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
	
}
