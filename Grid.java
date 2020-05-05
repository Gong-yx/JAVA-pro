import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid extends JPanel {
    //设置9*9宫格
    public static JTextField[][][] txtGame;
    private static JPanel[] pnlGame;
    //记录点击的格子位置
    public static int X;
    public static int Y;
    public static int Z;


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
                    pnlGame[z].add(txtGame[z][x][y]);
                    txtGame[z][x][y].setEditable(false);
                    //得到当前点击的格子位置
                    int finalZ = z;
                    int finalY = y;
                    int finalX = x;
                    txtGame[z][x][y].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            X= finalX;
                            Y= finalY;
                            Z= finalZ;
                        }

                    });
                }
            }
        }
    }

    public void changeGrid(boolean b) {
        for (int z = 0; z < 9; z++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    txtGame[z][x][y].setEditable(b);
                }
            }
        }
    }
}
