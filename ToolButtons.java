import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JButton Save;
    private JButton Load;
    public static int count=10;

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
        //
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
                //记录最后一次擦除
                Tools.lastNum.add(Grid.txtGame[Grid.Z][Grid.X][Grid.Y].getText());
                Tools.lastX.add(Grid.X);
                Tools.lastY.add(Grid.Y);
                Tools.lastZ.add(Grid.Z);
                //进行擦除
                Grid.txtGame[Grid.Z][Grid.X][Grid.Y].setText("");
            }
        });

        //添加撤销按钮到面板并设置其功能
        add(Repeal);
        Repeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //执行记录的最后一步操作
                Grid.txtGame[Tools.lastZ.get(Tools.lastZ.size()-1)][Tools.lastX.get(Tools.lastX.size()-1)][Tools.lastY.get(Tools.lastY.size()-1)].setText(Tools.lastNum.get(Tools.lastNum.size()-1));
                //移除已经撤销的操作记录
                Tools.lastX.remove(Tools.lastX.size()-1);
                Tools.lastY.remove(Tools.lastY.size()-1);
                Tools.lastZ.remove(Tools.lastZ.size()-1);
                Tools.lastNum.remove(Tools.lastNum.size()-1);
            }
        });
        //添加保存按钮到面板并设置其功能
        add(Save);
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
        this.count--;
    }

    public static int getCount(){
        return count;
    }

    public void OnQuit(){
        //消息提示框
        int result = JOptionPane.showConfirmDialog(this,"这位客官，您确定不在留下提高一点智商？","Confirm",JOptionPane.YES_NO_OPTION);
        //确认是否退出
        if (result == JOptionPane.NO_OPTION)
            return;
        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void saveToFile() throws FileNotFoundException {
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
                            Grid.txtGame[z][x][y].setEditable(false);
                        } else
                            Grid.txtGame[z][x][y].setText(s[y]);
                    }
                }
            }
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
    }
}
