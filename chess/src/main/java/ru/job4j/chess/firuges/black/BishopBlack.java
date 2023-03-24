package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(String.format(
                    "Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int deltaX = dest.getX() - position().getX() > 0 ? 1 : -1;
        int deltaY = dest.getY() - position().getY() > 0 ? 1 : -1;
        int x = position().getX();
        int y = position().getY();
        int size = Math.abs(dest.getX() - position().getX());
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            x += deltaX;
            y += deltaY;
            steps[i] = Cell.findBy(x, y);
        }
        return steps;

    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(dest.getX() - source.getX()) == Math.abs(dest.getY() - source.getY());
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
