package pl.edu.agh.torbjorns.model.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Color {
    RED(javafx.scene.paint.Color.RED),
    BLACK(javafx.scene.paint.Color.BLACK);

    private final javafx.scene.paint.Color fxColor;
}
