package microsoft.exchange.webservices.data.property.complex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * @author Vladislav Bauer
 */

@RunWith(JUnit4.class)
public class ComplexPropertyCollectionTest {

  @Test
  public void testComplexPropertyChangedPositive() {
    final ComplexPropertyCollection<ComplexProperty> collection = createFakeComplexPropertyCollection();

    final ComplexProperty property = createFakeComplexProperty();
    collection.complexPropertyChanged(property);

    final List<ComplexProperty> modifiedItems = collection.getModifiedItems();
    Assert.assertTrue(collection.getAddedItems().isEmpty());
    Assert.assertTrue(modifiedItems.contains(property));
    Assert.assertEquals(1, modifiedItems.size());
  }

  @Test(expected = RuntimeException.class)
  public void testComplexPropertyChangedNegative() {
    final ComplexPropertyCollection<ComplexProperty> collection = createFakeComplexPropertyCollection();
    collection.complexPropertyChanged(null);
    Assert.fail();
  }


  private ComplexProperty createFakeComplexProperty() {
    return new ComplexProperty() {};
  }

  private ComplexPropertyCollection<ComplexProperty> createFakeComplexPropertyCollection() {
    return new ComplexPropertyCollection<ComplexProperty>() {
      @Override protected ComplexProperty createComplexProperty(final String xmlElementName) {
        return null;
      }
      @Override protected String getCollectionItemXmlElementName(final ComplexProperty complexProperty) {
        return null;
      }
    };
  }

}
