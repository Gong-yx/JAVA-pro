import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Window extends JFrame {
    private Grid SudokuGrid;
    private JLabel Title;

    public Window(){
        init();
        addGrid();
        addTitle();
        this.setVisible(true);
    }

    private void init(){
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(300,320);
        this.setResizable(false);
        this.setTitle("Sudoku");
        this.setLayout(new BorderLayout());
    }

    private void addGrid(){
        SudokuGrid = new Grid();
        SudokuGrid.setBorder(new TitledBorder("Game"));
        this.add(SudokuGrid,BorderLayout.CENTER);
    }

    private void addTitle(){
        Title = new JLabel("爱        数        独",JLabel.CENTER);
        Title.setFont(new Font("爱数独",1,20));
        this.add(Title,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new Window();
    }
}
