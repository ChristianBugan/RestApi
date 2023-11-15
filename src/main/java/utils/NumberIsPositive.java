package utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;

public class NumberIsPositive extends TypeSafeMatcher<Integer>{

	public void describeTo(Description description) {
		description.appendText(" postive number ");
	}

	@Override
	protected boolean matchesSafely(Integer item) {

		return item >0;
		
	}

	public static Matcher<Integer> postiveNumber(){
		return new NumberIsPositive();
	}
	
	
}
