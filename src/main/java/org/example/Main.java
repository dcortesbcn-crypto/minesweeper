package org.example;

import org.example.domain.model.Minesweeper;
import org.example.input.MinesweeperUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // TODO max Number Flags need to be equal or higher than numberMines
        new MinesweeperUI(new Minesweeper(8, 8, 5,14));
    }
}