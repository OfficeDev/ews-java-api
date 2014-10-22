package microsoft.exchange.webservices.data;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertThat;

/**
 * Testclass for methods of Task
 *
 * @author serious6
 */
@RunWith(JUnit4.class)
public class TaskTest extends BaseTest {

    /**
     * Mock for the Task
     */
    private Task taskMock;

    /**
     * Setup Mocks
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        this.taskMock = new Task(this.exchangeServiceMock);
    }

    /**
     * Test for reading the value before it is assigned
     *
     * @throws Exception
     */
    @Test(expected = ServiceObjectPropertyException.class)
    public void testInitialValuePercent() throws Exception {
        taskMock.getPercentComplete();
    }

    /**
     * Test for adding 0.0 as percentCompleted
     *
     * @throws Exception
     */
    @Test
    public void testAddZeroPercent() throws Exception {
        final Double targetValue = 0.0;
        taskMock.setPercentComplete(targetValue);

        assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
        assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
        assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
    }

    /**
     * Test for adding 100.0 as percentCompleted
     *
     * @throws Exception
     */
    @Test
    public void testAdd100Percent() throws Exception {
        final Double targetValue = 100.0;
        taskMock.setPercentComplete(targetValue);

        assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
        assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
        assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
    }

    /**
     * Test for adding Double.MAX_VALUE as percentCompleted
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddMaxDoublePercent() throws Exception {
        final Double targetValue = Double.MAX_VALUE;
        taskMock.setPercentComplete(targetValue);
    }

    /**
     * Test for adding Double.NEGATIVE_INFINITY as percentCompleted
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddMinDoublePercent() throws Exception {
        final Double targetValue = Double.NEGATIVE_INFINITY;
        taskMock.setPercentComplete(targetValue);
    }

    /**
     * Test for adding Double.NaN as percentCompleted
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNanDoublePercent() throws Exception {
        final Double targetValue = Double.NaN; // closest Value to Zero
        taskMock.setPercentComplete(targetValue);
    }

    /**
     * Test for checking if the value changes in case of a thrown exception
     *
     * @throws Exception
     */
    @Test
    public void testDontChangeValueOnException() throws Exception {
        // set valid value
        final Double targetValue = 50.5;
        taskMock.setPercentComplete(targetValue);

        assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
        assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
        assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));

        final Double invalidValue = Double.NEGATIVE_INFINITY;
        try {
            taskMock.setPercentComplete(invalidValue);
        } catch (IllegalArgumentException ex) {
            // ignored
        }

        assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
        assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
        assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
    }
}
