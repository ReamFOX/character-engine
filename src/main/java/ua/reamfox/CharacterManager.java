package ua.reamfox;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages a collection of characters with thread-safe operations. Provides methods to add, retrieve, and remove
 * characters by name.
 */
public class CharacterManager {

  private final Map<String, Character> characters = new ConcurrentHashMap<>();

  /**
   * Adds a character to the manager if not already present.
   *
   * @param character the character to add (must not be null)
   * @return the previous character associated with the name, or null if there was no mapping for the name
   * @throws IllegalArgumentException if character is null
   */
  public Character addCharacter(Character character) {
    return characters.putIfAbsent(character.getName(), character);
  }

  /**
   * Retrieves a character by name.
   *
   * @param name the name of the character to retrieve (case-sensitive)
   * @return the character with the specified name, or null if not found
   */
  public Character getCharacter(String name) {
    return characters.get(name);
  }

  /**
   * Removes a character by name.
   *
   * @param name the name of the character to remove (case-sensitive)
   * @return the character that was removed, or null if no character was found
   */
  public boolean removeCharacter(String name) {
    return characters.remove(name) != null;
  }

  /**
   * Checks if a character with the given name exists.
   *
   * @param name the name to check (case-sensitive)
   * @return true if a character with the name exists, false otherwise
   */
  public boolean containsCharacter(String name) {
    return characters.containsKey(name);
  }

  /**
   * Returns an unmodifiable view of all characters.
   *
   * @return an unmodifiable map of character names to character instances
   */
  public Map<String, Character> getAllCharacters() {
    return Collections.unmodifiableMap(characters);
  }

  /**
   * Returns the number of characters in the manager.
   *
   * @return the number of characters
   */
  public int getCharacterCount() {
    return characters.size();
  }

  /**
   * Removes all characters from the manager.
   */
  public void clear() {
    characters.clear();
  }
}
