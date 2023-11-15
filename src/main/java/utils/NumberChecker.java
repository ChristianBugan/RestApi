package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberChecker extends TypeSafeMatcher<String>{

	public void describeTo(Description description) {
		description.appendText("expected numbers but got :");
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			Integer.parseInt(item);// "123" --> TRUE //Abc2000 --> False
			return true;
			
		}catch(Exception e) {
			return false;
		}	
	}
	
	public static Matcher<String> numbersOnly(){
		return new NumberChecker();
	}

}
