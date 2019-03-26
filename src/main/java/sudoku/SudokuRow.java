package sudoku;

public class SudokuRow extends SudokuElement {
    public SudokuRow(SudokuField[] fields){
        super(fields);
    }
    public String toString(){
        String sout="";
        for(int i=0;i<9;i++){
            sout+=fields[i].getFieldValue()+ " ";
        }
        sout+="\n";
        return sout;
    }


}
