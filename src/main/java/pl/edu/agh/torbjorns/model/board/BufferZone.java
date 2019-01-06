package pl.edu.agh.torbjorns.model.board;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BufferZone {

    public final static int SIZE = 8;

    private final List<BufferPlace> places;

    public BufferPlace getPlace(int place) {
        return places.get(place);
    }

}
