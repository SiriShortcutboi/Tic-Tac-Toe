package Game;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
public class TicTacToeAi {
Scanner input = new Scanner(System.in);
	String human;
	String computer;

	public void Intro() {

		System.out.println("Welcome to the great intellectual challenge of all time: Tic-Tc-Toe");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
	}

	public String yes_no_response(String question) {
		String response = "";
		while (true) {
			System.out.println(question);
			response = input.nextLine();
			if (response.equalsIgnoreCase("yes")){
				break;
			}
			else if (response.equalsIgnoreCase("no")) {
				break;
			}
				
			}
		return response;
	}

	public int get_number(String question, int low, int high) {
		while (true) {
			
			int numInput;
			try {
				System.out.println(question);
				numInput = input.nextInt();
				if ((numInput>=low&&numInput <=high)) {
					
					return numInput;
				}
				System.out.println("Invalid input; re-enter slot number:");
				
			}catch(InputMismatchException e) {
				input.nextLine();
			continue;
			}
		}
		
	}
	
	public void pieces(String X, String O) {
		String go_first = yes_no_response("Do you require the first move? (yes/no): ");
		if (go_first.equals("yes")) {
			human = X;
			computer = O;
			
		}
		else {
			human = O;
			computer = X;
			
		}
	}
	
	public String[] new_board(int NUM_SQUARES, String EMPTY) {
		String[] board = new String[NUM_SQUARES];
		for (int i = 0;i<NUM_SQUARES;i++) {		
			board[i]=EMPTY;
		}
			
		return board;
					
	}

	public void display_board(String[] board) {
		System.out.println("\t\t\t/---|---|---\\");
		System.out.println("\t\t\t| " +board[0]+" | " +board[1]+" | "+board[2]+" |");
		System.out.println("\t\t\t|-----------|");
		System.out.println("\t\t\t| " +board[3]+" | " +board[4]+" | "+board[5]+" |");
		System.out.println("\t\t\t|-----------|");
		System.out.println("\t\t\t| " +board[6]+" | " +board[7]+" | "+board[8]+" |");
		System.out.println("\t\t\t|-----------|");
	}		
///////////////////////////////////////////////////////////////////////////////////////////

	public int[] legal_moves(String[] board,int NUM_SQUARES,String EMPTY) {
		int[] moves = new int [NUM_SQUARES];
		
		for (int i = 0;i<NUM_SQUARES; i++) {
			if (board[i] == EMPTY) {
				moves[i] = i;
			}
			else {
				moves[i] = -1;
				}
			}
			return moves;
			
	}
		
	public int human_move(String [] board,int NUM_SQUARES, String EMPTY) {
		int[] moves = legal_moves(board,NUM_SQUARES,EMPTY); 
		int move = -1;
		while (true){
			move =get_number("Where will you move? (0 - 8):", 0, NUM_SQUARES);
				for(int x:moves) {
					if (move == x){
						return move;
					}
				}
				System.out.println("That  square is already occupied, foolish human. Choose another.");
		}
	}
	public String Next_turn (String turn) { 
		if (turn == "X") {
		return "O";
		}
		return "X";
	}
	public String checkWinner(String[] board , String EMPTY, String TIE) {
		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
				
			}
			else if (line.equals("OOO")) {
				return "O";
			}
		
		}
		for (int a = 0; a < 9; a++) {
			if (board[a] == EMPTY) {
				break;
				
			}
			else if (a == 8 ) {
				return TIE;
			}
		}
		return "";
	}
		
		/////////////////////////////////////////////////////////////////////		
		
	public int comp_move(String[] board,String EMPTY,String TIE) {
	String[] test_board= new String [9];
	//make a copy to work with since function will be changing  array
	for (int i = 0; i<9;i++) {
		test_board[i] = board[i];
	}
	int[] best_moves = {0,1,2,3,4,5,6,7,8};
	int[] legalmoves = legal_moves(board,9,EMPTY);

	
	//checking if any of the legal moves the computer can win with 
	for (int move: legalmoves) {
		if( move != -1) {
			System.out.println("in if statement for checking comp move");
			test_board[move] = computer;
			String win = checkWinner(test_board,EMPTY,TIE);
			System.out.print(win);
			if(win.equals(computer)) {
				return move;
			}
		}
		else if (move == -1) {
			continue;
		}
	test_board[move] = EMPTY;
	}
	
	//checking if any of the legal moves the computer can win with
	for (int move: legalmoves) {
		if( move != -1) {
			System.out.println("in if statement for checking comp move");
			test_board[move] = human;
			String win = checkWinner(test_board,EMPTY,TIE);
			System.out.print(win);
			if(win.equals(human)) {
				return move;
			}
		}
		else if (move == -1) {
			continue;
		}
	test_board[move] = EMPTY;
	}
	
	//best move
	for (int move: best_moves) {
		boolean inarray = contains(legalmoves,move);
		if(inarray) {
			return move;
		}
	}
	return best_moves[0];

}

	public boolean contains (int[] array, int key) {
	for (int i: array) {
		if (i == key) {
			return true;
		}
	}
	return false;
}
			
		
	// build game
	public TicTacToeAi() {
		String X = "X";
		String O = "O";
		String EMPTY = " ";
		String TIE = "TIE";
		int NUM_SQUARES = 9;
		int move;
		String win = "";
		
		Intro(); // intro to game
		pieces(X,O); //
		String [] board = new_board(NUM_SQUARES,EMPTY);


		String turn = "X";
		display_board(board);
		while(win == "") {
			
			if (turn == human) {
				move = human_move(board,NUM_SQUARES,EMPTY);
				board[move] = human;
			}
			else {
				move = comp_move(board, EMPTY, TIE);
				board[move] = computer;
			}
			
			display_board(board);
			win = checkWinner(board,EMPTY,TIE);
			System.out.print(win);
			turn = Next_turn(turn);
	
	
	
	}
	

}
	public static void main (String[] args) {
		new TicTacToeAi();
	}
}
