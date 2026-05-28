package sit707_week6;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {
	
	@Test
	public void testStudentIdentity() {
		String studentId = "s225504843";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Nephat Komu Muriithi";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	@Test
	public void testFalseNumberIsEven() {
		Assert.assertFalse(WeatherAndMathUtils.isEven(5));
	}
	
	@Test
	public void testTrueNumberIsEven() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(4));
	}
	
    @Test
    public void testCancelWeatherAdvice() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }
    
    @Test
    public void testNegativeWindSpeedThrowsException() {
    	Assert.assertThrows(IllegalArgumentException.class, () -> WeatherAndMathUtils.weatherAdvice(-1, 2));
    }
    @Test
    public void testNegativePrecipitationThrowsException() {
    	Assert.assertThrows(IllegalArgumentException.class, () -> WeatherAndMathUtils.weatherAdvice(10, -1));
    }
    
    @Test
    public void testAllClear() {
    	Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(20, 2));
    }
    
    @Test
    public void testDangerousWindBoundary() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0));
    }
    @Test
    public void testIsPrime_one() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(1));
    }
    
    @Test
    public void testIsPrime_evenSmall() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
    }
    @Test
    public void testBoundaryWind70() {
    	Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(70.0, 3));
    }
    @Test
    public void testIsPrime_evenLarge() {
    	Assert.assertFalse(WeatherAndMathUtils.isPrime(10));
    }
    @Test
    public void testLoopNotExecuted_n2() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
    }

    @Test
    public void testLoopNotExecuted_n1() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(1));
    }

    @Test
    public void testLoopExecuted_evenNumber() {
    	Assert.assertFalse(WeatherAndMathUtils.isPrime(4)); 
    }

    @Test
    public void testLoopExecuted_largeEven() {
    	Assert.assertFalse(WeatherAndMathUtils.isPrime(10)); 
    }

    @Test
    public void testLoopExecuted_oddNumber() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(3)); 
    }

    @Test
    public void testLoopExecuted_nonPrimeOdd() {
    	Assert.assertTrue(WeatherAndMathUtils.isPrime(9));
    }
    @Test
    public void testCancelWindDanger() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(71, 3));
    }

    @Test
    public void testCancelRainDanger() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(20, 7));
    }

    @Test
    public void testCancelCombinedCondition() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(50, 5));
    }

    @Test
    public void testWarnWindOnly() {
    	Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(50, 2));
    }

    @Test
    public void testWarnRainOnly() {
    	Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(20, 5));
    }

    @Test
    public void testAllClearr() {
    	Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(20, 2));
    }
    @Test
    public void testBoundaryWind45Rain4() {
    	Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(45, 4));
    }
    @Test
    public void testNegativeWindAndRainThrowsException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> WeatherAndMathUtils.weatherAdvice(-1, -1));
    }
    
    @Test
	public void testSumUpToN_normalCase() {
		assertEquals(6, LoopUtil.sumUpToN(3));
	}

	@Test
	public void testSumUpToN_singleValue() {
		assertEquals(1, LoopUtil.sumUpToN(1));
	}

	@Test
	public void testSumUpToN_zeroCase() {
		assertEquals(0, LoopUtil.sumUpToN(0));
	}

	@Test
	public void testSumUpToN_largeValue() {
		assertEquals(15, LoopUtil.sumUpToN(5));
	}

	@Test
	public void testCountMultiplesOfThree_basic() {
		assertEquals(1, LoopUtil.countMultiplesOfThree(3));
	}

	@Test
	public void testCountMultiplesOfThree_multipleMatches() {
		assertEquals(3, LoopUtil.countMultiplesOfThree(10));
	}

	@Test
	public void testCountMultiplesOfThree_noMatches() {
		assertEquals(0, LoopUtil.countMultiplesOfThree(2));
	}

	@Test
	public void testCountMultiplesOfThree_zero() {
		assertEquals(0, LoopUtil.countMultiplesOfThree(0));
	}

	@Test
	public void testCountMultiplesOfThree_boundary() {
		assertEquals(4, LoopUtil.countMultiplesOfThree(12));
	}

	@Test
	public void testSumUpToN_normal() {
		assertEquals(15, LoopUtil.sumUpToN(5));
	}

	@Test
	public void testSumUpToN_small() {
		assertEquals(6, LoopUtil.sumUpToN(3));
	}

	@Test
	public void testCountMultiples_basic() {
		assertEquals(1, LoopUtil.countMultiplesOfThree(3));
	}

	@Test
	public void testCountMultiples_multiple() {
		assertEquals(3, LoopUtil.countMultiplesOfThree(10));
	}

	@Test
	public void testCountMultiples_none() {
		assertEquals(0, LoopUtil.countMultiplesOfThree(2));
	}

	@Test
	public void testCountMultiples_zero() {
		assertEquals(0, LoopUtil.countMultiplesOfThree(0));
	}

	@Test
	public void testCountMultiples_boundary() {
		assertEquals(4, LoopUtil.countMultiplesOfThree(12));
	}
	
}
