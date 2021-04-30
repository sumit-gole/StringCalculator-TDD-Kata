package calculator;

import java.util.*;
import ch.lambdaj.function.convert.Converter;
import static ch.lambdaj.Lambda.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.hamcrest.Matchers.*;

public class Calculator
{
	public static int add(String str)
	{
			List<Integer> numbers = parseNumbers(str);
			ensureAllNonNegative(numbers);
			return sum(numbers).intValue();
	}

	private static List<Integer> parseNumbers(String str)
	{
		String[] tokens = tokenize(str);
		List<Integer> numbers = convert(tokens, toInt());
		return numbers;
	}

	private static void ensureAllNonNegative(List<Integer> numbers) throws RuntimeException
	{
		List<Integer> negatives = filter(lessThan(0), numbers);
		if(negatives.size() > 0)
		{
			throw new RuntimeException("Negatives are Not Allowed : " + join(negatives));
		}
	}

	private static String[] tokenize(String str)
	{
		if(str.isEmpty())
		{
			return new String[0];
		}
		else if(usesCustomDelimiterSysntax(str))
		{
			return splitUsingCustomDelimiterSyntax(str);
		}
		else{
			return splitUsingNewlinesAndCommas(str);
		}
	}

	private static boolean usesCustomDelimiterSysntax(String str)
	{
		return str.startsWith("//");
	}

	private static String[] splitUsingNewlinesAndCommas(String str)
	{
		String[] tokens = str.split(",|\n");
		return tokens;
	}

	private static String[] splitUsingCustomDelimiterSyntax(String str)
	{
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(str);
		m.matches();
		String customDelimiter = m.group(1);
		String numbers = m.group(2);
		return numbers.split(Pattern.quote(customDelimiter));
	}

	private static Converter<String, Integer> toInt()
	{
		return new Converter<String, Integer>() {
				public Integer convert(String from){
					return toInt(from);
				}
			};
	}

	private static int toInt(String str) throws NumberFormatException
	{
		return Integer.parseInt(str);
	}
}
