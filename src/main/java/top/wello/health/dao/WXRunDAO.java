package top.wello.health.dao;

import java.util.List;

public class WXRunDAO {


    /**
     * stepInfoList : [{"timestamp":1555689600,"step":0},{"timestamp":1555776000,"step":0},{"timestamp":1555862400,"step":0},{"timestamp":1555948800,"step":0},{"timestamp":1556035200,"step":0},{"timestamp":1556121600,"step":0},{"timestamp":1556208000,"step":0},{"timestamp":1556294400,"step":0},{"timestamp":1556380800,"step":0},{"timestamp":1556467200,"step":0},{"timestamp":1556553600,"step":0},{"timestamp":1556640000,"step":0},{"timestamp":1556726400,"step":0},{"timestamp":1556812800,"step":0},{"timestamp":1556899200,"step":0},{"timestamp":1556985600,"step":0},{"timestamp":1557072000,"step":0},{"timestamp":1557158400,"step":0},{"timestamp":1557244800,"step":0},{"timestamp":1557331200,"step":0},{"timestamp":1557417600,"step":0},{"timestamp":1557504000,"step":0},{"timestamp":1557590400,"step":0},{"timestamp":1557676800,"step":0},{"timestamp":1557763200,"step":0},{"timestamp":1557849600,"step":0},{"timestamp":1557936000,"step":0},{"timestamp":1558022400,"step":0},{"timestamp":1558108800,"step":0},{"timestamp":1558195200,"step":0},{"timestamp":1558281600,"step":21419}]
     * watermark : {"timestamp":1558364713,"appid":"wxa042a3b33fd8977e"}
     */

    private WatermarkBean watermark;
    private List<StepInfoListBean> stepInfoList;

    public WatermarkBean getWatermark() {
        return watermark;
    }

    public void setWatermark(WatermarkBean watermark) {
        this.watermark = watermark;
    }

    public List<StepInfoListBean> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(List<StepInfoListBean> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }

    public static class WatermarkBean {
        /**
         * timestamp : 1558364713
         * appid : wxa042a3b33fd8977e
         */

        private int timestamp;
        private String appid;

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }

    public static class StepInfoListBean {
        /**
         * timestamp : 1555689600
         * step : 0
         */

        private long timestamp;
        private int step;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }
    }
}
