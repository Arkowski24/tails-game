package pl.edu.agh.torbjorns.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Board {
    private final List<CardStack> finishedCardStacks;
    private final List<CardStack> workingCardStacks;
    private final BufferZone bufferZone;
}
