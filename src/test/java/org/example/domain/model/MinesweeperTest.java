package org.example.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTest {

    @Nested
    class IsMineInPositionShould {

        @Test
        void returnFalse_whenNoMine() {
            var tilePosition = new TilePosition(0, 0);
            var mineswepper = new Minesweeper(10, 10, 0, new ArrayList<>());

            assertFalse(mineswepper.isMineInPosition(tilePosition));
        }

        @Test
        void returnTrue_whenMine() {
            var tilePosition = new TilePosition(2, 1);
            var minePositions = new ArrayList<TilePosition>();
            minePositions.add(tilePosition);

            var mineswepper = new Minesweeper(10, 10, 0, minePositions);

            assertTrue(mineswepper.isMineInPosition(tilePosition));
        }

    }


    @Nested
    class DistanceToNearestMineShould {

        @Test
        void returnZero_whenNoMines() {
            var tilePosition = new TilePosition(0, 0);
            var mineswepper = new Minesweeper(10, 10, 0, new ArrayList<>());

            assertEquals(Integer.MAX_VALUE, mineswepper.distanceToNearestMine(tilePosition));
        }

        @Test
        void returnDistanceToNearest_whenMultipleMines() {
            var tilePosition = new TilePosition(2, 1);
            var minePositions = new ArrayList<TilePosition>();
            minePositions.add(new TilePosition(1, 1));
            minePositions.add(new TilePosition(1, 0));

            var mineswepper = new Minesweeper(10, 10, 0, minePositions);

            assertEquals(1, mineswepper.distanceToNearestMine(tilePosition));
        }

    }


    @Nested
    class canSetMoreFlagsShould {

        @Test
        void returnFalse_whenNoMoreFlags() {
            var mineswepper = new Minesweeper(10, 10, 0, new ArrayList<>());

            assertFalse(mineswepper.canSetMoreFlags());
        }

        @Test
        void returnTrue_whenStillFlags() {
            var mineswepper = new Minesweeper(10, 10, 10, new ArrayList<>());

            assertTrue(mineswepper.canSetMoreFlags());
        }

    }


}