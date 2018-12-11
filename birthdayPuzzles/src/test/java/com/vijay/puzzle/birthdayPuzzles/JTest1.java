package com.vijay.puzzle.birthdayPuzzles;





import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JTest1 {
	List<String> expected = Arrays.asList("a", "b", "c");

	@Test
	public void test() {
		String result=CherylBirthday.concat("ram", "sita");
		assertEquals(result, "ramsita");
	}
	

	@Test
	public void test2() {
		String result=CherylBirthday.concat("", "sita");
		assertEquals(result, "sita");
	}

	@Test
	public void test3() {
		String result=CherylBirthday.concat("", "");
		assertNotEquals("ramsita", result);
	}
	
	@Test
	public void test4() {
		List<String> result=CherylBirthday.getList();
		assertThat(result, is(expected));
	}
}
