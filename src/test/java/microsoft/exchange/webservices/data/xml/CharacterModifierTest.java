package microsoft.exchange.webservices.data.xml;

import com.github.rwitzel.streamflyer.core.ModifyingReader;
import com.github.rwitzel.streamflyer.regex.RegexModifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(JUnit4.class)
public class CharacterModifierTest {

  private static final String SIMPLE_STRING = "Hi there !";

  public static void main(String[] args) throws IOException {
    BufferedReader reader =
        new BufferedReader(
            new ModifyingReader(
                new StringReader("<xml>111&#x0;333&#x0;&#x0;222</xml>"),
                new RegexModifier("&#x0;", 0, "")
            ));

    System.out.println(reader.readLine());
  }

  @Test
  public void testModify() {

    try (
        BufferedReader reader =
            new BufferedReader(
                new ModifyingReader(
                    new StringReader(SIMPLE_STRING),
                    new CharacterModifier(Collections.singletonMap(101, 69), Collections.singleton(32))
                ))
    ) {

      assertEquals("HithErE!", reader.readLine());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }

}