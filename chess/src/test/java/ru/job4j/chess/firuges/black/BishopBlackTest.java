package ru.job4j.chess.firuges.black;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.junit.Assert.assertThrows;

class BishopBlackTest {

    @Test
    void whenCreateObjectThenSamePosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell position = bishopBlack.position();
        Cell expected = Cell.C8;
        Assertions.assertThat(position).isEqualTo(expected);
    }

    @Test
    void whenCopyThenCorrectPisition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell destination = Cell.D7;
        Figure bishopCopy = bishopBlack.copy(destination);
        Assertions.assertThat(bishopCopy.position()).isEqualTo(destination);
    }

    @Test
    void wayC1G5ThenSuccess() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] way = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Assertions.assertThat(way).isEqualTo(expected);
    }

    @Test
    void whenWayC8D7ThenSuccess() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] way = bishopBlack.way(Cell.D7);
        Cell[] expected = {Cell.D7};
        Assertions.assertThat(way).isEqualTo(expected);
    }

    @Test
    void isDiagonalTrue() {
        Cell source = Cell.C1;
        Cell dest = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean result = bishopBlack.isDiagonal(source, dest);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void whenIsDiagonalD4E6ThenFalse() {
        Cell source = Cell.D4;
        Cell dest = Cell.E6;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean result = bishopBlack.isDiagonal(source, dest);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void whenIsDiagonalD4C2ThenFalse() {
        Cell source = Cell.D4;
        Cell dest = Cell.C2;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean result = bishopBlack.isDiagonal(source, dest);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void whenIsDiagonalC8D7ThenTrue() {
        Cell source = Cell.C8;
        Cell dest = Cell.D7;
        BishopBlack bishopBlack = new BishopBlack(source);
        boolean result = bishopBlack.isDiagonal(source, dest);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void whenIsDiagonalException() {
        Cell source = Cell.D4;
        Cell dest = Cell.C2;
        BishopBlack bishopBlack = new BishopBlack(source);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(dest);
                }
        );
        Assertions.assertThat(exception.getMessage()).isEqualTo(
                "Could not move by diagonal from %s to %s", source, dest
        );
    }
}