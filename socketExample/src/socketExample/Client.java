package socketExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
/**
 * 
 * @param 服务器只能连接一个客户端的简单程序，
 * 如果是安卓客户端，那么这个Socket必须放在子线程中，安卓主线程不能处理耗时操作，否则会闪退
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
			Socket socket=new Socket("192.168.0.106",2222);//创建客户端，指定服务器地址和端口号
			OutputStream os=socket.getOutputStream();//获取输出流向服务端发送信息
			PrintWriter pw=new PrintWriter(os);
			pw.write("你好服务器端，我是客户端！");
			pw.flush();//缓冲区数据没满也强制性发过去
		
			socket.shutdownOutput();
		 //以下为读取服务端返回的信息,一开始一直没有拿到返回的数据，换成局域网地址又可以了，是没法解析公网地址吧

			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);//将字节流转换为字符流
			BufferedReader br=new BufferedReader(isr);//为输入流添加缓冲
			String info=null;
			while((info=br.readLine())!=null){//读取服务端一行信息
				System.out.println("我是客户端，服务器对我说："+info);
			}
			socket.shutdownInput();
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
		    socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
