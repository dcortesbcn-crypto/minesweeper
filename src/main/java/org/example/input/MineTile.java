package org.example.input;

import org.example.domain.model.TilePosition;

import javax.swing.*;

class MineTile extends JButton {
    TilePosition position;

    public MineTile(TilePosition tilePosition) {
        this.position = tilePosition;
    }

}
