package org.example.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static java.util.Comparator.comparingInt;

public class Minesweeper {

    private final int numRows;
    private final int numColumns;
    private final int maxNumberFlags;
    private int usedFlags = 0;
    private final HashSet<TilePosition> flagPositions = new HashSet<>();
    private final HashSet<TilePosition> minePositions;

    public Minesweeper(int numRows, int numColumns, int maxNumberFlags, ArrayList<TilePosition> minePositions){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.maxNumberFlags = maxNumberFlags;
        this.minePositions = new HashSet<>(minePositions);

    }

    public int getNumberRows(){
        return numRows;
    }

    public int getNumberColumns(){
        return numColumns;
    }

    public HashSet<TilePosition> getMinePositions(){
        return minePositions;
    }

    public Minesweeper(int numRows, int numColumns, int numberMines, int maxNumberFlags) {
        this(numRows, numColumns, maxNumberFlags, setMines(numRows, numColumns, numberMines));
    }

    private static ArrayList<TilePosition> setMines(int numRows, int numColumns, int numberMines) {
        var mines = new HashSet<TilePosition>();
        var random = new Random();
        while (mines.size() < numberMines) {
            mines.add(new TilePosition(random.nextInt(numRows), random.nextInt(numColumns)));
        }
        System.out.println(mines);
        return new ArrayList<>(mines);
    }

    public boolean isMineInPosition(TilePosition tilePosition) {
        return minePositions.contains(tilePosition);
    }

    public boolean isFlagInPosition(TilePosition tilePosition) {
        return flagPositions.contains(tilePosition);
    }

    public int distanceToNearestMine(TilePosition tilePosition) {
        return minePositions.stream()
                .min(comparingInt(tilePosition::distance))
                .map(tilePosition::distance)
                .orElse(Integer.MAX_VALUE);
    }

    public boolean canSetMoreFlags() {
        return usedFlags < maxNumberFlags;
    }

    public void setFlag(TilePosition position) {
        usedFlags = usedFlags + 1;
        flagPositions.add(position);
    }

    public boolean isGameWined() {

        return !flagPositions.isEmpty() && flagPositions.containsAll(minePositions);
    }
}
