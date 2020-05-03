import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumButtons extends JPanel {
    private JButton[] Nums;
    //设置右侧工具栏九宫格
    public NumButtons(){
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
                    Grid.txtGame[Grid.Z][Grid.X][Grid.Y].setText(String.valueOf(finalI1 +1));
                }
            });
        }
    }
}
