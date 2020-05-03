import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tools extends JPanel {
    private JButton Start;
    private JPanel TButtons;
    private JPanel Nums;
    public int[][][][] Questions = new int[10][9][3][3];

    public Tools(){
        Start = new JButton("开始游戏");
        setLayout(new BorderLayout());
        add(Start,BorderLayout.NORTH);
        //点击开始游戏选择难度
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //建立三个难度选项
                Object[] Difficulties = {"新手","普通","专家"};
                //初始化难度选择
                //建立选择弹框
                Object difficulty = JOptionPane.showInputDialog(null,"请选择游戏难度：","难度选择",JOptionPane.INFORMATION_MESSAGE,null,Difficulties,Difficulties[1]);
                //不同难度选择题库
                if (difficulty==Difficulties[0]){
                    Window.changeState(true);
                    String fileName = "Easy.txt";
                    try {
                        Questions = readFromFile(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if (difficulty==Difficulties[1]){
                    Window.changeState(true);
                    String fileName = "Normal.txt";
                    try {
                        Questions = readFromFile(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if (difficulty==Difficulties[2]){
                    Window.changeState(true);
                    String fileName = "Hard.txt";
                    try {
                        Questions = readFromFile(fileName);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
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

    public int[][][][] readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        //十组题目
        int[][][][] que = new int[10][9][3][3];
        Scanner in = new Scanner(file);
        for (int i=0;i<2;i++){
            for (int z=0;z<9;z++){
                for (int x=0;x<3;x++){
                    for (int y=0;y<3;y++){
                        que[i][z][x][y] = in.nextInt();//这里报错
                    }
                }
                //换行
                in.nextLine();
                in.nextLine();
            }
        }
        //debug用的输出
        for (int i=0;i<2;i++){
            for (int z=0;z<9;z++){
                for (int x=0;x<3;x++){
                    for (int y=0;y<3;y++){
                        System.out.print(que[i][z][x][y]);
                    }
                }
            }
        }
        return que;
    }
}

