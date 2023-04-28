package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: There is something wrong here. A few things actually... 
 */
public class NaivePyramidSolver implements PyramidSolver {
    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        // Fixed handling NULL parameter
        if(pyramid == null){
            throw new IllegalArgumentException();
        }
        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        // Fixed bug - adding check for empty pyramids
        if(row < 0) return 0;

        // Fixed bug - should return the elements of the topmost row
        // Returning zero will omit the values of the topmost elements that is the reason for returning wrong result
        if (row == 0) return pyramid.get(row,column);

        int myValue = pyramid.get(row, column);
        long left  = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
        return Math.max(left, right);
    }
}