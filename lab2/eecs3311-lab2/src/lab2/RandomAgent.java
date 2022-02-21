package lab2;

import java.util.List;

public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }
    
    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() { // TODO
    	
    	List<Cell> cells = board.getPossibleCells();
    	
    	//Getting a random cell from the list
    	Cell fromCell = cells.get((int)Math.floor(Math.random()*(cells.size())));
    	
    	List<Cell> dests = board.getPossibleDestinations(fromCell);
    	
    	Cell destCell = dests.get((int)Math.floor(Math.random()*(dests.size())));
    	
    	System.out.printf("[%s (%s)] Moving piece %s to %s.", board.getTurn().getType(), this.getClass().getSimpleName(), fromCell.getCoordinate().toString(), destCell.getCoordinate().toString());
    	
    	return new Move(fromCell, destCell);
    }
}