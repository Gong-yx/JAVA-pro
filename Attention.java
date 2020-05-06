import javax.swing.*;
import java.awt.*;

public class Attention extends JPanel {
    private CountRemain countRemains;
    private JLabel time;

    public Attention() {
        addCounts();
        addTimes();
    }

    public void addCounts(){
        countRemains=new CountRemain();
        this.add(countRemains,BorderLayout.WEST);
    }

    public void addTimes(){
        time=new JLabel("!");
        this.add(time,BorderLayout.EAST);
    }
}
