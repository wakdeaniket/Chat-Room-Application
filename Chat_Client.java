package chat_Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

import javax.swing.JOptionPane;

public class Chat_Client implements Runnable {
	Socket Sock;
	Scanner INPUT;
	Scanner SEND =new Scanner(System.in);
	PrintWriter OUT;
	
	public Chat_Client (Socket X) {
		this.Sock=X;
	}
	
	public void run() {
		try {
			try {
				INPUT =new Scanner(Sock.getInputStream());
				OUT=new PrintWriter(Sock.getOutputStream());
				OUT.flush();
			    CheckStream();
			}
			finally {
				Sock.close();
			}
		}
		catch(Exception e) {}
	}
	
	public void DISCONNECT() throws IOException{
		OUT.println(Client_Gui.UserName+"has Disonnected");
		OUT.flush();
		Sock.close();
		JOptionPane.showMessageDialog(null, "You Disconnected");
		System.exit(0);
	}
	
	public void CheckStream()
	{
		while(true) {
			Receive();
		}
	}
	
	public void Receive() {
		if(INPUT.hasNext()) {
			String Msg=INPUT.nextLine();
			if(Msg.contains("#?!")) {
				String Temp1=Msg.substring(3);
				Temp1=Temp1.replace("[", " ");
				Temp1=Temp1.replace("]", " ");
                
				String[] CurrentUser=Temp1.split(", ");
				Client_Gui.JL_Online.setListData(CurrentUser);
			}
			else
			{
				Client_Gui.TA_Conversation.append(Msg+"\n");
			}
		}
	}
	
	public void Send(String X)
	{
		OUT.println(Client_Gui.UserName+" "+X);
		OUT.flush();
		Client_Gui.TF_Message.setText(" ");
	}
}
