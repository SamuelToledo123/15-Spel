public class IsSolvable {

    //https://stackoverflow.com/questions/34570344/check-if-15-puzzle-is-solvable
    public boolean isSolvable(int[] puzzle) {
        int parity = 0;
        int gridWidth = (int) Math.sqrt(puzzle.length);
        int row = 0; // den nuvarande raden vi är på
        int blankRow = 0; // raden med den tomma brickan

        for (int i = 0; i < puzzle.length; i++) {
            if (i % gridWidth == 0) { // gå vidare till nästa rad
                row++;
            }
            if (puzzle[i] == 0) { // den tomma brickan
                blankRow = row; // spara raden där den hittades
                continue;
            }
            for (int j = i + 1; j < puzzle.length; j++) {
                if (puzzle[i] > puzzle[j] && puzzle[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) { // jämnt rutnät
            if (blankRow % 2 == 0) { // tom bricka på ojämn rad; räknat från botten
                return parity % 2 == 0;
            } else { // tom bricka på jämn rad; räknat från botten
                return parity % 2 != 0;
            }
        } else { // udda rutnät
            return parity % 2 == 0;
        }
    }
}
