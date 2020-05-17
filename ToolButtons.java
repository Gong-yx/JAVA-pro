import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ToolButtons extends JPanel {
    //设置右侧工具栏除数字九宫格外的所有按钮及其功能
    private JButton Repeal;
    private JButton Delete;
    private JButton Tips;
    private JButton Quit;
    public static int count=0;
    private JButton Record;
    private JButton Save;
    private JButton Load;



    public ToolButtons(){
        //设置继续游戏按钮
        Load = new JButton("继续游戏");
        //设置撤销按钮
        Repeal = new JButton("撤销");
        //设置擦除按钮
        Delete = new JButton("擦除");
        //设置提示按钮
        Tips = new JButton("提示");
        //设置退出按钮
        Quit = new JButton("退出");
        //设置保存按钮
        Save = new JButton("保存");
        setLayout(new GridLayout(3,2));
        add(Tips);
        Tips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnTips();
            }
        });

       //加入继续游戏按钮并设置为读取上一次存档
        add(Load);
        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int z=0;z<9;z++){
                    for (int x=0;x<3;x++){
                        for (int y=0;y<3;y++){
                            Grid.txtGame[z][x][y].setText("");
                        }
                    }
                }
                try {
                    readFromSave();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //设置擦除按钮
        add(Delete);
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断是否可以进行擦除
                if (Grid.txtGame[Grid.Z][Grid.X][Grid.Y].isEditable()) {
                    //记录最后一次擦除
                    Tools.lastNum.add(Grid.txtGame[Grid.Z][Grid.X][Grid.Y].getText());
                    Tools.lastX.add(Grid.X);
                    Tools.lastY.add(Grid.Y);
                    Tools.lastZ.add(Grid.Z);
                    //进行擦除
                    Grid.txtGame[Grid.Z][Grid.X][Grid.Y].setText("");
                }
            }
        });

        //添加撤销按钮到面板并设置其功能
        add(Repeal);
        Repeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Tools.lastX.size()>0) {
                    //执行记录的最后一步操作
                    Grid.txtGame[Tools.lastZ.get(Tools.lastZ.size() - 1)][Tools.lastX.get(Tools.lastX.size() - 1)][Tools.lastY.get(Tools.lastY.size() - 1)].setText(Tools.lastNum.get(Tools.lastNum.size() - 1));
                    //移除已经撤销的操作记录
                    Tools.lastX.remove(Tools.lastX.size() - 1);
                    Tools.lastY.remove(Tools.lastY.size() - 1);
                    Tools.lastZ.remove(Tools.lastZ.size() - 1);
                    Tools.lastNum.remove(Tools.lastNum.size() - 1);
                }
            }
        });

        //添加保存按钮到面板并设置其功能
        add(Save);
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,"当前游戏进度已保存，下次返回游戏后点击’继续游戏‘即可继续本轮游戏。");
                    saveToFile();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //添加退出按钮到面板并设置其功能
        add(Quit);

        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnQuit();
            }
        });
    }
    //设置答案提示框的功能
    public void OnTips(){
        //每次点击提示按钮提示次数都会减一
        this.count--;
        //当还有提示次数时，系统可在对应空格中填上答案
        if(count>=0) {
            CountRemain.setText(count);
            int site=Tools.Question[Grid.Z][Grid.X][Grid.Y];
            if(site<0) {
                site=-1*site;
            }
            NumButtons.WheterOutput(site-1,"提示","请仔细检查并更改您当前答案以完成本轮游戏。");
        }
        //没有提示次数时，会有弹窗警告
        if(count<0){
            JOptionPane.showMessageDialog(this,"对不起，您的剩余提示次数为0");
        }
    }

    public static int getCount(){
        return count;
    }

    public void OnQuit(){
        //消息提示框
        int result = JOptionPane.showConfirmDialog(this,"这位客官，您确定要离开游戏吗？","Confirm",JOptionPane.YES_NO_OPTION);
        //确认是否退出
        if (result == JOptionPane.NO_OPTION)
            return;
        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    public void saveToFile() throws FileNotFoundException {
        //建立新的存档文件
        PrintWriter out = new PrintWriter("Save.txt");
        out.println(Tools.fileName);
        for (int z=0;z<9;z++){
            for (int x=0;x<3;x++){
                for (int y=0;y<3;y++){
                    if (Grid.txtGame[z][x][y].isEditable())
                        if (Grid.txtGame[z][x][y].getText().equals(""))
                            out.print(" "+",");
                        else
                            out.print(Grid.txtGame[z][x][y].getText()+",");
                    else
                        out.print("-"+Grid.txtGame[z][x][y].getText()+",");
                }
                out.println();
            }
        }
        //记录保存时游戏可用提示次数
        out.println(count);
        //记录可用的撤销记录
        for (int i=0;i<Tools.lastX.size();i++){
            out.print(Tools.lastX.get(i)+" ");
        }
        out.println();
        for (int i=0;i<Tools.lastY.size();i++){
            out.print(Tools.lastY.get(i)+" ");
        }
        out.println();
        for (int i=0;i<Tools.lastZ.size();i++){
            out.print(Tools.lastZ.get(i)+" ");
        }
        out.println();
        for (int i=0;i<Tools.lastNum.size();i++){
            out.println(Tools.lastNum.get(i));
        }
        out.flush();
    }

    public void readFromSave() throws FileNotFoundException {
        Window.changeState(true);
        Scanner in = new Scanner(new File("Save.txt"));
        //读取上一次文件
        String file = in.nextLine();
        //读取并输出保存时棋盘状态
        for (int z=0;z<9;z++){
            for (int x=0;x<3;x++){
                String str = in.nextLine();
                String[] s = str.split(",");
                for (int y=0;y<3;y++){
                    if (!s[y].equals(" ")) {
                        int Num = Integer.parseInt(s[y]);
                        if (Num < 0) {
                            Grid.txtGame[z][x][y].setText(-1 * Num + "");
                            Grid.txtGame[z][x][y].setForeground(Color.GRAY);
                            Grid.txtGame[z][x][y].setEditable(false);
                        } else
                            Grid.txtGame[z][x][y].setText(s[y]);
                    }
                }
            }
        }
        //恢复显示提示次数
        count = in.nextInt();
        CountRemain.setText(count);
        in.nextLine();
        //恢复撤销记录
        String str1 = in.nextLine();
        String[] s1 = str1.split(" ");
        for (int i=0;i<s1.length;i++){
            Tools.lastX.add(Integer.parseInt(s1[i]));
        }
        String str2 = in.nextLine();
        String[] s2 = str2.split(" ");
        for (int i=0;i<s2.length;i++){
            Tools.lastY.add(Integer.parseInt(s2[i]));
        }
        String str3 = in.nextLine();
        String[] s3 = str3.split(" ");
        for (int i=0;i<s3.length;i++){
            Tools.lastZ.add(Integer.parseInt(s3[i]));
        }
        for (int i=0;i<s1.length;i++){
            Tools.lastNum.add(in.nextLine());
        }
        in.close();
        Scanner input = new Scanner(new File(file));
        for (int z=0;z<9;z++) {
            for (int x = 0; x < 3; x++) {
                String s = input.nextLine();
                String[] ss = s.split(",");
                for (int y = 0; y < 3; y++) {
                    Tools.Question[z][x][y] = Integer.parseInt(ss[y]);
                }
            }
        }
        input.close();
    }
}
