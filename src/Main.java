import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
//***********************************************************
class GameFrame extends JFrame {
    GamePanel panel;

    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
//***********************************************************

class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension (GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Graphics graphics;
    Random random;
    Image image;
    Thread gameThread;
    Ball ball;
    Paddle paddle1;
    Paddle paddle2;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle((GAME_WIDTH - PADDLE_WIDTH), (GAME_WIDTH / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision() {
        if (ball.y <= 0) {
            ball.setYdirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYdirection(-ball.yVelocity);
        }
        //bounce ball off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //optional for more difficulty
            } else {
                ball.yVelocity--;
            }
            ball.setXdirection(ball.xVelocity);
            ball.setYdirection(ball.yVelocity);
        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //optional for more difficulty
            } else {
                ball.yVelocity--;
            }

            ball.setXdirection(-ball.xVelocity);
            ball.setYdirection(ball.yVelocity);
        }

        if (ball.x <= 0){
            score.player2++;
            newBall();
            newPaddles();
            System.out.println("player2 : " + score.player2);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newBall();
            newPaddles();
            System.out.println("player1 : " + score.player1);

        }
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }


    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
//***********************************************************
class Paddle extends Rectangle { //player controller
    int id;
    int yVelocity;
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) //constructor
    {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYdirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYdirection(speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYdirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYdirection(speed);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYdirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYdirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYdirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYdirection(0);
                }
                break;
        }
    }

    public void setYdirection(int randomYdirection) {
        yVelocity = randomYdirection;
    }

    public void move() {
        y += yVelocity;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.magenta);
        }
        g.fillRect(x, y, width, height);

    }
}
class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 1;
    Ball(int x,int y, int height, int width){

        super(x,y,height,width);
        random=new Random();
        int randomXdirection = random.nextInt(2);
        if(randomXdirection==0){
            randomXdirection--;
        }

        setXdirection(randomXdirection*initialSpeed);

        int randomYdirection= random.nextInt(2);
        if(randomYdirection==0){
            randomYdirection--;
        }

        setYdirection(randomYdirection*initialSpeed);
    }

    public void setXdirection(int randomXdirection){
        xVelocity=randomXdirection;
    }
    public void setYdirection(int randomYdirection){
        yVelocity=randomYdirection;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(x, y,height,width);
    }
}

class Score extends Rectangle{
    static int GAME_WIDTH;
    static int HEIGHT_WIDTH;
    int player1,player2;
    Score(int GAME_WIDTH,int HEIGHT_WIDTH){
        Score.GAME_WIDTH=GAME_WIDTH;
        Score.HEIGHT_WIDTH=HEIGHT_WIDTH;

    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(GAME_WIDTH/2, 0,GAME_WIDTH/2,HEIGHT_WIDTH);
        g.drawString(String.valueOf(player1/10)+ String.valueOf(player1%10),(GAME_WIDTH/2)-85,50);
        g.drawString(String.valueOf(player2/10)+ String.valueOf(player2%10),(GAME_WIDTH/2)+20,50);

    }
}
public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
    }

}