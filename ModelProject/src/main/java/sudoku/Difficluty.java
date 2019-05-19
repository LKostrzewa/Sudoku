package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Difficluty {
    EASY(20),
    MEDIUM(40),
    HARD(60);

    private int length;

    Difficluty(int length){
        this.length = length;
    }

    public void clean(SudokuBoard sudoku){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<81; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i=0; i<length; i++){
            sudoku.set(list.get(i)/9,list.get(i)%9,0);
        }
    }
}
