package org.example.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTest {

    @Nested
    class InitializeWithRandomMinesShould {

        @Test
        void throwError_whenNumberOfRowsLessThanOne() {
            assertThrows(IllegalStateException.class ,() -> new Minesweeper(0, 10, 0, 0).getMinePositions().isEmpty());
        }

        @Test
        void throwError_whenNumberOfColumnsLessThanOne() {
            assertThrows(IllegalStateException.class ,() -> new Minesweeper(10, 0, 0, 0).getMinePositions().isEmpty());
        }

        @Test
        void throwError_whenNumberOfFlagsLessThanMines() {
            assertThrows(IllegalStateException.class ,() -> new Minesweeper(10, 10, 1, 0).getMinePositions().isEmpty());
        }


        @Test
        void hasNoMinesSet_whenNumberOfMinesZero() {
            assertTrue(new Minesweeper(10, 10, 0, 0).getMinePositions().isEmpty());
        }

        @Test
        void returnMinesInValidPositions_whenNumberOfMinesBiggerThanZero() {
            var mines = new Minesweeper(2, 2, 2, 2).getMinePositions();

            assertEquals(2, mines.size());
            mines.forEach(mine -> {
                assertTrue( 0 <= mine.x() && mine.x() <= 2);
                assertTrue( 0 <= mine.y() && mine.y() <= 2);
            });
        }

    }

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

            var mineswepper = new Minesweeper(10, 10, 5, minePositions);

            assertTrue(mineswepper.isMineInPosition(tilePosition));
        }

    }

    @Nested
    class IsFlagInPositionShould {

        @Test
        void returnFalse_whenNoFlag() {
            var tilePosition = new TilePosition(0, 0);
            var mineswepper = new Minesweeper(10, 10, 10, new ArrayList<>());

            mineswepper.setFlag(tilePosition);

            assertTrue(mineswepper.isFlagInPosition(tilePosition));
        }

        @Test
        void returnTrue_whenFlag() {
            var tilePosition = new TilePosition(0, 0);
            var mineswepper = new Minesweeper(10, 10, 10, new ArrayList<>());

            assertFalse(mineswepper.isFlagInPosition(tilePosition));
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

            var mineswepper = new Minesweeper(10, 10, 5, minePositions);

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

    @Nested
    class isGameWinedShould {

        @Test
        void returnFalse_whenStillNotAllMinesFlagged() {
            var tilePosition = new TilePosition(2, 1);
            var minePositions = new ArrayList<TilePosition>();
            minePositions.add(tilePosition);
            var mineswepper = new Minesweeper(10, 10, 5, minePositions);

            assertFalse(mineswepper.isGameWined());
        }

        @Test
        void returnTrue_whenStillFlags() {
            var tilePosition = new TilePosition(2, 1);
            var minePositions = new ArrayList<TilePosition>();
            minePositions.add(tilePosition);
            var mineswepper = new Minesweeper(10, 10, 5, minePositions);

            mineswepper.setFlag(tilePosition);

            assertTrue(mineswepper.isGameWined());
        }

    }


}