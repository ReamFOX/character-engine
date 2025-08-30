package ua.reamfox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Skill {

  private String name;

  private int level;

  private int damage;

}
