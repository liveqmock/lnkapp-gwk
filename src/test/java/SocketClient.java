import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.record.Record1101;
import org.fbi.gwk.domain.tps.record.Record2102;
import org.fbi.gwk.processor.TpsContext;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 黄岛 非税
 * User: zhanrui
 */
public class SocketClient {

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

    //1561070 入资登记预交易

    private String getRequestMsg1010() {
        String header = "1.0" +
                new SimpleDateFormat("[yyyyMMdd--HHmmss]").format(new Date())+
                "0000" +
                "1701010" +
                "999999999" +
                "123456789012" +
                "LNK170" +  //userid 6
                "TRCTQC" +  //appid 6
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                "[MAC123456789012345678901234567]";

        String body = "PZH001|" +
                "ACCTNO001|" +
                "MCHT001|" +
                "PRJ001|" +
                "100|";
        return header + body;
    }

    private String getRequestMsg1020() {
        String header = "1.0" +
                new SimpleDateFormat("[yyyyMMdd--HHmmss]").format(new Date())+
                "0000" +
                "1701020" +
                "999999999" +
                "123456789012" +
                "LNK170" +  //userid 6
                "TRCTQC" +  //appid 6
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                "[MAC123456789012345678901234567]";

        String body = "PZH001|" +
                "ACCTNO1234123456789|" +        //对私
                "MCHT001|" +
                "PRJ001|" +
                "100|";
        return header + body;
    }

    private String get1101(){
        SingleRecordMsg tpsTia = new SingleRecordMsg();
        Record1101 record1101 = new Record1101();
        record1101.setCard_no("6283660033405313"); //TODO
        record1101.setSerial_no("111");
        record1101.setConsume_datetime("2012-08-21");
        record1101.setConsume_datetimee("2012-09-16");
        record1101.setOriginal_amount("2055.00");
        record1101.setDownload_type("1");
        record1101.setEn_code("GBK");
        record1101.setReg_code("1");
        record1101.setPack_no("1");
        record1101.setChild_pack_no("2");
        tpsTia.getBody().getRecords().add(record1101);
        String reqXml = tpsTia.toXml(tpsTia);


        TpsContext tpsContext = new TpsContext();
        tpsContext.setTpsTiaTxnCode("2102");
        tpsContext.setTpsTiaXml(reqXml);
        return reqXml;
    }


    public static void main(String... argv) throws UnsupportedEncodingException, InterruptedException {
        Thread.sleep(3000);
        SocketClient client = new SocketClient();
/*
        System.out.println("" + argv.length);
        if (argv.length < 3) {
            System.out.println("命令参数示例：\n" +
                    "    MockClient -ip=127.0.0.1 -port=60004 -txn=1010");
            System.exit(1);
        }

        for (String arg : argv) {
            if (arg.startsWith("-ip=")) {
                client.ip = arg.substring(4);
                continue;
            }
            if (arg.startsWith("-port=")) {
                client.port = Integer.parseInt(arg.substring(6));
                continue;
            }
            if (arg.startsWith("-txn=")) {
                client.txnCode = arg.substring(5);
                continue;
            }
        }

*/
        //client.getTxnFile();
        client.ip = "127.0.0.1";
        client.port = 8066;
        client.txnCode = "1000";

//        String message = client.getRequestMsg1020();
        String message = client.get1101();
        System.out.printf("===请求报文:%s\n", message);

        int len = message.getBytes("GBK").length;
        String strLen = "" + (len + 6);
        for (int i = strLen.length(); i < 6; i++) {
            strLen += " ";
        }
        byte[] recvbuf = client.call((strLen + message).getBytes("GBK"));
        System.out.printf("===服务器返回报文:%s\n", new String(recvbuf, "GBK"));


/*
        message = client.getRequestMsg1070();

        len = message.getBytes("GBK").length;
        strLen = "" + (len + 6);
        for (int i = strLen.length(); i < 6; i++) {
            strLen += " ";
        }
        recvbuf = client.call((strLen + message).getBytes("GBK"));
        System.out.printf("服务器返回报文：%s\n", new String(recvbuf, "GBK"));
*/
    }
}
