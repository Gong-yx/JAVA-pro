import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tools extends JPanel {
    private JButton Start;
    private JPanel TButtons;
    private JPanel Nums;
    public static String fileName;
    public static int[][][] Question = new int[9][3][3];
    //记录最后一次改动的坐标
    public static ArrayList<Integer> lastX = new ArrayList<>();
    public static ArrayList<Integer> lastY = new ArrayList<>();
    public static ArrayList<Integer> lastZ = new ArrayList<>();
    public static ArrayList<String> lastNum = new ArrayList<>();


    public Tools(){
        Start = new JButton("开始游戏");
        setLayout(new BorderLayout());
        add(Start,BorderLayout.NORTH);
        //点击开始游戏选择难度
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //初始化提示次数
                ToolButtons.count = 10;
                CountRemain.setText(ToolButtons.count);
                //清空所有格子
                for (int z=0;z<9;z++){
                    for (int x=0;x<3;x++){
                        for (int y=0;y<3;y++){
                            Grid.txtGame[z][x][y].setText("");

                        }
                    }
                }
                //建立三个难度选项
                Object[] Difficulties = {"新手","普通","专家"};
                //初始化难度选择
                //建立选择弹框
                Object difficulty = JOptionPane.showInputDialog(null,"请选择游戏难度：","难度选择",JOptionPane.INFORMATION_MESSAGE,null,Difficulties,Difficulties[1]);
                //不同难度选择题库
                if (difficulty==Difficulties[0]){
                    Window.changeState(true);
                    fileName = "Easy"+ (int)(Math.random()*10+1)+".txt";
                    try {
                        displayQuestion(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if (difficulty==Difficulties[1]){
                    Window.changeState(true);
                    fileName = "Normal"+ (int)(Math.random()*10+1)+".txt";
                    try {
                        displayQuestion(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if (difficulty==Difficulties[2]){
                    Window.changeState(true);
                    fileName = "Hard"+ (int)(Math.random()*10+1)+".txt";
                    try {
                        displayQuestion(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            private ActionListener getActionListener() {
                return this;
            }
        });
        addNums();
        addToolButtons();
    }

    private void addNums(){
        Nums = new NumButtons();
        this.add(Nums,BorderLayout.CENTER);
    }

    private void addToolButtons(){
        TButtons = new ToolButtons();
        this.add(TButtons,BorderLayout.SOUTH);
    }


    public void displayQuestion(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        //十组题目
        int[][][] que = new int[9][3][3];
        ArrayList<Integer> coordinateX = new ArrayList<>();
        ArrayList<Integer> coordinateY = new ArrayList<>();
        ArrayList<Integer> coordinateZ = new ArrayList<>();
        Scanner in = new Scanner(file);
        for (int z = 0; z < 9; z++) {
            for (int x = 0; x < 3; x++) {
                String s = in.nextLine();
                String[] ss = s.split(",");
                for (int y = 0; y < 3; y++) {
                    que[z][x][y] = Integer.parseInt(ss[y]);
                    //若为负数输出数字的绝对值并设置为不可编辑

                    if (que[z][x][y] < 0) {
                        Grid.txtGame[z][x][y].setText((-que[z][x][y]) + "");
                        Grid.txtGame[z][x][y].setForeground(Color.GRAY);
                        Grid.txtGame[z][x][y].setEditable(false);
                    }
                }
            }
        }
        for (int z=0;z<9;z++){
            for (int x=0;x<3;x++){
                for (int y=0;y<3;y++){
                    Question[z][x][y] = que[z][x][y];
                }
            }
        }
    }

}

