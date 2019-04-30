

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 
 * @author 这是一个客户端给服务端发送信息，又收到服务器端返回的信息的程序
 *
 */
public class Client {
	    String msg="客户端对服务器说:“我给你打了1000万刚才！”";
        Socket clientSocket=null;
        OutputStream outputStream=null;
        PrintWriter writer=null;
        InputStream inputStream=null;
        BufferedReader bufferedReader=null;
        InetAddress inetAddress= null;
        InputStreamReader inputStreamReader=null;
        Client(){
        //bug:一开始用下面这个域名死活都报错，怎么找也找不到错误，改成它的ip地址形式就对了，
        // 可能就是没法解析这个域名对应为这个ip地址
		try {
			 //inetAddress = InetAddress.getByName("ly-and-tl.uicp.cn");
	        // System.out.println(inetAddress);
		    clientSocket=new Socket("192.168.0.100",5000);
		    System.out.println("------客户端启动----");
		    //向服务器发送信息
		    outputStream=clientSocket.getOutputStream();
		    writer=new PrintWriter(outputStream,true);
		    writer.write(msg+"\n");
		    writer.flush();
		    clientSocket.shutdownOutput();
		  //以下为读取服务端返回的信息,一开始一直没有拿到返回的数据，换成局域网地址又可以了，是没法解析公网地址吧
		    inputStream=clientSocket.getInputStream();
		    if(clientSocket.isConnected())
            	System.out.println("服务器和客户端连接正常！");
            else
            	System.out.println("服务器和客户端连接不正常!");
		    inputStreamReader=new InputStreamReader(inputStream);
		    bufferedReader=new BufferedReader(inputStreamReader);
		    String str=null;
			while((str=bufferedReader.readLine())!=null){
            System.out.print(str);
			}
		    System.out.println("\n");
		   clientSocket.shutdownInput();
		} catch (IOException e) {
		    e.printStackTrace();
		}finally{
			if(writer!=null)
		    writer.close();
			if(inputStream!=null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(inputStreamReader!=null)
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(bufferedReader!=null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(clientSocket!=null)
				try {
					System.out.println("------客户端关闭-----");
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
      }
}
