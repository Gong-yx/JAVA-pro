import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class Window extends JFrame {
    private static Grid SudokuGrid;
    private JLabel Title;
    private Tools tools;
    private static JPanel Attentions;

    //分步设置画布各个界面及功能
    public Window(){
        init();
        addGrid();
        addTitle();
        addTools();
        addAttentions();
        this.setVisible(true);
    }

    //设置画布，大小400*360，title为“数独”
    private void init(){
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(400,360);
        this.setResizable(false);
        this.setTitle("Sudoku");
        this.setLayout(new BorderLayout());
    }
    //设置九宫格，调用Grid
    private void addGrid(){
        SudokuGrid = new Grid();
        SudokuGrid.setBorder(new TitledBorder("Game"));
        this.add(SudokuGrid,BorderLayout.CENTER);
    }
    //设置游戏名称
    private void addTitle(){
        Title = new JLabel("数              独",JLabel.CENTER);
        Title.setBackground(Color.ORANGE);
        Title.setFont(new Font("数独",1,20));
        this.add(Title,BorderLayout.NORTH);
    }
    //设置工具栏按钮及功能，调用addTools
    private void addTools(){
        tools = new Tools();
        tools.setBorder(new TitledBorder("Tools"));
        this.add(tools,BorderLayout.EAST);
    }
    //在画布下方显示提示部分，调用addAttentions
    private void addAttentions(){
        Attentions=new Attention();
        Attentions.setBorder(new TitledBorder("Attention!"));
        this.add(Attentions, BorderLayout.SOUTH);
    }
    //改变棋盘的开放状态（可输入或不可输入）
    public static void changeState(boolean state){
        SudokuGrid.changeGrid(state);
    }

    //主程序
    public static void main(String[] args) {
        new Window();
        //游戏规则提示
        JOptionPane.showMessageDialog(null,
                "数独游戏规则："+
                        "请点击右侧工具栏中的1~9数字按钮将其填入左侧空格，每一行、列、九宫格中1~9均各出现一次且不能重复，" +
                        "空格被填满则游戏胜利。准备好就点击‘开始游戏’吧！");
    }
}
