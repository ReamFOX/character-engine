package ua.reamfox;

import java.util.List;
import lombok.Data;

@Data
public class Character {

  private String name;

  private int lvl;

  private int health;

  private List<String> skills;

  public Character(String name, List<String> skills) {
    this.name = name;
    this.lvl = 1;
    this.health = 100;
    this.skills = skills;
  }

  public void lvlUp() {
    lvl++;
    health += 10;
  }

  public void addSkill(String skill) {
    skills.add(skill);
  }
}
