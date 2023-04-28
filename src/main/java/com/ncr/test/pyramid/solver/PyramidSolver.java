package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;

/**
 *  Represents the API of a problem solver.
 *  Refer to {@link com.ncr.test.pyramid.data.Pyramid} for problem description.
 */
public interface PyramidSolver {
    /**
     * Calculate the maximum sum for the given inverted pyramid elements(bottom-to-top)
     * @param pyramid the pyramid object
     * @throws IllegalArgumentException if the given {@code pyramid} is {@code null}
     * @return maximum sum of the inverted pyramid elements
     */
    long pyramidMaximumTotal(Pyramid pyramid);
}
