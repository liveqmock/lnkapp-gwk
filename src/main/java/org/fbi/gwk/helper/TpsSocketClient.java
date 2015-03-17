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
 * 第三方服务器 client
 * User: zhanrui
 * Date: 13-11-27
 */
public class TpsSocketClient {
    private String ip;
    private int port;
    private int timeout = 30000; //默认超时时间：ms  连接超时与读超时统一
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
            throw new RuntimeException("第三方服务器地址错误");
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
                throw new RuntimeException("服务器连接已关闭!");
            }
            if (readNum < 8) {
                throw new RuntimeException("读取报文头长度部分错误...");
            }
            int msgLen = Integer.parseInt(new String(lenbuf).trim());
            byte[] recvbuf = new byte[msgLen];

            //连接不稳定时 需延时一定时间
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //
            }

            readNum = is.read(recvbuf, 8, msgLen - 8);   //阻塞读
            if (readNum != msgLen) {
                throw new RuntimeException("报文长度错误,报文头指示长度:[" + msgLen + "], 实际获取长度:[" + readNum + "]");
            }

            //合并buffer
            System.arraycopy(lenbuf, 0, recvbuf, 0, lenbuf.length);
            return recvbuf;
        } catch (SocketTimeoutException e1) {
            throw new RuntimeException("连接超时", e1);
        } catch (IOException e2) {
            throw new RuntimeException("通讯异常", e2);
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

        //业务报文
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

        String headerStr = msgCommHeader.marshal(); //已包括授权码
        String msg = headerStr + bizMsg;


        try {
            byte[] recvbuf = mock.call((msg).getBytes("GBK"));
            System.out.printf("服务器返回：%s\n", new String(recvbuf, "GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
