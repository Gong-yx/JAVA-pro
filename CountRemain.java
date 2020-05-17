import javax.swing.*;
import java.awt.*;

public class CountRemain extends JPanel {

    private JLabel sentence;
    public static JTextField countRemains;
    public int count;

    //布局剩余提示次数在页面下方
    public CountRemain(){
        sentence=new JLabel("剩余提示次数:");
        countRemains=new JTextField();
        count=ToolButtons.getCount();
        countRemains.setText(String.valueOf(count));
        setLayout(new GridLayout(1,2));
        add(sentence);
        add(countRemains);
        countRemains.setEditable(false);
    }
    //点击提示按钮，对应剩余提示次数减，页面上的显示改变
    public static void setText(int count) {
        countRemains.setText(String.valueOf(count));
    }

}
