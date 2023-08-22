package cn.yoyo.module.aps;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aps {

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();


    }

    public static List<ScheduleResult> schedule(List<Task> taskList) {
        // TODO: 2021/8/17 过滤掉不符合要求的任务
        // TODO: 2021/8/17 按照交货时间、风险等级排序，得到优先级任务列表
        // TODO: 2021/8/17 根据工作日配置信息生成工作日列表
        // TODO: 2021/8/17 根据工作日列表+排序任务列表生成任务调度列表
        // TODO: 2021/8/17 根据任务调度列表分析任务是否符合生产要求
        // TODO: 2021/8/17 生成任务调度结果
        return null;
    }

    @Data
    public static class Task {
        private Long id;
        private String name;
        private Date deliveryTime;
        private List<ScheduleResult> executeScheduleList;
    }

    @Data
    public static class ScheduleResult {
        private Long id;
        private Long taskId;
        private int startTime;
        private int endTime;
    }
}


