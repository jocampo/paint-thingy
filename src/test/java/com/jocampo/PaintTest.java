package com.jocampo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PaintTest {

    // TODO: For the sake of simplicity, we'll assume that each color is represented by a letter (A, B, C...)
    // Eventually, we'd change these into actual colors (hexadecimal representation, for example)

    /*
     * Test Grid painting function
     */
    @Test
    public void fillMatchingSlotsBasic() {
        String[][] grid = {
                {"A", "A", "A", "A"},
                {"C", "C", "C", "A"},
                {"A", "A", "A", "A"}
        };
        String[][] result = {
                {"B", "B", "B", "B"},
                {"C", "C", "C", "B"},
                {"B", "B", "B", "B"}
        };
        // Painting the first slot paints ALL of the As
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 0, "B"), result);
    }

    @Test
    public void fillMatchingSlotsComplex() {
        String[][] grid = {
                {"A", "A", "A", "A"},
                {"C", "A", "F", "K"},
                {"A", "F", "D", "F"}
        };
        String[][] result = {
                {"B", "B", "B", "B"},
                {"C", "B", "F", "K"},
                {"A", "F", "D", "F"}
        };
        // Painting another slot only paints the As
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 3, "B"), result);
    }

    @Test
    public void fillMatchingSlotsIsolatedColor() {
        String[][] grid = {
                {"A", "A", "A"},
                {"B", "B", "B"},
                {"B", "A", "B"},
                {"B", "B", "B"}
        };
        String[][] result = {
                {"A", "A", "A"},
                {"B", "B", "B"},
                {"B", "B", "B"},
                {"B", "B", "B"}
        };
        // Painting the first slot paints ALL of the As
        assertArrayEquals(Paint.fillMatchingSlots(grid, 2, 1, "B"), result);
    }

    @Test
    public void fillOnlyAdjacentSlots() {
        String[][] grid = {
                {"A", "B"},
                {"B", "A"}
        };
        String[][] result = {
                {"B", "B"},
                {"B", "A"}
        };
        // Check that we don't paint both As
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 0, "B"), result);
    }

    @Test
    public void fillSameColor() {
        String[][] grid = {
                {"A", "A", "A"},
                {"B", "B", "B"},
                {"B", "A", "B"},
                {"B", "B", "B"}
        };
        String[][] result = {
                {"A", "A", "A"},
                {"B", "B", "B"},
                {"B", "A", "B"},
                {"B", "B", "B"}
        };
        // Check that we both grids remain equal
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 0, "A"), result);
    }

    @Test
    public void testNullGrid() {
        String[][] grid = null;
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 0, "B"), null);
    }

    @Test
    public void testNegativeCoordinates() {
        String[][] grid = {
                {"A", "B"},
                {"B", "A"}
        };
        assertArrayEquals(Paint.fillMatchingSlots(grid, -1, 0, "B"), null);
    }

    @Test
    public void testOverflowingCoordinates() {
        String[][] grid = {
                {"A", "B"},
                {"B", "A"}
        };
        assertArrayEquals(Paint.fillMatchingSlots(grid, 0, 200, "B"), null);
    }

    /*
     * Test Grid printing function
     */
    @Test(expected = Exception.class)
    public void testGridPrinterException() throws Exception {
        Paint.printGrid(null);
    }

    @Test
    public void testGridPrinterRegularFlow() throws Exception {
        String[][] grid = {
                {"A", "B"},
                {"B", "A"}
        };
        // TODO: Actually validate what it prints? For now, just check that it doesn't blow up
        Paint.printGrid(grid);
    }
}