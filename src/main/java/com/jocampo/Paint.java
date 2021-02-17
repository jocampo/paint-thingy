package com.jocampo;

public class Paint {
    private final static String[][] DEFAULT_GRID = {
            {"A", "A", "A", "A"},
            {"C", "C", "C", "A"},
            {"A", "A", "A", "A"}
    };

    private final static String[][] DEFAULT_GRID_2 = {
            {"A", "A", "A"},
            {"B", "B", "B"},
            {"B", "A", "B"},
            {"B", "B", "B"}
    };

    /**
     * Convenience function to print the grid into the console
     * @param rows: grid object we're going to print
     * @throws Exception if the grid is null
     */
    public static void printGrid(String[][] rows) throws Exception {
        if (rows == null) {
            throw new Exception("ERROR: Attempted to print a null grid");
        }

        for (String[] row : rows) {
            System.out.println(String.join(" : ", row));
        }
    }



    /**
     * Paints over the desired coordinates of the grid, using a new color and spreading throughout the grid,
     * similar to how the "fill" function works in MS-Paint
     *
     * @param rows: grid object we're going to use as basis
     * @param x: 0-based position of the row we want to paint over
     * @param y: 0-based position of the column we want to paint over
     * @param newColor: 0-based position of the column we want to paint over
     */
    public static String[][] fillMatchingSlots(String[][] rows, int x, int y, String newColor) {
        // Validate grid and newColor
        if (rows == null || newColor == null || newColor.equals("")) {
            return null;
        }
        // Validate coords
        if (x < 0 || x >= rows.length) {
            return null;
        }
        if (y < 0 || y >= rows[x].length) {
            return null;
        }

        // Paint the cell
        String originalColor = rows[x][y];
        rows[x][y] = newColor;

        if (originalColor.equals(newColor)) {
            // Realistically, painting the same cell with the same color should change nothing, so just return
            return rows;
        }

        // Move on to adjacent matching-color cells:
        // NOTE: There's no need to check if we have already visited a certain cell, since we already painted it (so the
        // color will NOT match anymore and we won't want to visit it anyway). This happens because all of the function
        // calls are working on the same object reference.

        if (y - 1 >= 0 && rows[x][y - 1].equals(originalColor)) {
            fillMatchingSlots(rows, x, y - 1, newColor);
        }
        if (x + 1 < rows.length && rows[x + 1][y].equals(originalColor)) {
            fillMatchingSlots(rows, x + 1, y, newColor);
        }
        if (y + 1 < rows[x].length && rows[x][y + 1].equals(originalColor)) {
            fillMatchingSlots(rows, x, y + 1, newColor);
        }
        if (x - 1 >= 0 && rows[x - 1][y].equals(originalColor)) {
            fillMatchingSlots(rows, x - 1, y, newColor);
        }
        return rows;
    }

    public static void main(String[] args) throws Exception {
        printGrid(DEFAULT_GRID_2);
        System.out.println();
        System.out.println();
        printGrid(fillMatchingSlots(DEFAULT_GRID_2, 2, 1, "C"));
    }
}
