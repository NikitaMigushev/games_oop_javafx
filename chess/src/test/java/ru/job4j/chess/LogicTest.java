package ru.job4j.chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    void whenMoveSuccessful()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);
        logic.move(Cell.C1, Cell.G5);
        Cell resultPosition = logic.getFigures()[0].position();
        Cell expectedPosition = Cell.G5;
        Assertions.assertThat(resultPosition).isEqualTo(expectedPosition);
    }

    @Test
    void whenMoveThenOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        PawnBlack pawnBlack = new PawnBlack(Cell.E3);
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.G5);
        });
    }
}