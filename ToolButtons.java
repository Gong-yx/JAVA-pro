import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ToolButtons extends JPanel {
    //设置右侧工具栏除数字九宫格外的所有按钮及其功能
    private JButton Repeal;
    private JButton Delete;
    private JButton Tips;
    private JButton Quit;
    public static int count=10;
    private JButton Record;



    public ToolButtons(){
        //设置撤销按钮
        Repeal = new JButton("撤销");
        //设置擦除按钮
        Delete = new JButton("擦除");
        //设置提示按钮
        Tips = new JButton("提示");
        //设置退出按钮
        Quit = new JButton("退出");
        //
        Record=new JButton("保存");
        setLayout(new GridLayout(5,1));
        add(Tips);
        Tips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnTips();
            }
        });


        add(Delete);
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grid.txtGame[Grid.Z][Grid.X][Grid.Y].setText("");
            }
        });

        //添加撤销按钮到面板并设置其功能
        add(Repeal);
        Repeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(Record);
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
}
