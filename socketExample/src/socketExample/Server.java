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
 * @author������ֻ������һ���ͻ��˵ļ򵥳���
 *
 */
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          try {
        	  //����һ���������˵�Socket,����8888�Ķ˿�
			ServerSocket serverSocket=new ServerSocket(2222);
			//����accept()��ʼ�����ͻ��˵����ӣ����û�пͻ������Ӿͻ�ѭ���ȴ�������ط���
			//����һ���õ������Ӿͻ�����ִ�У��������µĿͻ���������Ҳ�����ͷ
			System.out.println("�������������ȴ��ͻ��˵�����");
			Socket socket=serverSocket.accept();//�ɹ��Ժ�ͻ��˷���һ��socketʵ��
			//��ȡһ��������������ȡ�ͻ��˷�������Ϣ
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
			BufferedReader br=new BufferedReader(isr);//Ϊ��������ӻ���
			String info=null;
			while((info=br.readLine())!=null){//��ȡ�ͻ���һ����Ϣ
				System.out.println("���Ƿ��������ͻ��˶���˵:"+info);
			}
			socket.shutdownInput();
			//��ȡ�������Ӧ�ͻ���
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			pw.write("�ҽ��տͻ��˵������ˣ�");
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
