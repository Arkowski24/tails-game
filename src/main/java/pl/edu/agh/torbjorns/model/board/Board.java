package pl.edu.agh.torbjorns.model.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Board {
    private final List<FinishedCardStack> finishedCardStacks;
    private final List<CardStack> workingCardStacks;
    private final BufferZone bufferZone;
}
