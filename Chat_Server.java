package chat_Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class Chat_Server {
	
	public static ArrayList<Socket> ConnectionArray=new ArrayList<Socket>();
	public static ArrayList<String> CurrentUser=new ArrayList<String>();
	public static void main(String [] Args) throws IOException{
		
	
		try {
			final int PORT=6666;
			ServerSocket ServerSock =new ServerSocket(PORT);
			System.out.println("waiting for connections......");
			
			while(true) {
				Socket Sock=ServerSock.accept();
				ConnectionArray.add(Sock);
				System.out.println("client: "+ Sock.getLocalAddress().getHostName());
				
				AddUserName(Sock);
				Chat_Server_Return Chat = new Chat_Server_Return(Sock);
				Thread X=new Thread(Chat);
				X.start();
			}
	    }
		catch(Exception e) {
			
		}
	}
	
	public static void AddUserName(Socket X)throws IOException{
		Scanner INPUT=new Scanner(X.getInputStream());
		String UserName=INPUT.nextLine();
		CurrentUser.add(UserName);
		for(int i=1;i<=Chat_Server.ConnectionArray.size();i++) {
			Socket Temp_Sock =(Socket) Chat_Server.ConnectionArray.get(i-1);
			PrintWriter Out =new PrintWriter(Temp_Sock.getOutputStream());
			Out.println("#?!"+CurrentUser);
			Out.flush();
		}
	}
}
