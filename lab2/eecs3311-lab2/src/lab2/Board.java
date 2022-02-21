package lab2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.loadBoard("Boards/Starter.txt");
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) {
    	// TODO
        return board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() {
    	// TODO
    	List<Cell> output = new ArrayList<Cell>();
    	//going through all the rows and columns
    	for(int r = 0; r < size; r++) {
    		for(int c = 0; c < size; c++) {
    			if(board[r][c].getPiece() != null && 
    					board[r][c].getPiece().getType() == Piece.Type.MUSKETEER) 
    			{output.add(board[r][c]);}
    		}
    	}
        return output;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() {
    	// TODO
    	List<Cell> output = new ArrayList<Cell>();
    	for(int r = 0; r < size; r++) {
    		for(int c = 0; c < size; c++) {
    			if(board[r][c].getPiece() != null && 
    					board[r][c].getPiece().getType() == Piece.Type.GUARD) 
    			{output.add(board[r][c]);}
    		}
    	}
        return output;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) {
    	// TODO
    	Cell from = board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col];
    	Cell to = board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col];
    	from.setPiece(null);
    	to.setPiece((turn == Piece.Type.MUSKETEER) ? new Musketeer() : new Guard());
    	
    	//changing the turn after moving the piece
    	turn = (turn == Piece.Type.MUSKETEER) ? Piece.Type.GUARD : Piece.Type.MUSKETEER;
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) {
    	// TODO
    	board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col] = move.toCell;
    	board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col] = move.fromCell;
    	
    	//changing the turn after moving the piece
    	turn = (turn == Piece.Type.MUSKETEER) ? Piece.Type.GUARD : Piece.Type.MUSKETEER;
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) {
    	// TODO
    	int rowDifference = Math.abs(move.fromCell.getCoordinate().row - move.toCell.getCoordinate().row);
    	int colDifference = Math.abs(move.fromCell.getCoordinate().col - move.toCell.getCoordinate().col);
    	
    	Piece fromType = move.fromCell.getPiece();
    	Piece toType = move.toCell.getPiece();
    	
    	if((rowDifference == 0 && colDifference == 1) || (rowDifference == 1 && colDifference == 0)) {
    		
    		if(fromType.getType() == Piece.Type.MUSKETEER && toType.getType() == Piece.Type.GUARD)
    			return true;
    		if(fromType.getType() == Piece.Type.GUARD && toType == null)
    			return true;
    	}
    	
        return false;
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() {
    	// TODO
    	List<Cell> output = new ArrayList<Cell>(); 
    	
    	//going through all the cells for this turn
    	List<Cell> cells = (turn.name() == Piece.Type.MUSKETEER.name()) ? 
    			getMusketeerCells() : getGuardCells();
    	
    	for(int i = 0; i < cells.size(); i++) {
    		if(getPossibleDestinations(cells.get(i)).size() > 0) output.add(cells.get(i));
    	}
    	
        return output;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) {
    	// TODO
    	List<Cell> output = new ArrayList<Cell>(); 
    	Coordinate c = fromCell.getCoordinate();
    	
    	if(fromCell.getPiece() != null) {
    		if(fromCell.getPiece().getType() == Piece.Type.MUSKETEER) {
        		if(c.row > 0 && 
        				board[c.row-1][c.col].getPiece() != null &&
        				board[c.row-1][c.col].getPiece().getType() == Piece.Type.GUARD) 
        		{output.add(board[c.row-1][c.col]);}
        		if(c.row < 4 && 
        				board[c.row+1][c.col].getPiece() != null &&
        				board[c.row+1][c.col].getPiece().getType() == Piece.Type.GUARD) 
        		{output.add(board[c.row+1][c.col]);}
        		if(c.col > 0 && 
        				board[c.row][c.col-1].getPiece() != null &&
        				board[c.row][c.col-1].getPiece().getType() == Piece.Type.GUARD) 
        		{output.add(board[c.row][c.col-1]);}
        		if(c.col < 4 && 
        				board[c.row][c.col+1].getPiece() != null &&
        				board[c.row][c.col+1].getPiece().getType() == Piece.Type.GUARD) 
        		{output.add(board[c.row][c.col+1]);}
        	}
        	else if(fromCell.getPiece().getType() == Piece.Type.GUARD) {
        		if(c.row > 0 && board[c.row-1][c.col].getPiece() == null) {output.add(board[c.row-1][c.col]);}
        		if(c.row < 4 && board[c.row+1][c.col].getPiece() == null) {output.add(board[c.row+1][c.col]);}
        		if(c.col > 0 && board[c.row][c.col-1].getPiece() == null) {output.add(board[c.row][c.col-1]);}
        		if(c.col < 4 && board[c.row][c.col+1].getPiece() == null) {output.add(board[c.row][c.col+1]);}
        	}
    	}
    	
        return output;
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() {
    	// TODO
    	List<Move> output = new ArrayList<Move>(); 
    	
    	//going through all the cells for this turn
    	List<Cell> cells = (turn == Piece.Type.MUSKETEER) ? 
    			getMusketeerCells() : getGuardCells();
    	
    	for(int i = 0; i < cells.size(); i++) {
    		for (int j = 0; j < getPossibleDestinations(cells.get(i)).size(); j++) {
    			output.add(new Move(cells.get(i), getPossibleDestinations(cells.get(i)).get(j)));
    		}
    	}
    	
        return output;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() {
    	// TODO
    	
    		boolean sameRow = getMusketeerCells().get(0).getCoordinate().row == getMusketeerCells().get(1).getCoordinate().row &&
    				getMusketeerCells().get(1).getCoordinate().row == getMusketeerCells().get(2).getCoordinate().row;
    		
    		boolean sameCol = getMusketeerCells().get(0).getCoordinate().col == getMusketeerCells().get(1).getCoordinate().col &&
    				getMusketeerCells().get(1).getCoordinate().col == getMusketeerCells().get(2).getCoordinate().col;
    		
    		if(sameRow || sameCol) {
    			winner = Piece.Type.GUARD;
	    		return true;
    		}
    		
    		if(turn == Piece.Type.MUSKETEER && getPossibleMoves().size() == 0) {
    			winner = Piece.Type.MUSKETEER;
    			return true;
    		}
    	
        return false;
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
