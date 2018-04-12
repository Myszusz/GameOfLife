import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Game {
	public static void main(String[] args){
		Game g = new Game();
		
	}
	public int stepNumber = 0;
	public Vector<Vector<Character>> gameBoard = new Vector<Vector<Character>>();
	int numberOfRows = 30;
	int numberOfColumns = 60;
	int numberOfCells = numberOfRows * numberOfColumns;
	ArrayList<Integer> aliveRules = new ArrayList<Integer>();
	ArrayList<Integer> bornRules = new ArrayList<Integer>();
	ArrayList<Integer> bothRules = new ArrayList<Integer>();
	private DefaultTableModel dtm;
	private Vector<String> columnNames;
	
	public Game(){
		
		iniBoard();
		
		setRules(getFieldValue("23"), getFieldValue("3"));
	}
	
	public void iniBoard(){
		Vector<Vector<Character>> nextBoard = new Vector<Vector<Character>>();
		
		
		for(int r = 0; r < numberOfRows; r++){
			
			Vector<Character> nextRow = new Vector<Character>();
			
			
			for(int c = 0; c < numberOfColumns; c++){
				
					nextRow.add(' ');
				
			}
			
			nextBoard.add(nextRow);
		}
		gameBoard = nextBoard;
		setColumns();
	}
	
	public void randomize(double percent){
		
		// double aaa = numberOfCells;
		//Double percentOfCells = aaa * percent;
		//int counter = percentOfCells.intValue();
		//int countAdded = 0;
		
		Double random = 0.0;
		
		Vector<Vector<Character>> randomBoard = new Vector<Vector<Character>>();
		
		
			for(int r = 0; r < numberOfRows; r++){
				
				Vector<Character> nextRow = new Vector<Character>();
				
					for(int c = 0; c < numberOfColumns; c++){
						
						random = Math.random()*100;
						
						if(random <= percent*100){
							nextRow.add('♾');
							//countAdded++;
						}
						else{
							nextRow.add(' ');
						}
					}	
				
				randomBoard.add(nextRow);
			}
		
		
		gameBoard = randomBoard;
		
		
	}
	
	public DefaultTableModel getModel(){
		
		
		dtm = new DefaultTableModel(gameBoard,columnNames);
		return dtm;
	}

	public void goNextStep() {
		stepNumber++;
		
		setBoard();
		setColumns();
	}

	public void setBoard() {
		Vector<Vector<Character>> nextBoard = new Vector<Vector<Character>>();
		
		
		for(int r = 0; r < numberOfRows; r++){
			
			Vector<Character> nextRow = new Vector<Character>();
			
			
			for(int c = 0; c < numberOfColumns; c++){
				if(isNextCellAlive(r, c)){
					nextRow.add('♾');
				}
				else{
					nextRow.add(' ');
				}
			}
			
			nextBoard.add(nextRow);
		}
		gameBoard = nextBoard;
	}

	public void setSize(){
		
	}
	
	public void setColumns() {
		columnNames = new Vector<String>();
		for(int n = 0; n < numberOfColumns; n++){
			columnNames.add(Integer.toString(n));
		}
	}

	public boolean isNextCellAlive(int r, int c) {
		
		if(rules_Eqals_countAlive(r,c,aliveRules) /*only alive*/ && isCurrentCellAlive(r,c)){
			return true;
		}
		else if(rules_Eqals_countAlive(r,c,bothRules)/*born and alive*/){
			return true;
		}
		else if(rules_Eqals_countAlive(r,c,bornRules)/*only born*/ && !isCurrentCellAlive(r,c)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean rules_Eqals_countAlive(int r, int c, ArrayList<Integer> rules){
		int countAlive = countAlive(r,c);
		for(int a : rules){
				if(countAlive == a){
				return true;
			}
		}		
		return false;
	}
	
	public void setRules(ArrayList<Integer> alive, ArrayList<Integer> born){
		ArrayList<Integer> both = new ArrayList<Integer>();
		for(int x : alive){
				if(born.contains(x)){
					both.add(x);
				}
		}
		
		alive.removeAll(both);
		born.removeAll(both);
			
		aliveRules = alive;
		bornRules = born;
		bothRules = both;
	}
	
	public ArrayList<Integer> getFieldValue(String field){
		char[] chField = field.toCharArray();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(char e : chField){
			list.add(Integer.parseInt(String.valueOf(e)));
		}
		return list;
	}
	
	private boolean isCurrentCellAlive(int r, int c) {
		if(r < 0 || c < 0 || r >= numberOfRows || c >= numberOfColumns){
			return false;
		}
		else{
			Vector<Character> currentRow = gameBoard.elementAt(r);
			char currentCell = currentRow.elementAt(c);
			
			if(currentCell == ' '){
				return false;
			}
			else{
				return true;
			}
			
		}
		
		
	}

	public int countAlive(int r, int c){
		
		int count = 0;
		
		for(int x = r-1; x <= r+1; x++){
			for(int y = c-1; y <= c+1; y++){
				if(x == r && y == c){
					continue;
				}
				else if(isCurrentCellAlive(x,y)){
					count++;
				}
			}
		}
		
		return count;
	}
	
	public void ChangeSelectedCell(int rowIndex, int colIndex) {
		if(rowIndex < 0 || colIndex < 0) {
            return;
        }
        Vector<Character> row = gameBoard.elementAt(rowIndex);
        char c = row.elementAt(colIndex);
        System.out.println(rowIndex + "," + colIndex + " = " + c);

        if(c == ' '){
            row.set(colIndex, '♾');
        }
        else {
            row.set(colIndex, ' ');
        }
	}
	
}
