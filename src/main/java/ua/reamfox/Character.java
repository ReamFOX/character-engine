package ua.reamfox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * Represents a game character with attributes like name, level, health, and skills. This class assumes that all input
 * has been validated by the service layer.
 */
@Data
public class Character {

  public static final int MAX_LEVEL = 100;
  public static final int MAX_HEALTH = 200;

  private static final int INITIAL_HEALTH = 100;
  private static final int HEALTH_INCREASE_PER_LEVEL = 10;

  private final String name;
  private int level;
  private int health;
  private final Set<String> skills;

  /**
   * Creates a new character with the specified name and skills.
   *
   * @param name   the name of the character
   * @param skills initial list of skills
   */
  public Character(String name, Set<String> skills) {
    this.name = name;
    this.level = 1;
    this.health = INITIAL_HEALTH;
    this.skills = new HashSet<>(skills);
  }

  /**
   * Increases the character's level and updates their health.
   *
   * @return true if level up was successful, false if maximum level is reached
   */
  public boolean levelUp() {
    if (level >= MAX_LEVEL) {
      return false;
    }
    level++;
    health = Math.min(health + HEALTH_INCREASE_PER_LEVEL, MAX_HEALTH);
    return true;
  }

  /**
   * Adds a new skill to the character's skill set.
   *
   * @param skill the skill to add
   * @return true if the skill was added, false if it was already present
   */
  public boolean addSkill(String skill) {
    return skills.add(skill);
  }

  /**
   * Removes a skill from the character's skill set.
   *
   * @param skill the skill to remove (case-sensitive)
   * @return true if the skill was removed, false if it wasn't found
   */
  public boolean removeSkill(String skill) {
    return skills.remove(skill);
  }

  /**
   * Checks if the character has a specific skill.
   *
   * @param skill the skill to check for
   * @return true if the character has the skill, false otherwise
   */
  public boolean hasSkill(String skill) {
    return skills.contains(skill);
  }

  public List<String> getSkills() {
    return List.copyOf(skills);
  }
}
