package socketExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author服务器只能连接一个客户端的简单程序，
 *
 */
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          try {
        	  //创建一个服务器端的Socket,监听8888的端口
			ServerSocket serverSocket=new ServerSocket(2222);
			//调用accept()开始监听客户端的链接，如果没有客户端连接就会循环等待在这个地方，
			//但是一旦拿到了连接就会往下执行，就算有新的客户端来连接也不会回头
			System.out.println("服务期启动，等待客户端的连接");
			Socket socket=serverSocket.accept();//成功以后客户端返回一个socket实例
			//获取一个输入流用来读取客户端发来的信息
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);//将字节流转换为字符流
			BufferedReader br=new BufferedReader(isr);//为输入流添加缓冲
			String info=null;
			while((info=br.readLine())!=null){//读取客户端一行信息
				System.out.println("我是服务器，客户端对我说:"+info);
			}
			socket.shutdownInput();
			//获取输出流响应客户端
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			pw.write("我接收客户端的连接了！");
			pw.flush();
			
			socket.shutdownOutput();
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
