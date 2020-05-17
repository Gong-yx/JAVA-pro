import javax.swing.*;
import java.awt.*;

public class Attention extends JPanel {
    private CountRemain countRemains;
    private JLabel time;

    public Attention() {
        addCounts();
        addCodes();
    }

    public void addCounts(){
        countRemains=new CountRemain();
        this.add(countRemains,BorderLayout.WEST);
    }

    public void addCodes(){
        time=new JLabel("!");
        this.add(time,BorderLayout.EAST);
    }
}
