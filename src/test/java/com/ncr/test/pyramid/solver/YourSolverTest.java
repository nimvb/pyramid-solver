package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.impl.YourSolver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class YourSolverTest {
    private static final int MAX_DEPTH = 100;

    private static final int[][] SAMPLE_DATA = {
            { 5, 9, 8, 4 },
            { 6, 4, 5, 0 },
            { 6, 7, 0, 0 },
            { 3, 0, 0, 0 }
    };
    private static final int[][] DEMO_DATA = {
            { 59, 207, 98, 95 },
            { 87,   1, 70,  0 },
            { 36,  41,  0,  0 },
            { 23,   0,  0,  0 }
    };

    private static final int[][] NEGATIVE_DATA = {
            {-7, -8, -3, -10},
            {-4, -5, -6, 0},
            {-2, -3, 0, 0},
            {-1, 0, 0, 0}
    };

    private static final int[][] MIXED_NEGATIVE_POSITIVE_DATA = {
            {-7, -8, -3, 10},
            {-4, -5, -9, 0},
            {-2, -3, 0, 0},
            {-1, 0, 0, 0}
    };

    private static final int[][] DEMO_DATA_WITH_NON_ZERO_NEUTRAL_VALUES   = {
            {59, 207, 98, 95},
            {87, 1, 70, -80},
            {36, 41, 0, 1000},
            {23, 13, 12, 500}
    };

    protected PyramidSolver solver;

    @Before
    public void setUp() {
        solver = new YourSolver();
    }

    @Test
    public void solverHandlesSampleData() {
        Pyramid pyramid = new Pyramid(SAMPLE_DATA);
        assertEquals("Max path in Sample pyramid", 24, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesDemoData() {
        Pyramid pyramid = new Pyramid(DEMO_DATA);
        assertEquals("Max path in Demo pyramid", 353, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverSurvivesLargeData() {
        PyramidGenerator generator = new RandomPyramidGenerator(MAX_DEPTH, 1000);
        Pyramid pyramid = generator.generatePyramid();
        assertTrue("Max path in a large pyramid not positive", solver.pyramidMaximumTotal(pyramid) > 0L);
    }

    @Test
    public void solverHandlesRandomData() {
        RandomPyramidGenerator.setRandSeed(25321L);  // ensure pyramid contents
        final PyramidGenerator generator = new RandomPyramidGenerator(5, 99);
        final Pyramid pyramid = generator.generatePyramid();

        assertEquals("Max path in 'random' pyramid", 398, this.solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenEmptyPyramid_shouldReturnZero() {
        final Pyramid pyramid = new Pyramid(new int[0][]);

        assertEquals("Max path in 'empty' pyramid", 0, this.solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenPyramidWithSingleElement_shouldReturnTheElementValue() {
        int[][] data = new int[1][];
        data[0] = new int[]{50};

        final Pyramid singleElementPyramid = new Pyramid(data);

        assertEquals("Max path in 'single-element' pyramid", 50, this.solver.pyramidMaximumTotal(singleElementPyramid));
    }

    @Test
    public void givenNULLPyramid_shouldThrownAnException() {
        assertThrows(IllegalArgumentException.class, () -> this.solver.pyramidMaximumTotal(null));
    }

    @Test
    public void givenPyramidWithNegativeValues_shouldReturnCorrectMaximumValue() {
        final Pyramid pyramid = new Pyramid(NEGATIVE_DATA);

        assertEquals("Max path in 'all-negative' pyramid", -11, this.solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenPyramidWithMixedValues_shouldReturnCorrectMaximumValue() {
        final Pyramid pyramid = new Pyramid(MIXED_NEGATIVE_POSITIVE_DATA);

        assertEquals("Max path in 'mix-negative-positive' pyramid", -3, this.solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenDemoDataWithNonZeroNeutralValues_shouldReturnTheCorrectResult() {
        Pyramid pyramid = new Pyramid(DEMO_DATA_WITH_NON_ZERO_NEUTRAL_VALUES);
        assertEquals("Max path in Demo pyramid", 353, solver.pyramidMaximumTotal(pyramid));
    }
}
