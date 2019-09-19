package com.unipower.alarm.util;

import com.nari.osp.msgbus.impls.MessageServiceImpl;
import com.nari.osp.msgbus.interfaces.IMessageSession;
import com.nari.osp.msgbus.message.Msg;
import com.nari.osp.msgbus.message.StreamMsg;
import com.nari.osp.msgbus.msgexception.MessageServiceException;
//import com.unipower.perfdata.bean.PerfData;
//import com.unipower.perfdata.dao.PerfDataDao;
//import com.unipower.perfdata.dao.PerfNodeDao;
import org.springframework.dao.DataAccessException;

public class NariReceiver {

    public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NariReceiver.class);

    public static void setOnListener() {

        MessageServiceImpl ss = new MessageServiceImpl();
        IMessageSession ms = null;
        try {
            /**
             * 参数说明：
             * connectionID
             */
            ms = ss.create("test2");
        } catch (MessageServiceException e) {
            e.printStackTrace();
        }
        log.info("开始启动监听");

        PerfNodeDao nodeDao = new PerfNodeDao();
        PerfDataDao dataDao = new PerfDataDao();
        while (true) {
            try {
                /**
                 * 参数说明：
                 * i : topic前缀
                 * s : 订阅者名称
                 * b : 是否持久化
                 */
                Msg msg = ms.receiveTopicMessage(1002, "test2", false);

                if (msg != null) {
                    log.info("收到消息！");
                    if (msg instanceof StreamMsg) {
                        log.info("开始解析");
                        StreamMsg streamMsg = (StreamMsg) msg;
                        byte[] bytes = streamMsg.getBytes();
                        //调用接口解析
                        PerfData perfData = TopicMsgParseUtil.parsePerfData(bytes);

                        log.info("解析完成");

                        log.info("开始入库");
                        try {
                            nodeDao.addPrefNode(perfData);
                            dataDao.addPrefData(perfData);

                            log.info("入库完成");
                        } catch (DataAccessException e) {
                            log.error("入库异常");
                            e.printStackTrace();
                        }
                    } else {
                        log.error("解析失败");
                    }
                }
            } catch (MessageServiceException e) {
                e.printStackTrace();
            }
        }
    }

}
