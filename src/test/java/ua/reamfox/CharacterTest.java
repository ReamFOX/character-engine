package ua.reamfox;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterTest {

  private Character hero;

  @BeforeEach
  void setup() {
    hero = new Character("Hero", List.of());
  }

  @Test
  void testLevelUp() {
    hero.lvlUp();
    assertEquals(2, hero.getLvl());
    assertEquals(110, hero.getHealth());
  }

  @Test
  void testAddSkill() {
    String expectedSkill = "Fireball";
    hero.addSkill(expectedSkill);
    assertTrue(hero.getSkills().contains(expectedSkill));
  }
}
