package com.unipower.alarm.entity;

import java.io.Serializable;

/**
 * 告警实体类
 */
public class AlarmData implements Serializable {

    private long id;    //告警ID
    private long time;  //告警时间
    private String type;    //告警类型
    private int level;  //告警等级
    private String object_gid;  //告警对象通用ID
    private String object_name; //告警对象名称
    private String sub_type;    //告警子类型
    private String device_gid;  //故障发生节点的gid
    private int status; //告警处理状态
    private long handle_time;   //告警处理时间
    private String title;   //告警名称
    private String content; //告警具体内容

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getObject_gid() {
        return object_gid;
    }

    public void setObject_gid(String object_gid) {
        this.object_gid = object_gid;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getDevice_gid() {
        return device_gid;
    }

    public void setDevice_gid(String device_gid) {
        this.device_gid = device_gid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getHandle_time() {
        return handle_time;
    }

    public void setHandle_time(long handle_time) {
        this.handle_time = handle_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
