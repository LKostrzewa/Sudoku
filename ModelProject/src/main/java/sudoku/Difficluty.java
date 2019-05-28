package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Difficluty {
    EASY(20),
    MEDIUM(40),
    HARD(60);

    private final int length;

    Difficluty(final int len) {
        this.length = len;
    }

    public void clean(final SudokuBoard sudoku) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        //System.out.println(list);
        for (int i = 0; i < length; i++) {
            //System.out.print(list.get(i)+"\t");
            sudoku.set(list.get(i) / 9, list.get(i) % 9, 0);
        }
        //System.out.println("\n");
    }
}
