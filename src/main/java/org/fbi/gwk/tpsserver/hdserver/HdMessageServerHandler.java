package org.fbi.gwk.tpsserver.hdserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * User: zhanrui
 * Date: 2014-11-06
 */
public class HdMessageServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(HdMessageServerHandler.class);
    private static Map<String,Object> contextsMap = new ConcurrentHashMap<String,Object>();

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String reqXml)  {
        try {
            logger.info("服务器收到报文：" + reqXml);

            /*int startIndex = reqXml.indexOf("<TransCode>")+"<TransCode>".length();
            int endIndex = reqXml.indexOf("</TransCode>");*/
            String txnCode = reqXml.substring(8, 14).trim().toUpperCase();

            TxnContext txnContext = new TxnContext();
            txnContext.setMsgtia(reqXml);
            Class clazz = Class.forName("org.fbi.gwk.tpsserver.hdserver.processor.T" + txnCode + "Processor");
            Processor processor = (Processor)clazz.newInstance();
            processor.service(txnContext);

            String respXml = txnContext.getMsgtoa();
//            respXml = "<?xml version='1.0' encoding='GBK'?>" + respXml;
//            String strLen = String.format("%08d", respXml.getBytes("GBK").length + 8);

//            respXml = strLen + respXml;
            logger.info("服务器发送报文：" + respXml);
            ctx.writeAndFlush(respXml);
            /*String str = "00001103  9000     CCB-370211      CZ-370211201504071408420044               DVJfrWuqXA7/UZLhgh0CiPQ2qJEA3ELDiXROT7vSkb4=<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                    "<Root>\n" +
                    "  <Head>\n" +
                    "    <src>CCB-370211</src>\n" +
                    "    <des>CZ-370211</des>\n" +
                    "    <dataType>9000</dataType>\n" +
                    "    <msgId>21013720150407140842</msgId>\n" +
                    "    <msgRef>11013702110000000000</msgRef>\n" +
                    "    <workDate>2015-04-07</workDate>\n" +
                    "  </Head>\n" +
                    "  <Body>\n" +
                    "    <BatchHead>\n" +
                    "      <pack_no>20150407140842</pack_no>\n" +
                    "      <all_num>1</all_num>\n" +
                    "      <all_amt>0</all_amt>\n" +
                    "      <child_pack_count>1</child_pack_count>\n" +
                    "      <child_pack_no>1</child_pack_no>\n" +
                    "      <child_pack_num>1</child_pack_num>\n" +
                    "      <child_pack_amt>0</child_pack_amt>\n" +
                    "    </BatchHead>\n" +
                    "    <Object>\n" +
                    "      <Record>\n" +
                    "        <ori_datatype_code>1101</ori_datatype_code>\n" +
                    "        <ori_send_orgcode>CZ-370211</ori_send_orgcode>\n" +
                    "        <ori_entrust_date>2015-03-30</ori_entrust_date>\n" +
                    "        <result>2101007</result>\n" +
                    "        <add_word>无消费信息返回</add_word>\n" +

                    "      </Record>\n" +
                    "    </Object>\n" +
                    "  </Body>\n" +
                    "  <Signs>\n" +
                    "    <Sign>DVJfrWuqXA7/UZLhgh0CiPQ2qJEA3ELDiXROT7vSkb4=</Sign>\n" +
                    "    <Stamp></Stamp>\n" +
                    "  </Signs>\n" +
                    "</Root>";
            ctx.writeAndFlush(str);*/
        } catch (ClassNotFoundException e) {
            logger.error("Processor not found!", e);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Processor instance error!", e);
        } catch (Exception e){
            logger.error("Txn process error", e);
            //TODO 返回异常报文？
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("ChannelInactived.");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Unexpected exception from downstream.", cause);
        ctx.close();
    }

}
