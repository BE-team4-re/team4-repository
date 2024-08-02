package src.employment.command;

public class Interpreter {
	
	private static Interpreter interpreter = null;
	
	private Interpreter() {
		
	}
	
	public static Interpreter getInterpreter() {
		if (interpreter == null) {
			interpreter = new Interpreter();
			return interpreter;
		}
		return interpreter;
	}
	
	public void interpret(String command) {
		
	}
	
}