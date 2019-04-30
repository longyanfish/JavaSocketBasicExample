import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * 
 * @author 服务器只能连接多个客户端的简单程序，
 *
 */
public class ChatServer {
	
	public static void main(String[] args) {
		Socket socket=null;
		ServerSocket serverSocket=null;
		int count=0;
	     Properties pro=System.getProperties();
	     pro.setProperty("file.encoding", "UTF-8");
	 	//创建一个服务端，指定5000为通信的端口和客户端通信
		try {
			serverSocket=new ServerSocket(5000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	     while(true){
			try {
				//在没有客户端连接时，服务器会阻塞在此，监听客户端的连接
				socket=serverSocket.accept(); 
				new SocketThread(socket).start();
				count++;//统计客户端的数量
				System.out.println("客户端的数量："+count);
				InetAddress address=socket.getInetAddress();
				System.out.println("当前客户端的IP："+address.getHostAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}		
	

	
	
	class  SocketThread extends Thread{
		InputStream inputStream=null;
		BufferedReader bufferedReader=null;
		OutputStream outputStream=null;
		PrintWriter printWriter=null;
		String msg="服务器对客户端说：“我收到你的钱了！”";
		Socket socketThread=null;
		SocketThread(Socket socketThread){
			this.socketThread=socketThread;
		}
		 public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				
				  if(socketThread.isConnected())
		            	System.out.println("服务器连接到客户机了！");
		            else
		            	System.out.println("服务器连接到客户机了！");
			
			     System.out.println("客户端的inetAddress是"+socketThread.getInetAddress());
				//套接字返回一个输入流
				inputStream=socketThread.getInputStream();
				//用inputStreamReader作为中介到达字符流
	            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
				bufferedReader=new BufferedReader(inputStreamReader);
				//读取客户端的数据
				String str=null;
				//BufferedReader的readLine()方法会等到一个换行符或者分隔符才会返回，否则会阻塞等待
				while((str=bufferedReader.readLine())!=null){
	            System.out.print(str);
				}
	            
	            System.out.println("\n");
	            //禁用套接字的输入流
	            socketThread.shutdownInput();
				//下面是向客户端输出信息
	          
				outputStream=socketThread.getOutputStream();
				  if(socketThread.isConnected())
		            	System.out.println("服务器连接到客户机了！");
		            else
		            	System.out.println("服务器连接到客户机了！");
				printWriter=new PrintWriter(outputStream,true);
				printWriter.write(msg+"\n");
		        printWriter.flush();
		        socketThread.shutdownOutput();
				System.out.println("-----服务器关闭-----");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			    try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printWriter.close(); 
                  try {
					socketThread.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
     }
  }

