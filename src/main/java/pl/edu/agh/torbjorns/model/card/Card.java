package pl.edu.agh.torbjorns.model.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.model.board.CardHolder;

@Getter
@RequiredArgsConstructor
public class Card {
    private final Rank rank;
    private final Suit suit;
    @Setter private @Nullable CardHolder holder = null;

    public Color getColor() {
        return suit.getColor();
    }
}
