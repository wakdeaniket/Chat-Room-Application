package chat_Room;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.color.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.ActiveEvent;
public class Client_Gui {

		private static Chat_Client ChatClient;
		public static String UserName ="Anoymous";
		
		public static JFrame MainWindow =new JFrame();
		public static JButton B_CONNECT =new JButton();
		public static JButton B_DISCONNECT =new JButton();
		public static JButton B_SEND =new JButton();
        public static JLabel L_Messege =new JLabel("Messege: ");
        public static JTextField TF_Message=new JTextField(20);
        public static JLabel L_Conversation =new JLabel("Messege: ");
        public static JTextArea TA_Conversation=new JTextArea();
        public static JScrollPane SP_Conversation = new JScrollPane();
        public static JLabel L_Online =new JLabel();
        public static JList JL_Online =new JList();
        public static JScrollPane SP_Online = new JScrollPane();
        public static JLabel L_LoggedInAs =new JLabel();
        public static JLabel L_LoggedInAsBox =new JLabel();
        

        public static JFrame LogInWindow =new JFrame();
        public static JTextField TF_UserNameBox=new JTextField(20);
		public static JButton B_Enter =new JButton("ENTER");
        public static JLabel L_EnterUserName =new JLabel("ENTER USER NAME:");
        public static JPanel P_LogIn =new JPanel();
        
        
        public static void main(String Args[]) {
        	BuildMainWindow();
        	Initialize();
        }

        public static void Connect()
        {
        	try {
        			final int PORT=6666;
        			//final String HOST=InetAddress.getLocalHost();
        			Socket Sock=new Socket(InetAddress.getLocalHost(),PORT);
        			System.out.println("you connected to"+InetAddress.getLocalHost());
        			ChatClient=new Chat_Client(Sock);
        			PrintWriter OUT=new PrintWriter(Sock.getOutputStream());
        			OUT.println(UserName);
        			OUT.flush();
        			Thread X =new Thread(ChatClient);
        			X.start();
        	}
        	catch(Exception e) {
        		
        	}
        }
        
        public static void Initialize()
        {
        	B_SEND.setEnabled(false);
        	B_DISCONNECT.setEnabled(false);
        	B_CONNECT.setEnabled(true);

        }
        
        public static void BuildLogInWindow()
        {
        	LogInWindow.setTitle("what is your namee?");
        	LogInWindow.setSize(400,100);
        	LogInWindow.setLocation(250, 200);
        	LogInWindow.setResizable(false);
        	P_LogIn = new JPanel();
        	P_LogIn.add(L_EnterUserName);
        	P_LogIn.add(TF_UserNameBox);
        	P_LogIn.add(B_Enter);
        	LogInWindow.add(P_LogIn);
        	LogInAction();
        	LogInWindow.setVisible(true);
        }
        
        public static void BuildMainWindow()
        {
        	MainWindow.setTitle(UserName+"'s chat box");
        	MainWindow.setSize(500,320);
        	MainWindow.setLocation(220, 180);
        	MainWindow.setResizable(false);
        	ConfigureMainWindow();
        	MainWindow_Action();
        	MainWindow.setVisible(true);
        	
        }
        
        public static void ConfigureMainWindow()
        {
        	MainWindow.setBackground(new java.awt.Color(255,255,255));
        	MainWindow.setSize(500,320);
        	MainWindow.getContentPane().setLayout(null);

        	B_SEND.setBackground(new java.awt.Color(0,0,255));
        	B_SEND.setForeground(new java.awt.Color(255,255,255));
            B_SEND.setText("SEND");
        	MainWindow.getContentPane().add(B_SEND);
        	B_SEND.setBounds(250, 40, 81, 25);
        	
        	B_DISCONNECT.setBackground(new java.awt.Color(0,0,255));
        	B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
            B_DISCONNECT.setText("DISCONNECT");
        	MainWindow.getContentPane().add(B_DISCONNECT);
        	B_DISCONNECT.setBounds(10,40,110,25);
        	
        	B_CONNECT.setBackground(new java.awt.Color(0,0,255));
        	B_CONNECT.setForeground(new java.awt.Color(255,255,255));
            B_CONNECT.setText("CONNECT");
        	MainWindow.getContentPane().add(B_CONNECT);
        	B_CONNECT.setBounds(130, 40, 110, 25);
        	
        	L_Messege.setText("message:");
        	MainWindow.getContentPane().add(L_Messege);
            L_Messege.setBounds(10,10,60,20);
            
            TF_Message.setForeground(new java.awt.Color(255,255,255));
            TF_Message.requestFocus();
        	MainWindow.getContentPane().add(TF_Message);
            TF_Message.setBounds(70,4,260,30);
            
            L_Conversation.setText("Conersation");
            MainWindow.getContentPane().add(L_Conversation);
            L_Conversation.setBounds(100, 70, 140, 16);
            
            TA_Conversation.setColumns(20);
            TA_Conversation.setFont(new java.awt.Font("Tahoma",0,12));
            TA_Conversation.setForeground(new java.awt.Color(0,0,255));
            TA_Conversation.setLineWrap(true);
            TA_Conversation.setRows(5);
            TA_Conversation.setEditable(false);
            
            SP_Conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            SP_Conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            SP_Conversation.setViewportView(TA_Conversation);
            MainWindow.getContentPane().add(SP_Conversation);
            SP_Conversation.setBounds(10,90,330,180);
            
            L_Online.setHorizontalAlignment(SwingConstants.CENTER);
            L_Online.setText("Currently Online");
            L_Online.setToolTipText("");
            MainWindow.getContentPane().add(L_Online);
            L_Online.setBounds(350,70,130,16);
            
            //String [] TestNames = {"A","B","C","D"};
            JL_Online.setForeground(new java.awt.Color(0,0,255));
            //JL_Online.setListData(Chat_Client.CurrentUser);
            
            SP_Online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            SP_Online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            SP_Online.setViewportView(JL_Online);
            MainWindow.getContentPane().add(SP_Online);
            SP_Online.setBounds(350,90,130,180);
            
            L_LoggedInAs.setFont(new java.awt.Font("Tahoma",0,12));
            L_LoggedInAs.setText("Currently Logged In As");
            MainWindow.getContentPane().add(L_LoggedInAs);
            L_LoggedInAs.setBounds(348,0,140,15);
            
            L_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
            L_LoggedInAsBox.setFont(new java.awt.Font("Tahoma",0,12));
            L_LoggedInAsBox.setForeground(new java.awt.Color(255,0,0));
            L_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
            MainWindow.getContentPane().add(L_LoggedInAsBox);
            L_LoggedInAsBox.setBounds(340,17,150,20);
        }
        
        public static void LogInAction()
        {
        	B_Enter.addActionListener(
        		new java.awt.event.ActionListener() {
        			public void actionPerformed(java.awt.event.ActionEvent evt) {
        				ACTION_B_ENTER();
        			}
        		}
        	);
        }
        
        public static void ACTION_B_ENTER() {
        	if(!TF_UserNameBox.getText().equals("")) {
        		UserName= TF_UserNameBox.getText().trim();
        		L_LoggedInAsBox.setText(UserName);
        		Chat_Server.CurrentUser.add(UserName);
        		MainWindow.setTitle(UserName+"'s chat room");
        		LogInWindow.setVisible(false);
        		B_SEND.setEnabled(true);
            	B_DISCONNECT.setEnabled(true);
            	B_CONNECT.setEnabled(false);
        		Connect();
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null,"please Enter Name");
        	}
        }
        
        public static void MainWindow_Action()
        {

        	B_SEND.addActionListener(
        		new java.awt.event.ActionListener() {
        			public void actionPerformed(java.awt.event.ActionEvent evt) {
        				ACTION_B_SEND();
        			}
        		}
        	);
        	
        	B_DISCONNECT.addActionListener(
        		new java.awt.event.ActionListener() {
        			public void actionPerformed(java.awt.event.ActionEvent evt) {
        				ACTION_B_DISCONNECT();
        			}
        		}
        	);
        	
        	B_CONNECT.addActionListener(
        		new java.awt.event.ActionListener() {
        			public void actionPerformed(java.awt.event.ActionEvent evt) {
        				BuildLogInWindow();
        			}
        		}
        		
        		);
        }
        
        public static void ACTION_B_SEND(){
        	
        	if(!TF_Message.getText().equals(""))
        	{
        		ChatClient.Send(TF_Message.getText());
        		TF_Message.requestFocus();
        	}
        }
        
        public static void ACTION_B_DISCONNECT()
        {
        	
        	try 
        	{
        		ChatClient.DISCONNECT();
        	}
        	catch(Exception Y) {Y.printStackTrace();}
        }
}