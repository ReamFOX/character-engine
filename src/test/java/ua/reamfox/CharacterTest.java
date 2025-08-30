package ua.reamfox;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.reamfox.Character.MAX_HEALTH;
import static ua.reamfox.Character.MAX_LEVEL;

class CharacterTest {

  private Character hero;
  private Skill existingSkill;
  private Skill nonExistingSkill;

  @BeforeEach
  void setup() {
    existingSkill = new Skill("Sword Mastery", 1, 10);
    nonExistingSkill = new Skill("NonExistentSkill", 1, 10);
    hero = new Character("Hero", Set.of(existingSkill));
  }

  @Test
  void testInitialState() {
    assertEquals("Hero", hero.getName());
    assertEquals(1, hero.getLevel());
    assertEquals(100, hero.getHealth());
    assertTrue(hero.getSkills().contains(existingSkill));
  }

  @Test
  void testLevelUp() {
    assertTrue(hero.levelUp());
    assertEquals(2, hero.getLevel());
    assertEquals(110, hero.getHealth());
  }

  @Test
  void testMultipleLevelUps() {
    hero.levelUp(); // Level 2
    hero.levelUp(); // Level 3
    assertEquals(3, hero.getLevel());
    assertEquals(120, hero.getHealth());
  }

  @Test
  void testAddSkill() {
    var newSkill = new Skill("Shield Block", 1, 0);
    assertTrue(hero.addSkill(newSkill));
    assertTrue(hero.getSkills().contains(newSkill));
  }

  @Test
  void testAddDuplicateSkill() {
    assertFalse(hero.addSkill(existingSkill));
    assertEquals(
        1, hero.getSkills().stream()
            .filter(s -> s.equals(existingSkill))
            .count()
    );
  }

  @Test
  void testRemoveSkill() {
    assertTrue(hero.removeSkill(existingSkill));
    assertFalse(hero.hasSkill(existingSkill));
  }

  @Test
  void testRemoveNonExistentSkill() {
    assertFalse(hero.removeSkill(nonExistingSkill));
  }

  @Test
  void testHasSkill() {
    assertTrue(hero.hasSkill(existingSkill));
    assertFalse(hero.hasSkill(nonExistingSkill));
  }

  @Test
  void testMaxLevel() {
    // Set level to max - 1
    for (int i = 1; i < MAX_LEVEL - 1; i++) {
      hero.levelUp();
    }

    // Should be able to reach max level
    assertTrue(hero.levelUp());
    assertEquals(MAX_LEVEL, hero.getLevel());

    // Should not be able to level up beyond max
    assertFalse(hero.levelUp());
    assertEquals(MAX_LEVEL, hero.getLevel());
  }

  @Test
  void testMaxHealth() {
    // Level up until health would exceed max
    for (int i = 0; i < 20; i++) {
      hero.levelUp();
    }

    // Health should be capped at max
    assertEquals(MAX_HEALTH, hero.getHealth());

    // Additional level up shouldn't increase health beyond max
    int currentHealth = hero.getHealth();
    hero.levelUp();
    assertEquals(currentHealth, hero.getHealth());
  }
}
