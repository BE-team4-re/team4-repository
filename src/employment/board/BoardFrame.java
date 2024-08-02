package src.employment.board;

public class BoardFrame {
	
	BoardPrinter printer = null;
	
	public BoardFrame() {
		printer = new BoardPrinter();
	}
	
	public void printFrame() {
		System.out.println("#########################################################################");
		System.out.println("##########\t\t\t메인 게시판 입니다.\t\t\t##########");
		System.out.println("#########################################################################");
		System.out.println("-------------------------------------------------------------------------");
		if (printer != null) {
			printer.printBoard();
		} else {
			System.out.println("아직 게시물이 없습니다.");
		}
		System.out.println("#########################################################################");
	}
}
