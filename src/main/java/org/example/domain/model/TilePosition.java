package org.example.domain.model;

public record TilePosition(int x, int y) {

    public int distance(TilePosition otherPosition) {
        return Math.abs(x - otherPosition.x) + Math.abs(y - otherPosition.y);
    }

}
