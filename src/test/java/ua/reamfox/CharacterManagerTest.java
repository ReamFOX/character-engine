package ua.reamfox;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterManagerTest {

  private CharacterManager manager;
  private Character hero;
  private Character mage;
  private Skill swordMastery;
  private Skill fireball;
  private Skill teleport;

  @BeforeEach
  void setUp() {
    swordMastery = new Skill("Sword Mastery", 1, 10);
    fireball = new Skill("Fireball", 1, 10);
    teleport = new Skill("Teleport", 1, 0);
    manager = new CharacterManager();
    hero = new Character("Hero", Set.of(swordMastery));
    mage = new Character("Mage", Set.of(fireball, teleport));
  }

  @Test
  void addCharacter_shouldAddNewCharacter() {
    assertNull(manager.addCharacter(hero));
    assertEquals(hero, manager.getCharacter("Hero"));
  }

  @Test
  void addCharacter_shouldReturnExistingCharacterWhenDuplicate() {
    manager.addCharacter(hero);
    Character duplicateHero = new Character("Hero", Set.of(new Skill("Shield bash", 1, 10)));

    Character result = manager.addCharacter(duplicateHero);

    assertEquals(hero, result);
    assertEquals(1, manager.getCharacterCount());
  }

  @Test
  void addCharacter_shouldNotAcceptNullCharacter() {
    assertThrows(NullPointerException.class, () -> manager.addCharacter(null));
  }

  @Test
  void getCharacter_shouldReturnNullForNonExistentCharacter() {
    assertNull(manager.getCharacter("NonExistent"));
  }

  @Test
  void removeCharacter_shouldReturnTrueWhenCharacterExists() {
    manager.addCharacter(hero);
    assertTrue(manager.removeCharacter("Hero"));
    assertNull(manager.getCharacter("Hero"));
  }

  @Test
  void removeCharacter_shouldReturnFalseWhenCharacterDoesNotExist() {
    assertFalse(manager.removeCharacter("NonExistent"));
  }

  @Test
  void containsCharacter_shouldReturnCorrectResult() {
    manager.addCharacter(hero);

    assertTrue(manager.containsCharacter("Hero"));
    assertFalse(manager.containsCharacter("NonExistent"));
  }

  @Test
  void getAllCharacters_shouldReturnUnmodifiableMap() {
    manager.addCharacter(hero);
    Map<String, Character> characters = manager.getAllCharacters();
    Character existingCharacter = hero; // Use an existing character that we know is valid

    assertThrows(
        UnsupportedOperationException.class,
        () -> characters.put("Test", existingCharacter)
    );
  }

  @Test
  void getCharacterCount_shouldReturnCorrectCount() {
    assertEquals(0, manager.getCharacterCount());

    manager.addCharacter(hero);
    assertEquals(1, manager.getCharacterCount());

    manager.addCharacter(mage);
    assertEquals(2, manager.getCharacterCount());
  }

  @Test
  void clear_shouldRemoveAllCharacters() {
    manager.addCharacter(hero);
    manager.addCharacter(mage);

    manager.clear();

    assertEquals(0, manager.getCharacterCount());
    assertNull(manager.getCharacter("Hero"));
    assertNull(manager.getCharacter("Mage"));
  }

  @Test
  void concurrentAccess_shouldBeThreadSafe() {
    int threadCount = 10;
    try (ExecutorService service = Executors.newFixedThreadPool(threadCount)) {
      CountDownLatch latch = new CountDownLatch(1);

      for (int i = 0; i < threadCount; i++) {
        final String name = "Character" + i;
        service.execute(() -> {
          try {
            latch.await();
            Skill skill = new Skill("Skill" + name, 1, 10);
            Character character = new Character(name, Set.of(skill));
            manager.addCharacter(character);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        });
      }

      latch.countDown();
    }
    assertEquals(threadCount, manager.getCharacterCount());
  }
}
