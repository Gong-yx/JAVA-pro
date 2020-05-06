import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumButtons extends JPanel {
    private JButton[] Nums;
    //输入位置的行、列可能对应的所有z值分组
    private int[] to3r={0,1,2};
    private int[] to6r={3,4,5};
    private int[] to9r={6,7,8};
    private int[] to3c={0,3,6};
    private int[] to6c={1,4,7};
    private int[] to9c={2,5,8};
    //输入位置的行对应的所有z
    private int[] rowZ;
    //输入位置的列对应的所有z
    private int[] colZ;
    //当前是否已填满
    private boolean filled;

    //设置右侧工具栏九宫格
    public NumButtons(){
        filled=false;
        Nums = new JButton[9];
        for (int i=0;i<9;i++){
            Nums[i] = new JButton((i+1)+"");
        }
        setLayout(new GridLayout(3,3));
        for (int i=0;i<9;i++){
            add(Nums[i]);
            int finalI = i;
            int finalI1=i;
            //点击右侧工具栏的九宫格数字，可添加入9*9宫格中已选定区域
            Nums[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //确定行的z
                    if (java.util.Arrays.binarySearch(to3r, Grid.Z) < 0) {
                        if (java.util.Arrays.binarySearch(to6r, Grid.Z) < 0) {
                            rowZ = to9r;
                        } else rowZ = to6r;
                    } else rowZ = to3r;
                    //确定列的z
                    if (java.util.Arrays.binarySearch(to3c, Grid.Z) < 0) {
                        if (java.util.Arrays.binarySearch(to6c, Grid.Z) < 0) {
                            colZ = to9c;
                        } else colZ = to6c;
                    } else colZ = to3c;
                    //是否有重复
                    boolean judge = false;
                    //提示语
                    String remind = "行";
                    String output = String.valueOf(finalI1 + 1);
                    //判断行内重复
                    for (int i = 0; i < 3; i++) {
                        for (int yCheck = 0; yCheck < 3; yCheck++) {
                            if (Grid.txtGame[rowZ[i]][Grid.X][yCheck].getText().equals(output)) {
                                judge = true;
                            }
                        }
                    }
                    //判断列内重复
                    if (judge == false) {
                        for (int i = 0; i < 3; i++) {
                            for (int xCheck = 0; xCheck < 3; xCheck++) {
                                if (Grid.txtGame[(colZ[i])][xCheck][Grid.Y].getText().equals(output)) {
                                    judge = true;
                                    remind = "列";
                                }
                            }
                        }
                    }
                    //判断宫内重复
                    if (judge == false) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (Grid.txtGame[(Grid.Z)][i][j].getText().equals(output)) {
                                    judge = true;
                                    remind = "九宫格";
                                }
                            }
                        }
                    }
                    if (judge == false) {
                        Grid.txtGame[(Grid.Z)][Grid.X][Grid.Y].setText(String.valueOf(finalI1 + 1));
                        //判断是否填满
                        filled=true;
                        for(int a=0;a<9;a++){
                            for(int b=0;b<3;b++){
                                for(int c=0;c<3&&filled;c++){
                                    if(Grid.txtGame[a][b][c].getText().equals("")){
                                        filled=false;
                                    }
                                }
                            }
                        }
                        //填满则关闭输入并弹窗
                        if(filled==true){
                            Window.changeState(false);
                            JOptionPane.showMessageDialog(null,"恭喜，本轮游戏胜利！");
                        }
                    }
                    //判断有重复弹窗提示
                    else {
                        //当前为填满才有弹窗提示
                        if (filled == false) {
                            JOptionPane.showMessageDialog(null, "您填入的数字与所在" + remind + "其他数字重复，请重填！", "提示", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            });
       }
   }
}
