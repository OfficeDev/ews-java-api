package microsoft.exchange.webservices.data.xml;

import com.github.rwitzel.streamflyer.core.AfterModification;
import com.github.rwitzel.streamflyer.core.Modifier;
import com.github.rwitzel.streamflyer.util.ModificationFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class CharacterModifier implements Modifier {

  private final ModificationFactory factory;

  private final Map<Integer, Integer> mappings;
  private final Set<Integer> ignoreCharacters;


  public CharacterModifier(Map<Integer, Integer> mappings, Set<Integer> ignoreCharacters) {
    this(mappings, ignoreCharacters, 8192);
  }

  public CharacterModifier(Map<Integer, Integer> mappings, Set<Integer> ignoreCharacters,
                           int newNumberOrChars) {

    for (Integer cp : mappings.keySet()) {
      if (!Character.isBmpCodePoint(cp)) {
        throw new IllegalArgumentException("Only mappings for BMP code points are supported");
      }
    }

    for (Integer cp : ignoreCharacters) {
      if (!Character.isBmpCodePoint(cp)) {
        throw new IllegalArgumentException("Only mappings for BMP code points are supported");
      }
    }

    this.mappings = mappings;
    this.ignoreCharacters = ignoreCharacters;
    this.factory = new ModificationFactory(0, newNumberOrChars);
  }

  @Override
  public AfterModification modify(StringBuilder characterBuffer, int firstModifiableCharacterInBuffer,
                                  boolean endOfStreamHit) {

    for (int i = firstModifiableCharacterInBuffer; i < characterBuffer.length(); i++) {
      final int c = characterBuffer.codePointAt(i);

      if (mappings.containsKey(c)) {
        characterBuffer.replace(i, i + 1, new String(Character.toChars(mappings.get(c))));
      } else if (ignoreCharacters.contains(c)) {
        characterBuffer.deleteCharAt(i);
        i--;
      }

    }
    return factory.skipEntireBuffer(characterBuffer, firstModifiableCharacterInBuffer, endOfStreamHit);
  }

  public static final Map<Integer, Integer> CP1252_TO_UNICODE;

  static {
    final Map<Integer, Integer> cp1252ToUnicode = new HashMap<>();
    cp1252ToUnicode.put(128, 8364);
    cp1252ToUnicode.put(130, 8218);
    cp1252ToUnicode.put(131, 402);
    cp1252ToUnicode.put(132, 8222);
    cp1252ToUnicode.put(133, 8230);
    cp1252ToUnicode.put(134, 8224);
    cp1252ToUnicode.put(135, 8225);
    cp1252ToUnicode.put(136, 710);
    cp1252ToUnicode.put(137, 8240);
    cp1252ToUnicode.put(138, 352);
    cp1252ToUnicode.put(139, 8249);
    cp1252ToUnicode.put(140, 338);
    cp1252ToUnicode.put(142, 381);
    cp1252ToUnicode.put(145, 8216);
    cp1252ToUnicode.put(146, 8217);
    cp1252ToUnicode.put(147, 8220);
    cp1252ToUnicode.put(148, 8221);
    cp1252ToUnicode.put(149, 8226);
    cp1252ToUnicode.put(150, 8211);
    cp1252ToUnicode.put(151, 8212);
    cp1252ToUnicode.put(152, 732);
    cp1252ToUnicode.put(153, 8482);
    cp1252ToUnicode.put(154, 353);
    cp1252ToUnicode.put(155, 8250);
    cp1252ToUnicode.put(156, 339);
    cp1252ToUnicode.put(158, 382);
    cp1252ToUnicode.put(159, 376);

    CP1252_TO_UNICODE = Collections.unmodifiableMap(cp1252ToUnicode);
  }

  public static final Set<Integer> CP1252_IGNORE;

  static {
    final Set<Integer> cp1252Ignore = new HashSet<>();
    cp1252Ignore.add(0);
    cp1252Ignore.add(129);
    cp1252Ignore.add(141);
    cp1252Ignore.add(143);
    cp1252Ignore.add(144);
    cp1252Ignore.add(157);

    CP1252_IGNORE = Collections.unmodifiableSet(cp1252Ignore);
  }
}
