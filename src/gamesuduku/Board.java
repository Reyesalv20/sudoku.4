package gamesuduku;
import java.awt.Color;
import java.util.Random;
import javax.swing.*;
public class Board {
    Random rng = new Random();
    private int numbers[][] = new int[9][9];
    
    private boolean checkNum(int num, int ogRow, int ogCol){
        for(int row = 0;row<9;row++){
            if(numbers[row][ogCol]==num)
                return false;
        }
        for(int col = 0;col<9;col++){
            if(numbers[ogRow][col]==num)
                return false;
        }
        return true;
    }
    
    private void FillSector(int ixRow,int ixCol){
        int array[] = new int[9];
        int cont = 0;
        int ogCol = ixCol;
        while (cont<9){
            int num = rng.nextInt(0,10);
            boolean check = false;
            for(int number : array){
                if(num==number){
                    check = true;
                }
            }
            if(check==false){
                if(checkNum(num,ixRow,ixCol)==true){
                    System.out.println(num);
                    array[cont]=num;
                    numbers[ixRow][ixCol] = num;
                    ixCol++;
                    cont++;
                    if(cont==3 || cont==6){
                        ixRow++;
                        ogCol++;
                        ixCol=ogCol;
                    }
                }
            }
        }
    }
    
    private void FillBoard(){
        FillSector(0,0);
        FillSector(0,3);
        FillSector(0,6);
        FillSector(3,0);
        FillSector(3,3);
        FillSector(3,6);
        FillSector(6,0);
        FillSector(6,3);
        FillSector(6,6);
        
    }
    
    public void CreateBoard(){
        FillBoard();
        JFrame board = new JFrame();
        board.setSize(500,500);
        board.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.red);
        panel.setSize(450,450);
        panel.setLayout(null);
        board.add(panel);
        int xPos = 0;
        for(int row = 0; row<9 ;row++){
            int yPos = 0;
            for(int col = 0; col<9 ;col++){
                String labelName = row+"-"+col;
                JTextField field = new JTextField();
                field.setName(labelName);
                field.setText(Integer.toString(numbers[row][col]));
                field.setBounds(xPos, yPos, 50, 50);
                if((row+col)%2==0)
                    field.setBackground(Color.white);
                else
                    field.setBackground(Color.gray);
                field.setOpaque(true);
                panel.add(field);
                //-------------------------------------------------
                yPos += 50;
            }
            xPos += 50;
        }
        board.setVisible(true);
        panel.setVisible(true);
    }
}
