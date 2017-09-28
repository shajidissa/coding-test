package com.connectgroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

public class DataFiltererTest {
	
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }
    
    @Test
    public void shouldReturnCollectionOfSizeZero_WhenLogFileHasOneRowAndCountryIsNotGB() throws FileNotFoundException {
        assertEquals(0, DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "US").size());
    }
    
    @Test
    public void shouldReturnCollectionOfSizeOne_WhenLogFileHasOneRowAndWhoseCountryIsGB() throws FileNotFoundException {
        assertEquals(1, DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB").size());
    }
    
    @Test
    public void shouldReturnCollectionOfOne_WhenLogFileHasManyRowsAndHasOneGB_AndResponseTimeGreaterThan36() throws FileNotFoundException {
        assertEquals(1, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "GB", 36).size());
    }
    
    @Test
    public void shouldReturnCollectionOfThree_WhenLogFileHasManyRowsAndHasThreeUS_AndResponseTimeGreaterThan538() throws FileNotFoundException {
        assertEquals(3, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 538).size());
    }
    
    @Test
    public void shouldReturnCollectionOfTwo_WhenLogFileHasManyRowsAndHasThreeUS_AndResponseTimeGreaterThan539() throws FileNotFoundException {
        assertEquals(2, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 539).size());
    }
    
    @Test
    public void shouldReturnCollectionOfOne_WhenLogFileHasManyRowsAndHasThreeUS_AndResponseTimeGreaterThan789() throws FileNotFoundException {
        assertEquals(1, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 789).size());
    }
    
    @Test
    public void shouldReturnCollectionOfZero_WhenLogFileHasManyRowsAndDoesNotHaveGB_AndResponseTimeGreaterThan300() throws FileNotFoundException {
        assertEquals(0, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "GB", 300).size());
    }
    
    @Test
    public void shouldReturnCollectionOfThree_WhenLogFileHasManyRowsAndAverageIs526() throws FileNotFoundException {
        assertEquals(3, DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).size());
    }
    
    @Test
    public void shouldReturnCollectionOfZero_WhenLogFileHasOneRow() throws FileNotFoundException {
        assertEquals(0, DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/single-line")).size());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
