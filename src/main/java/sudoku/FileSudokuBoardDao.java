package sudoku;

import java.io.*;
import java.util.Scanner;

public class FileSudokuBoardDao implements Dao <SudokuBoard>{
    private String path;

    FileSudokuBoardDao(String path){
        this.path=path;
    }

    public SudokuBoard read() {
        SudokuBoard sudoku = new SudokuBoard();
        try {
            Scanner scanner = new Scanner(new File(path));
            int i =0, j=0 ;
            while (scanner.hasNextInt()){
                sudoku.set(i,j, scanner.nextInt());
                j++;
                if(j%9==0){
                    i++;
                    j=0;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie znaleziono pliku");
        }

        return sudoku;
        /*try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard)inputStream.readObject();
        }
        catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
        catch (ClassNotFoundException e){
            System.out.println("Szukana klasa nie istnieje");
        }
        return sudoku;*/
    }

    public void write(SudokuBoard obj) {
        try{
            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter(path));
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    //outputWriter.write(obj.get(i,j));
                    outputWriter.write((obj.get(i,j))+" ");
                }
                outputWriter.newLine();
            }
            outputWriter.close(); //to chyba musi byc w innej klasie
        }
        catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
        /*try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        }
        catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }*/
    }
}
