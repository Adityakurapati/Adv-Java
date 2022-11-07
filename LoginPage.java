import javax.jws.Oneway;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
class LoginPage extends JFrame{
    JTextField name,email,password;
    JPanel one;
    LoginPage(){
        ImageIcon i = new ImageIcon("D:/MY USERS/Aditya/IF5I/Adv Java/Adv_Java_V/Unit 5/LOGIN.jpg");
        setVisible(true);
        
        setContentPane(new JLabel(new ImageIcon("D:/MY USERS/Aditya/IF5I/Adv Java/Adv_Java_V/Unit 5/LOGIN.jpg")));
        setLayout(new BorderLayout(50,50));
        setSize(i.getIconWidth(),i.getIconHeight());
        setTitle("Login/Sign In");

        one = new JPanel();
        one.setForeground(Color.white);
        JLabel l = new JLabel(i);
        one.add(l);
        add(one);
        JPanel two = new JPanel();
        add(two);
        two.setVisible(false);
        JLabel nametxt = new JLabel("UserName");
        name = new JTextField();
        JLabel mailtxt = new JLabel("Email");
        email = new JTextField();
        JLabel passtxt = new JLabel("Password");
        password = new JTextField();
        
        JButton signup = new JButton(new ImageIcon("loginBtn.png"));
        one.setLayout(null);
        one.add(nametxt);one.add(mailtxt);one.add(passtxt);
        one.add(name);one.add(email);one.add(password);one.add(signup);
        nametxt.setBounds(200,180,40,10);
        name.setBounds(650,200,220,30);
        email.setBounds(650,250,220,30);
        password.setBounds(650,300,220,30);
        signup.setBounds(700,350,100,50);
        signup.setPressedIcon(new ImageIcon("loading.png"));
        signup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // if(name.getText().equals("Admin") && password.getText().equals("Login")){
                //     email.setText("Welcome "+name.getText());
                // }else{
                //     email.setText("Login Failed ");
                // }
                try {
                    dataBase();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        // JButton login = new JButton("Sign In");
        // one.add(login);
        // login.setBounds(700,480,100,50);
        // login.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent e){
        //         two.setVisible(true);one.setVisible(false);
        //     }
        // });
        // JButton login = new JButton("Login");
    }
    public static void main(String a[]){
        new LoginPage();
    }

    void dataBase() throws ClassNotFoundException, SQLException{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String url="jdbc:ucanaccess://D:/MY USERS/Aditya/IF5I/Adv Java/Adv_Java_V/Unit 5/Aditya1.accdb";
        Connection con = DriverManager.getConnection(url);
        Statement s =con.createStatement();
        ResultSet rs = s.executeQuery("select * from Table1");
        while(rs.next()){
            String v1 = rs.getString(1);
            String v2 = rs.getString(2);
            // if(!( (name.getText().equals("")) && (password.getText().equals(""))  )){
            if(name.getText().length()>0 && password.getText().length()>0){
                if(v1.equals(name.getText()) && v2.equals(password.getText())){
                    System.out.println(v1+"     "+v2);
                    email.setText("Login Success ....");
                }else{
                    email.setText("Login Failed ....");
                    
                }
            }else{
                if(name.getText().equals("")) email.setText("Enter Your Name");
                if(password.getText().equals("")) email.setText("Enter Your Name");
            }
        }
        

    }
}