package com.leif.ffDataServer.helper;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author leif
 *
 */
public class AgeCalculator
{
	public static int calculateAge(LocalDate birthDate, LocalDate currentDate)
	{
		if ((birthDate != null) && (currentDate != null))
		{
			return Period.between(birthDate, currentDate).getYears();
		} 
		else
		{
			return 0;
		}
	}
	
	public static Period calculateTimespan(LocalDate start, LocalDate end)
	{
		if (start == null)
		{
			return Period.ZERO;
		} 
		
		if(end == null)
		{
			end = LocalDate.now();
		}
		
		return Period.between(start, end);
	}
}
//
//public class AgeCalculatorTest {
//
//    @Test
//    public void testCalculateAge_Success() {
//        // setup
//        LocalDate birthDate = LocalDate.of(1961, 5, 17);
//        // exercise
//        int actual = AgeCalculator.calculateAge(birthDate, LocalDate.of(2016, 7, 12));
//        // assert
//        Assert.assertEquals(55, actual);
//    }
//}