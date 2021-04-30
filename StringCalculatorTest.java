package calculator;
import org.jnit.*;
import static org.junit.Assert.*;

public class CalculatorTest
{
	@Test
	public void shouldReturnZeroOnEmptyString()
	{
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void shouldReturnNumberOnNumber()
	{
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void shouldReturnSumOnTwoNumbersDelimitedByComma()
	{
		assertEquals(3, Calculator.add("1,2"));
	}

	@Test
	public void shouldReturnSumOnMultipleNumbers()
	{
		assertEquals(6, Calculator.add("1,2,3"));
	}

	@Test
	public void shouldAcceptNewlinesAsValidDelimiter()
	{
		assertEquals(6, Calculator.add("1,2\n3"));
	}

	@Test
	public void shouldAcceptCustomeDelimiterSyntax()
	{
		assertEquals(3, Calculator.add("//;\n1;2"));
	}

	@Test
	public void shouldAcceptCustomeDelimiterSyntaxcouldBeAlsoARegExpSpecialChar()
	{
		assertEquals(3, Calculator.add("//.\n1.2"));
	}

	@Test
	public void shouldRaiseExceptionOnNegatives()
	{
		try
		{
			Calculator.add("-1,2,3");
			fail("Exception Expected..");
		}
		catch(RuntimeException ex)
		{
			//ok
		}
	}

	@Test
	public void exceptionMessageShouldContainTheNegativeNumber()
	{
		try
		{
			Calculator.add("-1,-2,3");
			fail("Exception Expected..");
		}
		catch(RuntimeException ex)
		{
			assertEquals("Negatives are Not Allowed: -1, -2", ex.getMessage());
		}
	}
}
