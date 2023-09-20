import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConnectFour extends JFrame {
    public static int counter = 0;
    Color[] setOfColors = {Color.lightGray,Color.GRAY};
    public void initComponents(JPanel panel1,JButton[][] buttonGrid) {
        int colorCounter = 0;
        for (int j = 6; j >= 1; j--) {
            for (char i = 'A'; i < 'H'; i++) {
                buttonGrid[j-1][i-65] = createButton(i,j,buttonGrid, panel1);
                buttonGrid[j-1][i-65].setFont(new Font("Arial", Font.PLAIN, 40));
                buttonGrid[j-1][i-65].setBackground(setOfColors[colorCounter%2]);
                colorCounter++;
            }
        }
        setVisible(true);
    }
    private JButton createButton(char i, int j, JButton[][] buttonGrid, JPanel panel1) {
        JButton button = new JButton(" ");
        button.setName("Button" + String.valueOf(i) + String.valueOf(j));
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                makeAMove(i,buttonGrid);
            }
        });
        panel1.add(button);
        return button;
    }
    public void blockButtons(JButton[][] buttonGrid){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttonGrid[i][j].setEnabled(false);
            }
        }
    }
    public void makeAMove(char i,JButton[][] buttonGrid) {
        for (int j = 0; j < 6; j++) {
            if(buttonGrid[j][i - 65].getText().equals(" ") && counter % 2 == 0){
                buttonGrid[j][i-65].setText("X");
                winCheck(j,i-65, buttonGrid);
                counter++;
                break;
            } else if (buttonGrid[j][i - 65].getText().equals(" ") && counter % 2 == 1) {
                buttonGrid[j][i-65].setText("O");
                winCheck(j,i-65, buttonGrid);
                counter++;
                break;
            }
        }
    }
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(612, 570);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setTitle("Connect Four");
        JButton[][] buttonGrid = new JButton[6][7];
        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,600,500);
        panel1.setLayout(new GridLayout(6, 7, 0, 0));
        initComponents(panel1,buttonGrid);
        add(panel1);
        panel1.setVisible(true);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0,500,600,50);
        JButton ButtonReset = new JButton("Reset");
        ButtonReset.setName("ButtonReset");
        ButtonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanGrid();
                counter = 0;
            }
            private void cleanGrid() {
                int colorCounter = 0;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        buttonGrid[i][j].setText(" ");
                        buttonGrid[i][j].setBackground(setOfColors[colorCounter%2]);
                        colorCounter++;
                        buttonGrid[i][j].setEnabled(true);
                    }
                }
            }
        });
        panel2.add(ButtonReset);
        add(panel2);
        ButtonReset.setVisible(true);
    }
    private void winCheck(int i, int j, JButton[][] buttonGrid) {
        //horizontal
        int hCounter = 1;
        int k = 1;
        while(j-k >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i][j-k].getText())){
            k++;
            hCounter++;
        }
        k = 1;
        while(j+k < 7 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i][j+k].getText())){
            k++;
            hCounter++;
        }
        if(hCounter >= 4){
            blockButtons(buttonGrid);
            buttonGrid[i][j].setBackground(Color.blue);
            int m = 1;
            while(j-m >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i][j-m].getText())){
                buttonGrid[i][j-m].setBackground(Color.blue);
                m++;
            }
            m = 1;
            while(j+m < 7 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i][j+m].getText())){
                buttonGrid[i][j+m].setBackground(Color.blue);
                m++;
            }
        }
        //vertical
        int vCounter = 1;
        k = 1;
        while(i-k >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-k][j].getText())){
            k++;
            vCounter++;
        }
        if(vCounter >= 4){
            blockButtons(buttonGrid);
            buttonGrid[i][j].setBackground(Color.blue);
            int m = 1;
            while(i-m >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-m][j].getText())){
                buttonGrid[i-m][j].setBackground(Color.blue);
                m++;
            }
            m = 1;
            while(i+m < 6 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i+m][j].getText())){
                buttonGrid[i+m][j].setBackground(Color.blue);
                m++;
            }
        }
        //ldiagonal
        int ldCounter = 1;
        k = 1;
        while(i-k >= 0 && j-k >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-k][j-k].getText())){
            k++;
            ldCounter++;
        }
        k = 1;
        while(i+k <= 5 && j+k <= 6 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i+k][j+k].getText())){
            k++;
            ldCounter++;
        }
        if(ldCounter >= 4){
            blockButtons(buttonGrid);
            buttonGrid[i][j].setBackground(Color.blue);
            int m = 1;
            while(i-m >= 0 && j-m >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-m][j-m].getText())){
                buttonGrid[i-m][j-m].setBackground(Color.blue);
                m++;
            }
            m = 1;
            while(i+m <=5 && j+m <= 6 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i+m][j+m].getText())){
                buttonGrid[i+m][j+m].setBackground(Color.blue);
                m++;
            }
        }
        //rdiagonal
        int rdCounter = 1;
        k = 1;
        while(i+k <= 5 && j-k >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i+k][j-k].getText())){
            k++;
            rdCounter++;
        }
        k = 1;
        while(i-k >= 0 && j+k <= 6 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-k][j+k].getText())){
            k++;
            rdCounter++;
        }
        if(rdCounter >= 4){
            blockButtons(buttonGrid);
            buttonGrid[i][j].setBackground(Color.blue);
            int m = 1;
            while(i-m >= 0 && j+m <= 6 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i-m][j+m].getText())){
                buttonGrid[i-m][j+m].setBackground(Color.blue);
                m++;
            }
            m = 1;
            while(i+m <=5 && j-m >= 0 && Objects.equals(buttonGrid[i][j].getText(), buttonGrid[i+m][j-m].getText())){
                buttonGrid[i+m][j-m].setBackground(Color.blue);
                m++;
            }
        }
    }
}
