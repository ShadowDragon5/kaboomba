package com.factories.tile;

import com.entities.Position;
import com.entities.tiles.Tile;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TileCreatorTest {

    public static TileCreator tileCreator;

    @BeforeAll
    public static void setUp() {
        tileCreator = new DefaultTileCreator();
    }

    @Test
    @DisplayName("Tile creator returns null, with non existing gids")
    public void testReturnNullTileCreator() {
        Tile tile = tileCreator.createTile("100", new Position(), 0.1f);
        Assertions.assertNull(tile);
    }

    @DisplayName(value = "Tile creator successfully creates tiles with correct gids")
    @ParameterizedTest(name = "{index} => when gid is {0} tile is {1}")
    @MethodSource("provideTileMappings")
    void testWithCorrectGidsTileCreator(String gid, String result) {
        Tile tile = tileCreator.createTile(gid, new Position(), 0.1f);
        Assertions.assertNotNull(tile);
        Assertions.assertEquals(tile.getTextureFile(), result);
    }

    private static Stream<Arguments> provideTileMappings() {
        return Stream.of(
                Arguments.of("1", "src/main/resources/path.png"),
                Arguments.of("2", "src/main/resources/wall.png"),
                Arguments.of("3", "src/main/resources/box.png"),
                Arguments.of("4", "src/main/resources/portal_waypoint.png"),
                Arguments.of("5", "src/main/resources/portal_random.png")
        );
    }
}
