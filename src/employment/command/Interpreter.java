package src.employment.command;

import src.employment.board.BoardCategoryEnum;

public class Interpreter {
	
	private static Interpreter interpreter = null;
	private BoardCategoryEnum[] values = BoardCategoryEnum.values();
	
	private Interpreter() {
		
	}
	
	// 인터프리터 객체는 오직 하나만 허용됨. -> 명령 하나에 대한 해석을 해야 되기 때문에 멀티쓰레딩을 해서는 안됨.
	public static Interpreter getInterpreter() {
		if (interpreter == null) {
			interpreter = new Interpreter();
			return interpreter;
		}
		return interpreter;
	}
	
	public void interpret(String command) {
		// 커맨드가 들어오면, 명령어 사전에서 뒤져서 알맞은 명령어를 실행한다.
	}
	
}