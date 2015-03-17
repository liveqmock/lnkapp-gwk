package org.fbi.gwk.helper;

import org.fbi.gwk.tpsserver.hdserver.MsgCommHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ������������ client
 * User: zhanrui
 * Date: 13-11-27
 */
public class TpsSocketClient {
    private String ip;
    private int port;
    private int timeout = 30000; //Ĭ�ϳ�ʱʱ�䣺ms  ���ӳ�ʱ�����ʱͳһ
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public TpsSocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public byte[] call(byte[] sendbuf)  {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException("��������������ַ����");
        }
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(addr, port), timeout);
            socket.setSoTimeout(timeout);

            OutputStream os = socket.getOutputStream();
            os.write(sendbuf);
            os.flush();

            InputStream is = socket.getInputStream();
            byte[] lenbuf = new byte[8];
            int readNum = is.read(lenbuf);
            if (readNum == -1) {
                throw new RuntimeException("�����������ѹر�!");
            }
            if (readNum < 8) {
                throw new RuntimeException("��ȡ����ͷ���Ȳ��ִ���...");
            }
            int msgLen = Integer.parseInt(new String(lenbuf).trim());
            byte[] recvbuf = new byte[msgLen];

            //���Ӳ��ȶ�ʱ ����ʱһ��ʱ��
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //
            }

            readNum = is.read(recvbuf, 8, msgLen - 8);   //������
            if (readNum != msgLen) {
                throw new RuntimeException("���ĳ��ȴ���,����ͷָʾ����:[" + msgLen + "], ʵ�ʻ�ȡ����:[" + readNum + "]");
            }

            //�ϲ�buffer
            System.arraycopy(lenbuf, 0, recvbuf, 0, lenbuf.length);
            return recvbuf;
        } catch (SocketTimeoutException e1) {
            throw new RuntimeException("���ӳ�ʱ", e1);
        } catch (IOException e2) {
            throw new RuntimeException("ͨѶ�쳣", e2);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                //
            }
        }
    }


    public static void main(String... argv) throws UnsupportedEncodingException {
        TpsSocketClient mock = new TpsSocketClient("127.0.0.1", 2308);

        //ҵ����
        String bizMsg = "..........";

        int bizMsgLen = bizMsg.getBytes("GBK").length;

        String authoCode = "authorization";

        MsgCommHeader msgCommHeader = new MsgCommHeader();
        msgCommHeader.setSenderCode("111");
        msgCommHeader.setRecverCode("222");
        msgCommHeader.setDataType("9905");
        msgCommHeader.setSendTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        msgCommHeader.setSignFlag("0");

        msgCommHeader.setAuthoCode(authoCode);
        int authoCodeLen = authoCode.getBytes("GBK").length;
        msgCommHeader.setAuthoCodeLen("" + authoCodeLen);


        int headerLen = msgCommHeader.getMsgHeaderLength();
        int msgLen = bizMsgLen  + headerLen;
        msgCommHeader.setMsgLen("" + msgLen);

        String headerStr = msgCommHeader.marshal(); //�Ѱ�����Ȩ��
        String msg = headerStr + bizMsg;


        try {
            byte[] recvbuf = mock.call((msg).getBytes("GBK"));
            System.out.printf("���������أ�%s\n", new String(recvbuf, "GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
