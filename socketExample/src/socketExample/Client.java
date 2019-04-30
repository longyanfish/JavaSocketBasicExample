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
 * @param ������ֻ������һ���ͻ��˵ļ򵥳���
 * ����ǰ�׿�ͻ��ˣ���ô���Socket����������߳��У���׿���̲߳��ܴ����ʱ���������������
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
			Socket socket=new Socket("192.168.0.106",2222);//�����ͻ��ˣ�ָ����������ַ�Ͷ˿ں�
			OutputStream os=socket.getOutputStream();//��ȡ����������˷�����Ϣ
			PrintWriter pw=new PrintWriter(os);
			pw.write("��÷������ˣ����ǿͻ��ˣ�");
			pw.flush();//����������û��Ҳǿ���Է���ȥ
		
			socket.shutdownOutput();
		 //����Ϊ��ȡ����˷��ص���Ϣ,һ��ʼһֱû���õ����ص����ݣ����ɾ�������ַ�ֿ����ˣ���û������������ַ��

			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
			BufferedReader br=new BufferedReader(isr);//Ϊ��������ӻ���
			String info=null;
			while((info=br.readLine())!=null){//��ȡ�����һ����Ϣ
				System.out.println("���ǿͻ��ˣ�����������˵��"+info);
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
