
# Character Engine

A lightweight engine for managing D&D-style characters in Java.

---

## Features
- Create characters with basic stats (level, health, skills)
- Level up characters
- Add skills to characters
- Simple API for managing multiple characters

---

## Getting Started

### Clone the repository
```bash
git clone https://github.com/ReamFOX/character-engine.git
cd character-engine
```

### Build with Maven
```bash
mvn clean install
```

---

## Example Usage
```java
Character hero = new Character("Hero");
hero.addSkill("Fireball");
hero.levelUp();

CharacterManager manager = new CharacterManager();
manager.addCharacter(hero);

System.out.println(manager.getCharacter("Hero").getSkills());
```

---

## Running Tests
```bash
mvn test
```

---

## Project Structure
```
character-engine/
 ├── src/main/java/com/example/character/
 │    ├── Character.java
 │    └── CharacterManager.java
 ├── src/test/java/com/example/character/
 │    └── CharacterTest.java
 │    └── CharacterManagerTest.java
 ├── pom.xml
 ├── README.txt
 └── LICENSE
```

---

## Future Plans
- Inventory system for characters
- Save/load characters to a database
- Telegram bot integration
- More advanced stats and abilities

---

## License
This project is licensed under the MIT License - see the LICENSE file for details.
