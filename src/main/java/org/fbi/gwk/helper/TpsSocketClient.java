package org.fbi.gwk.helper;

import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgHead;
import org.fbi.gwk.domain.tps.base.TpsMsgSigns;
import org.fbi.gwk.domain.tps.record.Record1101;
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
            byte[] recvbuf = new byte[msgLen-8];

            //���Ӳ��ȶ�ʱ ����ʱһ��ʱ��
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //
            }

//            readNum = is.read(recvbuf, 8, msgLen - 8);   //������
            readNum = is.read(recvbuf);   //������
            if (readNum != msgLen-8) {
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
        TpsSocketClient mock = new TpsSocketClient("127.0.0.1", 8066);

        //ҵ����
        SingleRecordMsg tia = new SingleRecordMsg();
        TpsMsgHead  head = new TpsMsgHead();
        head.setSrc("CCB-370211");
        head.setDes("CZ-370211");
        head.setDataType("1101");
        head.setMsgId("11012201819991000016 ");
        head.setMsgRef("11012201819991000016 ");
        head.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tia.setHead(head);
        Record1101 record1101 = new Record1101();
        record1101.setCard_no("6283660033405313");
        record1101.setSerial_no("12");
        record1101.setConsume_datetime("2012-08-21");
        record1101.setConsume_datetimee("2012-08-21");
        record1101.setOriginal_amount("2055.00");
        record1101.setDownload_type("0");
        record1101.setEn_code("201001");
        record1101.setReg_code("100000");
        record1101.setPack_no("1");
        record1101.setChild_pack_no("2");
        tia.getBody().getRecords().add(record1101);
        TpsMsgSigns signs = new TpsMsgSigns();
        signs.setSign("DVJfrWuqXA7/UZLhgh0CiPQ2qJEA3ELDiXROT7vSkb4=");
        signs.setStamp("111");
        tia.setSigns(signs);

        String bizMsg = tia.toXml(tia);

        int bizMsgLen = bizMsg.getBytes("GBK").length;
//        String bizMsg = "..........";
//
//        int bizMsgLen = bizMsg.getBytes("GBK").length;

        String authoCode = "";

        MsgCommHeader msgCommHeader = new MsgCommHeader();
        msgCommHeader.setSenderCode("111");
        msgCommHeader.setRecverCode("222");
        msgCommHeader.setDataType("1101");
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
