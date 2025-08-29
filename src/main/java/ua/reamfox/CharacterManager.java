package ua.reamfox;

import java.util.HashMap;
import java.util.Map;

public class CharacterManager {

  private final Map<String, Character> characters = new HashMap<>();

  public void addCharacter(Character character) {
    characters.put(character.getName(), character);
  }

  public Character getCharacter(String name) {
    return characters.get(name);
  }

  public boolean removeCharacter(String name) {
    return characters.remove(name) != null;
  }
}
