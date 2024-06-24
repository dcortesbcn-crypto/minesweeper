package org.example.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TilePositionTest {

    @Test
    void givenTwoTiles_shouldTakeDistance() {
        var tilePosition = new TilePosition(0, 0);

        assertEquals(3, new TilePosition(2, 1).distance(tilePosition));

    }

}