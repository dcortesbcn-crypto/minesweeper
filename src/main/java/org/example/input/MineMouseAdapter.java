package org.example.input;

import org.example.domain.model.Minesweeper;
import org.example.domain.model.TilePosition;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static org.example.input.Icon.HEART;

class MineMouseAdapter extends MouseAdapter {

    private final Minesweeper minesweeper;

    private final Supplier<Boolean> revealMines;
    private final BiConsumer<TilePosition, Integer> revealTextOnTile;
    private final Supplier<Boolean> winGame;

    MineMouseAdapter(
            Minesweeper minesweeper,
            Supplier<Boolean> revealMines,
            BiConsumer<TilePosition, Integer> revealTextOnTile,
            Supplier<Boolean> winGame
    ) {
        this.minesweeper = minesweeper;
        this.revealMines = revealMines;
        this.revealTextOnTile = revealTextOnTile;
        this.winGame = winGame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MineTile mineTile = (MineTile) e.getSource();

        // left click
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (mineTile.getText().isEmpty()) {
                if (minesweeper.isMineInPosition(mineTile.position)) {
                    revealMines.get();
                } else if (!mineTile.getText().equals(HEART.icon)) {
                    var distance = minesweeper.distanceToNearestMine(mineTile.position);
                    revealTextOnTile.accept(mineTile.position, distance);
                }
            }
        }

        // right click
        if (e.getButton() == MouseEvent.BUTTON3 && minesweeper.canSetMoreFlags()) {
            if (!mineTile.getText().equals(HEART.icon)) {
                mineTile.setText(HEART.icon);
                minesweeper.setFlag(mineTile.position);
            } else {
                mineTile.setText("");
            }

        }

        if (minesweeper.isGameWined()) {
            winGame.get();
        }

    }

}
