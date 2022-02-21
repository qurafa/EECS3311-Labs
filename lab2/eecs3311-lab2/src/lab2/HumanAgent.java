package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanAgent extends Agent {

    public HumanAgent(Board board) {
        super(board);
    }

    private final Scanner scanner = new Scanner(System.in);
    
    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { 
    	// TODO
    	List<Cell> cells = board.getPossibleCells();
    	List<String> cellsCoord = getCoordinates(cells);
    	
    	System.out.printf("[%s] Possible pieces are %s. Enter the piece you want to move: ", board.getTurn().getType(), cellsCoord.toString());
    	
    	String input = "";
    	
    	//Getting the cell you want to move
    	while (true) {
    		if(scanner.hasNext()) {
    			input = scanner.next();
    			if(cellsCoord.contains(input.toUpperCase()))
    				break;
    			else
    				System.out.println(input + " is an invalid piece");
    			
    			System.out.printf("[%s] Possible pieces are %s. Enter the piece you want to move: ", board.getTurn().getType(), cellsCoord.toString());
    		}
    	}
    	
    	Cell fromCell = cells.get(cellsCoord.indexOf(input.toUpperCase()));
    	
    	List<Cell> dests = board.getPossibleDestinations(fromCell);
    	List<String> destCoord = getCoordinates(dests);
    	
    	System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), destCoord.toString());
    	
    	//Getting the destination cell input
    	while (true) {
    		if(scanner.hasNext()) {
    			input = scanner.next();
    			if(destCoord.contains(input.toUpperCase()))
    				break;
    			else
    				System.out.println(input + " is an invalid destination");
    			
    	    	System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), destCoord.toString());	
    		}
    	}
    	
    	Cell destCell = dests.get(destCoord.indexOf(input.toUpperCase()));
    	
    	return new Move(fromCell, destCell);
    }
    
    private List<String> getCoordinates(List<Cell> c) {
    	
    	//System.out.println(c.size());
    	List<String> output = new ArrayList<String>();
    	for(int i = 0; i < c.size(); i++) 
    	{
    		output.add(c.get(i).getCoordinate().toString().toUpperCase());
    	}
    	
    	return output;
    }
}
