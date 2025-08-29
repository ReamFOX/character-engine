package ua.reamfox;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterManagerTest {

  private CharacterManager manager;

  private Character hero;

  @BeforeEach
  void setup() {
    manager = new CharacterManager();
    hero = new Character("Hero", List.of());
  }

  @Test
  void testAddAndGetCharacter() {
    manager.addCharacter(hero);

    assertEquals(hero, manager.getCharacter("Hero"));
  }

  @Test
  void testRemoveCharacter() {
    manager.addCharacter(hero);

    assertTrue(manager.removeCharacter("Hero"));
    assertNull(manager.getCharacter("Hero"));
  }
}
