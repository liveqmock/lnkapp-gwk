import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.record.Record1101;
import org.fbi.gwk.domain.tps.record.Record2102;
import org.fbi.gwk.processor.TpsContext;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 黄岛 公务卡  特色平台
 * User: zhanrui
 */
public class StarringMock {

    private String ip;
    private int port;
    private String txnCode;


    public byte[] call(byte[] sendbuf) {
        Socket socket = null;
        OutputStream os = null;
        byte[] recvbuf = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(60000);

            os = socket.getOutputStream();
            os.write(sendbuf);
            os.flush();

            InputStream is = socket.getInputStream();
            recvbuf = new byte[6];
            int readNum = is.read(recvbuf);
            if (readNum < 6) {
                throw new RuntimeException("读取报文长度出错！");
            }
            int msgLen = Integer.parseInt(new String(recvbuf).trim());
            recvbuf = new byte[msgLen];

            readNum = is.read(recvbuf);
            if (readNum != msgLen - 6) {
                throw new RuntimeException("读取报文长度出错！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
                socket.close();
            } catch (IOException e) {
                //
            }
        }
        return recvbuf;
    }

    private String getTxnFile() {
        String txnFile = "txn" + this.txnCode +".txt";
        InputStream usageStream = getClass().getClassLoader().getResourceAsStream(txnFile);

        if (usageStream == null) {
            System.err.println("交易报文文件不存在：" + txnFile);
            System.exit(1);
        }

        StringBuffer  result = new StringBuffer();
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new InputStreamReader(usageStream));
            String line;

            while ((line = buf.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }


    private String getRequestMsg1000() {
        String header = "1.0" +
                new SimpleDateFormat("[yyyyMMdd--HHmmss]").format(new Date())+
                "0000" +
//                "1701000" +
                "170XXX1" +   //测试
                "999999999" +
                "123456789012" +
                "LNK170" +  //userid 6
                "CCBGWK" +  //appid 6
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                "[MAC123456789012345678901234567]";

        String body = "370211|" +
                "VCHNO001|" ;
        return header + body;
    }


    public static void main(String... argv) throws UnsupportedEncodingException, InterruptedException {
        Thread.sleep(1000);
        StarringMock mock = new StarringMock();
/*
        System.out.println("" + argv.length);
        if (argv.length < 3) {
            System.out.println("命令参数示例：\n" +
                    "    MockClient -ip=127.0.0.1 -port=60004 -txn=1010");
            System.exit(1);
        }

        for (String arg : argv) {
            if (arg.startsWith("-ip=")) {
                mock.ip = arg.substring(4);
                continue;
            }
            if (arg.startsWith("-port=")) {
                mock.port = Integer.parseInt(arg.substring(6));
                continue;
            }
            if (arg.startsWith("-txn=")) {
                mock.txnCode = arg.substring(5);
                continue;
            }
        }

*/
        //mock.getTxnFile();
        mock.ip = "127.0.0.1";
        mock.port = 60006;
        mock.txnCode = "1000";

        String message = mock.getRequestMsg1000();
        System.out.printf("===请求报文:%s\n", message);

        int len = message.getBytes("GBK").length;
        String strLen = "" + (len + 6);
        for (int i = strLen.length(); i < 6; i++) {
            strLen += " ";
        }
        byte[] recvbuf = mock.call((strLen + message).getBytes("GBK"));
        System.out.printf("===服务器返回报文:%s\n", new String(recvbuf, "GBK"));


/*
        message = mock.getRequestMsg1070();

        len = message.getBytes("GBK").length;
        strLen = "" + (len + 6);
        for (int i = strLen.length(); i < 6; i++) {
            strLen += " ";
        }
        recvbuf = mock.call((strLen + message).getBytes("GBK"));
        System.out.printf("服务器返回报文：%s\n", new String(recvbuf, "GBK"));
*/
    }
}
