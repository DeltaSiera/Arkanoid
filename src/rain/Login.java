package rain;

import rain.Graphics.Sprite;
import rain.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener {
    private File file =
            new File("/home/deividas/IdeaProjects/Arkanoid/res.txt");
    private PrintWriter writer;
    {
        try {
            writer = new PrintWriter("res.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Player> playerList = new LinkedList<>();
    private int score = 0;

    private String nickName;
    private JLabel jLabelHello = new JLabel("Hello. This is an Arkanoid game");
    private JLabel jLabellLogin = new JLabel("Enter your nickname: "),
                    scoreLabel;
    private JTextField jTextField = new JTextField(25);
    private Game arkanoidGame;
    private JPanel allCards;
    private JPanel menuPanel,
                    gamePanel, loginPanel;
    private JButton startButton,
                    againButton,
                    stopButton,loginButton,startGameButton,backToMenu,submitScore;
    private Container container = getContentPane();
    private CardLayout cardLayout;
    public void setScore(int i){
        score =i;
        if(scoreLabel!= null) {
            scoreLabel.setText(Integer.toString(score));
        }
    }


    public Login(){
        readRes();
        submitScore = new JButton("Submit Score");
        submitScore.addActionListener(this);
        backToMenu = new JButton("Back to menu");
        backToMenu.addActionListener(this);
        backToMenu.setBounds(new Rectangle(700,60,150,30));
        jLabellLogin.setBounds(new Rectangle(0,0,170,40));
        jLabellLogin.setForeground(Color.blue);
        jLabelHello.setForeground(Color.blue);
        jLabelHello.setFont(new Font("Serif", Font.ITALIC, 16));


        //-------------------------------------------------
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.BLACK);
        jTextField.setBounds(new Rectangle(jLabellLogin.getWidth()+10,jLabellLogin.getY()+5,200,30));
        loginPanel.add(jTextField);
        loginPanel.add(jLabellLogin);
        loginPanel.setLayout(null);
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.black);
        jLabelHello.setBounds(new Rectangle(75,0,300,40));
        startGameButton = new JButton("Start game");
        startGameButton.setBounds(new Rectangle(jLabellLogin.getX()+120,jLabellLogin.getHeight(),150,30));
        startGameButton.addActionListener(this);
        loginPanel.add(startGameButton);
        menuPanel.add(jLabelHello);
        startButton = new JButton("Start");
        startButton.setLayout(null);
        startButton.setBounds(150,150,100,40);
        startButton.addActionListener(this);

        loginButton = new JButton("Sign up");
        loginButton.setBounds(150,100,100,40);
        loginButton.addActionListener(this);
        menuPanel.add(loginButton);
        menuPanel.add(startButton);



        allCards = new JPanel(new java.awt.CardLayout());
        allCards.setBackground(Color.BLACK);
        makeGamePanel();
        allCards.add("MenuPanel",menuPanel);
        allCards.add("GamePanel", gamePanel);
        allCards.add("Login", loginPanel);
        container.add(allCards);
        setSize(new Dimension(400,400));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayout = (CardLayout) allCards.getLayout();
        this.setTitle("Arkanoid game");
        setFrameLocation();
    }
    private void readRes(){
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String name;
            int score;
            Scanner sc = new Scanner(inFile);
            while (sc.hasNextLine()){
                name = sc.next();
                score = sc.nextInt();
                playerList.add(new Player(name,score));
            }
            inFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerList.forEach(System.out::println);
    }
    private void makeGamePanel(){
        JLabel currectScore = new JLabel("Your score: ");
        currectScore.setFont(new Font("Serif",Font.ITALIC,20));
        currectScore.setForeground(Color.green);
        currectScore.setBounds(new Rectangle(700,120,150,30));
        arkanoidGame = new Game();
        scoreLabel = new JLabel();
        scoreLabel.setText(Integer.toString(score));
        Sprite.fillHashmap();
        againButton = new JButton("Again");
        gamePanel = new JPanel(null);
        stopButton = new JButton("Stop");
        //stopButton.setVerticalAlignment(SwingConstants.CENTER);
        stopButton.setBounds(700,0,150,30);

        submitScore.setBounds(new Rectangle(700,90,150,30));
        scoreLabel.setBounds(new Rectangle(700,150,100,30));
        scoreLabel.setFont(new Font("Serif", Font.ITALIC, 22));
        scoreLabel.setForeground(Color.green);
        arkanoidGame.setBounds(0,0,700,950);
        stopButton.addActionListener(this);
        gamePanel.add(currectScore);
        gamePanel.add(scoreLabel);
        gamePanel.add(stopButton);
        gamePanel.add(againButton);
        gamePanel.add(backToMenu);
        gamePanel.add(submitScore);
        //againButton.setHorizontalAlignment(SwingConstants.LEFT);
        againButton.setBounds(700,30,150,30);
        againButton.addActionListener(this);
        gamePanel.add("Arkanoid", arkanoidGame);
        allCards.add("GamePanel", gamePanel);
        gamePanel.setBackground(Color.darkGray);
        arkanoidGame.setLogin(this);
    }
    private void setFrameLocation(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nickName = jTextField.getText();
        if(e.getSource() == startButton){
            makeGamePanel();
            cardLayout.show(allCards, "GamePanel");
            arkanoidGame.start();
            this.setSize(900,970);

        }else if(e.getSource() == stopButton){
            arkanoidGame.stop();
            Level.score = 0;
            //arkanoidGame.stop();
            allCards.remove(gamePanel);
        }else if(e.getSource() == againButton){

           // allCards.remove(gamePanel);
            makeGamePanel();
            cardLayout.show(allCards, "GamePanel");
            arkanoidGame.start();

        }else if(e.getSource() == loginButton){
            cardLayout.show(allCards, "Login");
        }else if(e.getSource() == startGameButton){
            makeGamePanel();
            cardLayout.show(allCards, "GamePanel");
            this.setSize(900,970);
            arkanoidGame.start();
        }else if(e.getSource() == backToMenu){
            Level.score = 0;
            //arkanoidGame.stop();
            allCards.remove(gamePanel);
            for (Player player : playerList) {
                System.out.println(player);
                writer.println(player.toString());
            }
            writer.close();
            cardLayout.show(allCards, "MenuPanel");
            setSize(400,400);
            setFrameLocation();

        }else if(e.getSource() == submitScore){
            playerList.add(new Player(nickName,score));
        }
        else{
            cardLayout.next(container);
        }
    }



}