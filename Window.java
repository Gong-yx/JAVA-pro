import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.undo.UndoManager;
import java.awt.*;

public class Window extends JFrame {
    private static Grid SudokuGrid;
    private JLabel Title;
    private Tools tools;

    //分步设置画布各个界面及功能
    public Window(){
        init();
        addGrid();
        addTitle();
        addTools();
        this.setVisible(true);
    }
//设置画布
    private void init(){
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(400,320);
        this.setResizable(false);
        this.setTitle("Sudoku");
        this.setLayout(new BorderLayout());
    }
//设置九宫格
    private void addGrid(){
        //加入棋盘并放置于窗口中间
        SudokuGrid = new Grid();
        SudokuGrid.setBorder(new TitledBorder("Game"));
        this.add(SudokuGrid,BorderLayout.CENTER);
    }
//设置画布名称
    private void addTitle(){
        Title = new JLabel("爱        数        独",JLabel.CENTER);
        Title.setFont(new Font("爱数独",1,20));
        this.add(Title,BorderLayout.NORTH);
    }
//设置工具栏按钮及功能
    private void addTools(){
        tools = new Tools();
        tools.setBorder(new TitledBorder("Tools"));
        this.add(tools,BorderLayout.EAST);
    }
    //改变棋盘的开放状态（可输入或不可输入）
    public static void changeState(boolean state){
        SudokuGrid.changeGrid(state);
    }
    //主程序
    public static void main(String[] args) {
        new Window();
    }
}

