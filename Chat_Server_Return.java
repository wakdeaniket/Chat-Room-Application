package chat_Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Chat_Server_Return implements Runnable {
	
	Socket Sock;
	private Scanner INPUT;
	private PrintWriter OUT;
	String Msg=" ";
	
	public Chat_Server_Return(Socket X) {
		this.Sock=X;
	}
	
	public void CheckConnection() throws IOException{
		if(!Sock.isConnected()) {
			for(int i=1;i<=Chat_Server.ConnectionArray.size();i++) {
				if(Chat_Server.ConnectionArray.get(i)==Sock) {
					Chat_Server.ConnectionArray.remove(i);
				}
				
			}
			for(int i=1;i<=Chat_Server.ConnectionArray.size();i++) {
				Socket Temp_Sock =(Socket) Chat_Server.ConnectionArray.get(i-1);
				PrintWriter Temp_Out=new PrintWriter(Temp_Sock.getOutputStream());
				Temp_Out.println(Temp_Sock.getLocalAddress().getHostName()+" Disconnected");
				System.out.println(Temp_Sock.getLocalAddress().getHostName()+" Disconnected");
			}
		}
	}
	
	public void run() {
		try {
			try {
					INPUT=new Scanner(Sock.getInputStream());
					OUT=new PrintWriter (Sock.getOutputStream());
					while(true)
					{
						CheckConnection();
						if(!INPUT.hasNext())
						{ return;}
						
						Msg=INPUT.nextLine();
						System.out.println("Client says"+ Msg);
						
						for(int i=1;i<=Chat_Server.ConnectionArray.size();i++) {
							Socket Temp_Sock =(Socket) Chat_Server.ConnectionArray.get(i-1);
							PrintWriter Temp_Out=new PrintWriter(Temp_Sock.getOutputStream());
							Temp_Out.println(Msg);
							Temp_Out.flush();
							System.out.println("send to:"+Temp_Sock.getLocalAddress().getHostName());
						}
					}
			}
			finally {
				Sock.close();
			}
		}
		catch(Exception e) {}
		
						
					}
			
		}
	

