package controller;

import javax.swing.ImageIcon;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.employ.AdminManager;
import controller.member.LoginRegister;
import controller.member.UserMain;

import model.Member;
import model.Employ;
import javax.swing.JPasswordField;
import service.impl.MemberServiceImpl;
import service.impl.EmployServiceImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import util.Tool;
import java.awt.Color;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField username;
    private JPasswordField password;
    private JLabel timeLabel;

    private MemberServiceImpl memberService = new MemberServiceImpl();
    private EmployServiceImpl employService = new EmployServiceImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Login() {
        setTitle("登入介面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);


        ImageIcon icon = new ImageIcon(getClass().getResource("/ImageIcon/Login.png"));
        Image image = icon.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 450, 300);
        

        backgroundLabel.setLayout(null);

        
        JLabel nameLabel = new JLabel("帳號:");
        nameLabel.setBackground(new Color(0, 0, 0));
        nameLabel.setForeground(new Color(0, 0, 0));
        nameLabel.setFont(new Font("新細明體", Font.BOLD, 18));
        nameLabel.setBounds(103, 91, 46, 33);
        backgroundLabel.add(nameLabel);

        JLabel passwordLabel = new JLabel("密碼:");
        passwordLabel.setBackground(new Color(0, 0, 0));
        passwordLabel.setForeground(new Color(0, 0, 0));
        passwordLabel.setFont(new Font("新細明體", Font.BOLD, 18));
        passwordLabel.setBounds(103, 132, 66, 30);
        backgroundLabel.add(passwordLabel);

        username = new JTextField();
        username.setBounds(160, 98, 96, 21);
        backgroundLabel.add(username);
        username.setColumns(10);

        password = new JPasswordField();
        password.setBounds(160, 132, 96, 21);
        backgroundLabel.add(password);
        password.setColumns(10);

        
        timeLabel = new JLabel("時間載入中...");
        timeLabel.setForeground(new Color(0, 0, 0));
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        timeLabel.setBounds(256, 238, 229, 33);
        backgroundLabel.add(timeLabel);

        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                timeLabel.setText(Tool.getCurrentTime());
            }
        });
        timer.start();

        //Button
        JButton loginButton = new JButton("登入");
        loginButton.setFont(new Font("新細明體", Font.PLAIN, 20));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String usernameText = username.getText();
                String passwordText = new String(password.getPassword());

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "帳號或密碼不能為空！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Member loggedInMember = memberService.login(usernameText, passwordText);

                if (loggedInMember != null) {
                    JOptionPane.showMessageDialog(null, "登入成功！");
                    dispose();
                    
                    new UserMain(loggedInMember).setVisible(true);
                } else {
                    
                    Employ loggedInEmploy = employService.login(usernameText, passwordText);

                    if (loggedInEmploy != null) {
                        JOptionPane.showMessageDialog(null, "登入成功！");
                        dispose();
                        
                        new AdminManager(loggedInEmploy).setVisible(true);
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "帳號或密碼錯誤！", "登入失敗", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        loginButton.setBounds(280, 102, 87, 49);
        backgroundLabel.add(loginButton);

        JButton exitButton = new JButton("離開");
        exitButton.setFont(new Font("新細明體", Font.PLAIN, 18));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        exitButton.setBounds(217, 180, 87, 23);
        backgroundLabel.add(exitButton);

        JButton registerButton = new JButton("註冊");
        registerButton.setFont(new Font("新細明體", Font.PLAIN, 18));
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginRegister loginRegister = new LoginRegister();
                loginRegister.setVisible(true);
                dispose();
            }
        });
        registerButton.setBounds(91, 180, 87, 23);
        backgroundLabel.add(registerButton);
        
        
        contentPane.add(backgroundLabel);
    }
}