package com.unipower.alarm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unipower.alarm.entity.AlarmData;

import java.util.ArrayList;
import java.util.List;

public class MsgUtil {
    /**
     * 将字符串消息转为bean
     * @param str 处理的字符串
     * @return 返回的bean列表
     */
    public static List<AlarmData> handleMsg(String str){
        JSONArray bodyArr = JSONObject.parseObject(str).getJSONArray("body");
        List<AlarmData> list = new ArrayList<>();

        for(int i=0;i<bodyArr.size();i++){
            JSONObject bodyObj = bodyArr.getJSONObject(i);
            String gid = bodyObj.getString("gid");

            for(String key :bodyObj.keySet()){
                if(key.equals("gid"))
                    continue;
                AlarmData pref= new AlarmData();
//                pref.setGid(gid);

                list.add(pref);
            }
        }

        return list;
    }
}
