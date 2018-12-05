package edu.smith.cs.csc212.p10;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;
import me.jjfoley.gfx.TextBox;

public class TsoroGame extends GFX {

	public TsoroGame() {
		this.setupGame();
	}

	TState state = TState.Player1Turn;
	List<List<TicTacToeCell>> grid = new ArrayList<>();
	TextBox message = new TextBox("Hello World!");

	public List<TicTacToeCell> getAllCells() {
		List<TicTacToeCell> flatList = new ArrayList<>();
		for (List<TicTacToeCell> row : grid) {
			flatList.addAll(row);
		}
		return flatList;
	}

	public boolean allMarked(List<TicTacToeCell> row, TTTMark marker) {
		for (TicTacToeCell cell : row) {
			if (cell.symbol == marker) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean player1Wins() {
		List<TicTacToeCell> midRow = Arrays.asList(this.grid.get(1).get(1), this.grid.get(2).get(2),
				this.grid.get(3).get(3));
		if (allMarked(midRow, TTTMark.Player1)) {
			return true;
		}
		List<TicTacToeCell> botRow = Arrays.asList(this.grid.get(4).get(4), this.grid.get(5).get(5),
				this.grid.get(6).get(6));
		if (allMarked(botRow, TTTMark.Player1)) {
			return true;
		}

		List<TicTacToeCell> leftRightD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(1).get(1),
				this.grid.get(4).get(4));
		if (allMarked(leftRightD, TTTMark.Player1)) {
			return true;
		}
		List<TicTacToeCell> rightLeftD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(3).get(3),
				this.grid.get(6).get(6));
		if (allMarked(rightLeftD, TTTMark.Player1)) {
			return true;
		}
		List<TicTacToeCell> verticalD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(2).get(2),
				this.grid.get(5).get(5));
		if (allMarked(rightLeftD, TTTMark.Player1)) {
			return true;
		}

		return false;
	}

	public boolean player2Wins() {
		List<TicTacToeCell> midRow = Arrays.asList(this.grid.get(1).get(1), this.grid.get(2).get(2),
				this.grid.get(3).get(3));
		if (allMarked(midRow, TTTMark.Player2)) {
			return true;
		}
		List<TicTacToeCell> botRow = Arrays.asList(this.grid.get(4).get(4), this.grid.get(5).get(5),
				this.grid.get(6).get(6));
		if (allMarked(botRow, TTTMark.Player2)) {
			return true;
		}

		List<TicTacToeCell> leftRightD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(1).get(1),
				this.grid.get(4).get(4));
		if (allMarked(leftRightD, TTTMark.Player2)) {
			return true;
		}
		List<TicTacToeCell> rightLeftD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(3).get(3),
				this.grid.get(6).get(6));
		if (allMarked(rightLeftD, TTTMark.Player2)) {
			return true;
		}
		List<TicTacToeCell> verticalD = Arrays.asList(this.grid.get(0).get(0), this.grid.get(2).get(2),
				this.grid.get(5).get(5));
		if (allMarked(rightLeftD, TTTMark.Player2)) {
			return true;
		}

		return false;
	}

	public boolean boardIsFull() {
		for (TicTacToeCell cell : this.getAllCells()) {
			if (cell.inPlay()) {
				return false;
			}
		}
		return true;
	}

	static class TicTacToeCell {
		Rectangle2D area;
		TTTMark symbol;
		boolean mouseHover;
		TextBox display;

		public TicTacToeCell(int x, int y, int w, int h) {
			this.area = new Rectangle2D.Double(x, y, w, h);
			this.mouseHover = false;
			this.symbol = TTTMark.Empty;
			this.display = new TextBox("X");
		}

		public boolean inPlay() {
			return symbol == TTTMark.Empty;
		}

		public void draw(Graphics2D g) {

			switch (this.symbol) {
			case Empty:
				this.display.setString("_");
				break;
			case Player1:
				this.display.setString("X");
				break;
			case Player2:
				this.display.setString("O");
				break;
			default:
				break;

			}

			if (this.symbol == TTTMark.Empty && mouseHover) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.yellow);
			}
			g.fill(this.area);

			this.display.centerInside(this.area);
			this.display.setFontSize(70.0);
			this.display.setColor(Color.black);
			this.display.draw(g);
		}

		public boolean contains(IntPoint mouse) {
			if (mouse == null) {
				return false;
			}
			return this.area.contains(mouse);
		}
	}

	public void setupGame() {
		int size = this.getWidth() / 11;
//		for (int x = 0; x < 8; x++) {
			List<TicTacToeCell> row = new ArrayList<>();
			row.add(new TicTacToeCell(5 * size, 1 * size, size - 2, size - 2));
			this.grid.add(row);

			row.add(new TicTacToeCell(3 * size, 3 * size, size - 2, size - 2));
			this.grid.add(row);
			row.add(new TicTacToeCell(5 * size, 3 * size, size - 2, size - 2));
			this.grid.add(row);
			row.add(new TicTacToeCell(7 * size, 3 * size, size - 2, size - 2));
			this.grid.add(row);
			row.add(new TicTacToeCell(1 * size, 5 * size, size - 2, size - 2));
			this.grid.add(row);
			row.add(new TicTacToeCell(5 * size, 5 * size, size - 2, size - 2));
			this.grid.add(row);
			row.add(new TicTacToeCell(9 * size, 5 * size, size - 2, size - 2));

			this.grid.add(row);
//		}
	}

	@Override
	public void update(double time) {
		IntPoint mouse = this.getMouseLocation();
		IntPoint click = this.processClick();

		boolean stateChanged = false;
		if (this.state.isPlaying()) {
			for (TicTacToeCell cell : this.getAllCells()) {
				cell.mouseHover = cell.contains(mouse);

				if (cell.inPlay() && cell.contains(click)) {
					// More intelligence needed:
					if (this.state == TState.Player1Turn) {
						cell.symbol = TTTMark.Player1;
						this.state = TState.Player2Turn;
						stateChanged = true;
					} else {
						cell.symbol = TTTMark.Player2;
						this.state = TState.Player1Turn;
						stateChanged = true;
					}
				}
			}
		} else {
			if (click != null) {
				this.state = TState.Player1Turn;
				this.grid.clear();
				this.setupGame();
			}
		}

		if (stateChanged) {
			if (this.player1Wins()) {
				this.state = TState.Player1Win;
			} else if (this.player2Wins()) {
				this.state = TState.Player2Win;
			} else if (this.boardIsFull()) {
				this.state = TState.Tie;
			}
		}

		// Set message based on state!
		switch (this.state) {
		case Player1Turn:
			this.message.setString("Player 1 Turn");
			break;
		case Player2Turn:
			this.message.setString("Player 2 Turn");
			break;
		case Player1Win:
			this.message.setString("Player 1 Has Won");
			break;
		case Player2Win:
			this.message.setString("Player 2 Has Won");

			break;
		case Tie:
			this.message.setString("It's a tie!");
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (TicTacToeCell cell : this.getAllCells()) {
			cell.draw(g);
		}

		g.setColor(Color.black);

		Rectangle2D centerText = new Rectangle2D.Double(0, this.getHeight() * 3 / 4, this.getWidth(),
				this.getHeight() / 4);
		this.message.setFontSize(40.0);
		this.message.setColor(Color.black);
		this.message.centerInside(centerText);
		this.message.draw(g);
	}

	public static void main(String[] args) {
		TsoroGame app = new TsoroGame();
		app.start();
	}

}
