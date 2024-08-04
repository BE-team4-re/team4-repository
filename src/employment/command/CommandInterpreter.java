package src.employment.command;


import java.util.List;
import java.util.ArrayList;


public class CommandInterpreter {
	
	private static CommandInterpreter commandInterpreter = null;
	private CommandEnum[] commands = CommandEnum.values();
	
	private CommandInterpreter() {
		
	}
	
	// 인터프리터 객체는 오직 하나만 허용됨. -> 명령 하나에 대한 해석을 해야 되기 때문에 멀티쓰레딩을 해서는 안됨.
	public static CommandInterpreter getInterpreter() {
		if (commandInterpreter == null) {
			commandInterpreter = new CommandInterpreter();
			return commandInterpreter;
		}
		return commandInterpreter;
	}
	
	public FrameSet interpret(String userInputCommand) {
		FrameSet frameSet = null;
		// 커맨드가 들어오면, 명령어 사전에서 뒤져서 알맞은 명령어를 실행한다.
		for (CommandEnum command: commands) {
			String commandKey = command.getCommandKey();
			if (userInputCommand.equals(commandKey)) {
				System.out.println(command.getCommandName());
				frameSet = command.execute();
			}
		}
		return frameSet;
	}
	
}