import javax.swing.*;
import java.awt.*;

public class Attention extends JPanel {
    private JLabel countRemains;
    private JLabel timeUsed;
    private int count;

    public Attention(){
        //显示剩余提示次数
        this.count=ToolButtons.getCount();
        String strOfCount="剩余提示次数："+String.valueOf(this.count);
        countRemains=new JLabel(strOfCount);
        //显示耗时
        timeUsed=new JLabel("time");
        //布局
        setLayout(new GridLayout(1,2));
        //添加
        add(countRemains);
        add(timeUsed);
    }

}
