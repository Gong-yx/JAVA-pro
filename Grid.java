import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private JTextField[][][] txtGame;
    private JPanel[] pnlGame;

    public Grid(){
        pnlGame = new JPanel[9];
        txtGame = new JTextField[9][3][3];
        setLayout(new GridLayout(3,3));
        for (int i=0;i<9;i++){
            pnlGame[i] = new JPanel();
            pnlGame[i].setBorder(BorderFactory.createLineBorder(Color.black));
            pnlGame[i].setLayout(new GridLayout(3,3));
            this.add(pnlGame[i]);
        }
        for (int z=0;z<9;z++){
            for (int x=0;x<3;x++){
                for (int y=0;y<3;y++){
                    txtGame[z][x][y] = new JTextField();
                    txtGame[z][x][y].setBorder(BorderFactory.createEtchedBorder());
                    txtGame[z][x][y].setFont(new Font("Dialog",Font.ITALIC,20));
                    txtGame[z][x][y].setHorizontalAlignment(JTextField.CENTER);
                    pnlGame[z].add(txtGame[z][x][y]);
                }
            }
        }
    }
}
