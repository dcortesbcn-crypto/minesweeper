package org.example.input;

import org.example.domain.model.Minesweeper;
import org.example.domain.model.TilePosition;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static java.awt.BorderLayout.NORTH;
import static java.awt.Color.red;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static java.util.stream.IntStream.range;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static org.example.input.Icon.HUNGRY;

public class MinesweeperUI {

    private final String NAME = "Minesweeper";
    int tileSize = 80;
    JFrame frame = new JFrame(NAME);
    JPanel boardPanel = new JPanel();
    MineTile[] board;

    private final Minesweeper minesweeper;

    public MinesweeperUI(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
        board = new MineTile[minesweeper.getNumberRows() * minesweeper.getNumberColumns()];

        configFrame();
        addTextPanel();
        addBoardPanel();
        setTilesToBoard();
        setTileInformation();
        showGame();
    }

    private void configFrame() {
        frame.setSize(minesweeper.getNumberColumns() * tileSize, minesweeper.getNumberRows() * tileSize);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void addTextPanel() {
        var textLabel = new JLabel();
        textLabel.setFont(new Font("Arial", BOLD, 25));
        textLabel.setHorizontalAlignment(CENTER);
        textLabel.setText(NAME);
        textLabel.setOpaque(true);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, NORTH);
    }

    private void addBoardPanel() {
        boardPanel.setLayout(new GridLayout(minesweeper.getNumberRows(), minesweeper.getNumberColumns()));
        boardPanel.setBackground(new Color(219, 72, 124));
        frame.add(boardPanel);
    }

    private void showGame() {
        frame.setVisible(true);
    }

    private void setTileInformation() {
        Arrays.stream(board).forEach(tile -> {
            tile.setFocusable(false);
            tile.setMargin(new Insets(0, 0, 0, 0));
            tile.setFont(new Font("Arial Unicode MS", PLAIN, 45));
            tile.addMouseListener(new MineMouseAdapter(minesweeper, funRevealMines, funRevealText, winGame));
        });
    }

    private final Supplier<Boolean> funRevealMines = () -> {
        revealMines();
        return true;
    };

    private final Supplier<Boolean> winGame = () -> {
        boardPanel.setBackground(new Color(72, 219, 120));
        return true;
    };

    private final BiConsumer<TilePosition, Integer> funRevealText = (position, i) -> {
        board[getBoardPosition(position)].setText(String.valueOf(i));
    };

    private void setTilesToBoard() {
        range(0, minesweeper.getNumberRows()).forEach(row ->
                range(0, minesweeper.getNumberColumns()).forEach(column -> {
                    var tile = new MineTile(new TilePosition(row, column));
                    board[getBoardPosition(new TilePosition(row, column))] = tile;
                    boardPanel.add(tile);
                })
        );
    }

    private void revealMines() {
        minesweeper.getMinePositions()
                .stream()
                .filter(tilePosition -> !minesweeper.isFlagInPosition(tilePosition))
                .map(this::getBoardPosition)
                .forEach(position -> board[position].setText(HUNGRY.icon));
    }

    private int getBoardPosition(TilePosition position) {
        return position.x() * minesweeper.getNumberColumns() + position.y();
    }

}
