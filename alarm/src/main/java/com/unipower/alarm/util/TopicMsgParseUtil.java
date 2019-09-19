package com.unipower.alarm.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.unipower.alarm.entity.AlarmData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author leiyanqing
 * @Date 2019-06-10
 * @Desc 用于解析MQ数据
 */
public class TopicMsgParseUtil {
    /**
     * 解析性能运行数据并返回实体
     *
     * @param bytes
     * @return
     */
    public static AlarmData parseAlarmData(byte[] bytes) {
        try {
            //创建告警数据实体类
            AlarmData perfDataEntity = new AlarmData();
            //通过接口解析bytes数据
            com.unipower.alarm.util.DataInterface.AlarmData.Buider alarmData = com.unipower.alarm.util.
            com.unipower.perfdata.util.DataInterface.PerfData.Builder perfData = com.unipower.perfdata.util.DataInterface.PerfData.newBuilder().mergeFrom(bytes);
            //获取性能运行数据列表
            List<com.unipower.perfdata.util.DataInterface.Datas> datas = perfData.getDatasList();
            Iterator<com.unipower.perfdata.util.DataInterface.Datas> datasIte = datas.iterator();
            //迭代
            while (datasIte.hasNext()) {
                com.unipower.perfdata.util.DataInterface.Datas data = datasIte.next();
                //设置实体类的值
                perfDataEntity.setGid(data.getGid());
                perfDataEntity.setGroup(data.getGroup());
                //获取每个组中包含的指标列表
                List<com.unipower.perfdata.util.DataInterface.SubDatas> subDatasList = data.getSubDatasList();
                Iterator<com.unipower.perfdata.util.DataInterface.SubDatas> subDatasIte = subDatasList.iterator();
                Map<String, String> dataMap = new HashMap<>();
                //迭代
                while (subDatasIte.hasNext()) {
                    com.unipower.perfdata.util.DataInterface.SubDatas subData = subDatasIte.next();
                    //设置实体dataMap的值
                    dataMap.put(subData.getPref(), subData.getValue());
                }
                perfDataEntity.setDataMap(dataMap);
            }
            return perfDataEntity;
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

}
