package top.wello.health.service;

import com.google.gson.Gson;
import okhttp3.FormBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.wello.health.dao.UserDAO;
import top.wello.health.util.Utility;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BaiduService {

    private Logger logger = LoggerFactory.getLogger(BaiduService.class);

    @Value("${constant.health.baidu.key}")
    private String baiduKey;

    @Value("${constant.health.baidu.sec}")
    private String baiduSec;

    @Value("${constant.health.baidu.tokenUrl}")
    private String tokenUrl;

    @Value("${constant.health.baidu.imageUrl}")
    private String imageUrl;

    private volatile String baiduToken;
    private volatile long lastGenerateTime;
    private volatile long expireTime = 2592000000L;

    @Resource
    NetworkService networkService;

    public String getBaiduToken() {
        if (!Utility.isEmpty(baiduKey) && System.currentTimeMillis() - lastGenerateTime < expireTime) {
            return baiduToken;
        }
        FormBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", baiduKey)
                .add("client_secret", baiduSec).build();
        String res = networkService.post(tokenUrl, body);
        if (res != null) {
            try {
                BaiduTokenDTO baiduTokenDTO = new Gson().fromJson(res, BaiduTokenDTO.class);
                if (!Utility.isEmpty(baiduTokenDTO.access_token) && expireTime != 0) {
                    baiduToken = baiduTokenDTO.access_token;
                    expireTime = baiduTokenDTO.getExpires_in() * 1000;
                    lastGenerateTime = System.currentTimeMillis();
                    return baiduToken;
                } else {
                    logger.error("get token failed " + baiduTokenDTO);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static class BaiduFoodDTO {

        /**
         * log_id : 7357081719365269362
         * result_num : 5
         * result : [{"calorie":"119","has_calorie":true,"name":"酸汤鱼","probability":"0.396031","baike_info":{"baike_url":"http://baike.baidu.com/item/%E9%85%B8%E6%B1%A4%E9%B1%BC/1754055","description":"酸汤鱼，是黔桂湘交界地区的一道侗族名菜，与侗族相邻的苗、水、瑶等少数民族也有相似菜肴，但其中以贵州侗族酸汤鱼最为有名，据考证此菜肴最早源于黎平县雷洞镇牙双一带。制作原料主要有鱼肉、酸汤、山仓子等香料。成菜后，略带酸味、幽香沁人、鲜嫩爽口开胃，是贵州\u201c黔系\u201d菜肴的代表作之一。这道菜通常先自制酸汤，之后将活鱼去掉内脏，入酸汤煮制。"}},{"calorie":"38","has_calorie":true,"name":"原味黑鱼煲","probability":"0.265432"},{"calorie":"144","has_calorie":true,"name":"椒鱼片","probability":"0.0998993"},{"calorie":"98","has_calorie":true,"name":"酸菜鱼","probability":"0.0701917"},{"calorie":"257.65","has_calorie":true,"name":"柠檬鱼","probability":"0.0471465"}]
         */

        private long log_id;
        private int result_num;
        private List<ResultBean> result;

        public long getLog_id() {
            return log_id;
        }

        public void setLog_id(long log_id) {
            this.log_id = log_id;
        }

        public int getResult_num() {
            return result_num;
        }

        public void setResult_num(int result_num) {
            this.result_num = result_num;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * calorie : 119
             * has_calorie : true
             * name : 酸汤鱼
             * probability : 0.396031
             * baike_info : {"baike_url":"http://baike.baidu.com/item/%E9%85%B8%E6%B1%A4%E9%B1%BC/1754055","description":"酸汤鱼，是黔桂湘交界地区的一道侗族名菜，与侗族相邻的苗、水、瑶等少数民族也有相似菜肴，但其中以贵州侗族酸汤鱼最为有名，据考证此菜肴最早源于黎平县雷洞镇牙双一带。制作原料主要有鱼肉、酸汤、山仓子等香料。成菜后，略带酸味、幽香沁人、鲜嫩爽口开胃，是贵州\u201c黔系\u201d菜肴的代表作之一。这道菜通常先自制酸汤，之后将活鱼去掉内脏，入酸汤煮制。"}
             */

            private String calorie;
            private boolean has_calorie;
            private String name;
            private String probability;
            private BaikeInfoBean baike_info;

            public String getCalorie() {
                return calorie;
            }

            public void setCalorie(String calorie) {
                this.calorie = calorie;
            }

            public boolean isHas_calorie() {
                return has_calorie;
            }

            public void setHas_calorie(boolean has_calorie) {
                this.has_calorie = has_calorie;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProbability() {
                return probability;
            }

            public void setProbability(String probability) {
                this.probability = probability;
            }

            public BaikeInfoBean getBaike_info() {
                return baike_info;
            }

            public void setBaike_info(BaikeInfoBean baike_info) {
                this.baike_info = baike_info;
            }

            public static class BaikeInfoBean {
                /**
                 * baike_url : http://baike.baidu.com/item/%E9%85%B8%E6%B1%A4%E9%B1%BC/1754055
                 * description : 酸汤鱼，是黔桂湘交界地区的一道侗族名菜，与侗族相邻的苗、水、瑶等少数民族也有相似菜肴，但其中以贵州侗族酸汤鱼最为有名，据考证此菜肴最早源于黎平县雷洞镇牙双一带。制作原料主要有鱼肉、酸汤、山仓子等香料。成菜后，略带酸味、幽香沁人、鲜嫩爽口开胃，是贵州“黔系”菜肴的代表作之一。这道菜通常先自制酸汤，之后将活鱼去掉内脏，入酸汤煮制。
                 */

                private String baike_url;
                private String description;

                public String getBaike_url() {
                    return baike_url;
                }

                public void setBaike_url(String baike_url) {
                    this.baike_url = baike_url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }

    private static class BaiduTokenDTO {

        /**
         * refresh_token : 25.c9a43d2cedfd2fe6cbc929ce2e9635ef.315360000.1869148178.282335-15464059
         * expires_in : 2592000
         * session_key : 9mzdWuUbCB+elCxmuajKwDVFiRfNXtEMVRCb7SLr2mXXmBTER63jAQCqzqW/UrAt8PhUwfmsYoPgKalNMPfX1l+nTecJww==
         * access_token : 24.a96cbcc206fa87e80424d3102b923434.2592000.1556380178.282335-15464059
         * scope : brain_ingredient brain_custom_dish public vis-classify_dishes vis-classify_car brain_all_scope vis-classify_animal vis-classify_plant brain_object_detect brain_realtime_logo brain_dish_detect brain_car_detect brain_animal_classify brain_plant_classify brain_advanced_general_classify brain_poi_recognize wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi
         * session_secret : 14d9821b303c6b6fabd8ec2daa7a5d2d
         */

        private String refresh_token;
        private long expires_in;
        private String session_key;
        private String access_token;
        private String scope;
        private String session_secret;

        private String error;
        private String error_description;

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public long getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(long expires_in) {
            this.expires_in = expires_in;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getSession_secret() {
            return session_secret;
        }

        public void setSession_secret(String session_secret) {
            this.session_secret = session_secret;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getError_description() {
            return error_description;
        }

        public void setError_description(String error_description) {
            this.error_description = error_description;
        }

        @Override
        public String toString() {
            if (access_token != null) {
                return "BaiduTokenDTO " + access_token;
            }
            return "BaiduTokenDTO " + error + " " + error_description;
        }
    }



}
