package edu.ustc.stonepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener {
    int[][] data = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int row;
    int column;
    int count; //统计步数
    public MainFrame() {
        this.addKeyListener(this);
        initData();
        initFrame();
        paintView();
        setVisible(true);
    }

    /**
     * 打乱二维数组
     */
    public void initData() {
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int randomX = r.nextInt(4);
                int randomY = r.nextInt(4);
                int temp = data[i][j];
                data[i][j] = data[randomX][randomY];
                data[randomX][randomY] = temp;
            }
        }
        for(int i = 0; i< data.length; ++i) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] == 0) {
                    row = i;
                    column = j;
                }
            }
        }
    }

    /**
     * 此方法用于初始化窗体
     */
    public void initFrame(){
        //设置窗体大小
        setSize(514, 595);
        //设置窗体关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("石头迷阵单机版V1.0");
        //设置窗体在上层
        setAlwaysOnTop(true);
        //设置窗体居中
        setLocationRelativeTo(null);
        //取消窗体默认不住
        setLayout(null);
    }

    /**
     * 此方法用于绘制界面
     */
    public void paintView() {
        getContentPane().removeAll();

        if(victory()) {
            //加载胜利图片资源
            JLabel winLabel = new JLabel(new ImageIcon( "image\\win.png"));
            winLabel.setBounds(124, 230, 266, 88);
            getContentPane().add(winLabel);
        }

        JButton btn = new JButton("重新游戏");
        btn.setFocusable(false);
        btn.setBounds(350, 20, 100, 20);
        btn.addActionListener(e -> {
            count = 0;
            initData();
            paintView();
        });
        getContentPane().add(btn);
        
        JLabel scoreLabel = new JLabel("步数为：" + count);
        scoreLabel.setBounds(50, 20, 100, 20);
        getContentPane().add(scoreLabel);

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                JLabel imageLabel   = new JLabel(new ImageIcon( "image\\" + data[i][j] + ".png"));
                imageLabel.setBounds(50 + 100 * j, 90 + 100 * i, 100, 100);
                getContentPane().add(imageLabel);
            }
        }
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(26,30, 450, 484);
        getContentPane().add(background);
        //通知面板刷新
        getContentPane().repaint();
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        move(keyCode);
        //移动之后重新绘制界面，先清空
        paintView();
    }

    /**
     * 此方法用于键盘移动
     * @param keyCode
     */
    private void move(int keyCode) {
        if(victory()) {
            return;
        }

        if(keyCode == 37) { //left
            if(column == 3) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row][column + 1];
            data[row][column + 1] = temp;
            column++;
            count++;
        } else if(keyCode == 38) { //up
            if(row == 3) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row + 1][column];
            data[row + 1][column] = temp;
            row++;
            count++;
        } else if(keyCode == 39) { //right
            if(column == 0) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row][column - 1];
            data[row][column - 1] = temp;
            column--;
            count++;
        } else if(keyCode == 40) { // down
            if(row == 0) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row - 1][column];
            data[row - 1][column] = temp;
            row--;
            count++;
        } else if(keyCode == 90) {
            data = new int[][] {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
