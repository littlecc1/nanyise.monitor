package top.wello.health.service;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.wello.health.Request.WXRunRequest;
import top.wello.health.dao.WXRunDAO;
import top.wello.health.session.SessionHolder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.util.Arrays;
import java.util.Calendar;

@Service
public class WXRunService {

    private Logger logger = LoggerFactory.getLogger(WXRunService.class);

    public int getTodayStep(WXRunRequest request) {
        String wechatSession = SessionHolder.getUser().getWechatSession();
        WXRunDAO wxRunDAO = decrypt(request.getEncryptedData(), request.getIv(), wechatSession);
        if (wxRunDAO != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            long startOfToday = calendar.getTimeInMillis() / 1000;
            int stepToday = 0;
            for (WXRunDAO.StepInfoListBean step: wxRunDAO.getStepInfoList()) {
                if (step.getTimestamp()  >= startOfToday) {
                    stepToday += step.getStep();
                }
            }
            return stepToday;
        }
        return -1;
    }

    /**
     * 小程序 数据解密
     *
     * @param encryptData 加密数据
     * @param iv          对称解密算法初始向量
     * @param sessionKey  对称解密秘钥
     * @return 解密数据
     */
    public WXRunDAO decrypt(String encryptData, String iv, String sessionKey) {
        try {
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(Base64.decodeBase64(iv)));
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES"), algorithmParameters);
            byte[] decode = PKCS7Encoder.decode(cipher.doFinal(Base64.decodeBase64(encryptData)));
            String decryptStr = new String(decode, StandardCharsets.UTF_8);
            return JSON.parseObject(decryptStr, WXRunDAO.class);
        } catch (Exception e) {
            logger.error("error in decrypt: {}", e.getMessage());
        }
        return null;
    }

    private static class PKCS7Encoder {

        static Charset CHARSET = Charset.forName("utf-8");
        static int BLOCK_SIZE = 32;

        /**
         * 获得对明文进行补位填充的字节.
         *
         * @param count 需要进行填充补位操作的明文字节个数
         * @return 补齐用的字节数组
         */
        public static byte[] encode(int count) {
            // 计算需要填充的位数
            int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
            if (amountToPad == 0) {
                amountToPad = BLOCK_SIZE;
            }
            // 获得补位所用的字符
            char padChr = chr(amountToPad);
            String tmp = new String();
            for (int index = 0; index < amountToPad; index++) {
                tmp += padChr;
            }
            return tmp.getBytes(CHARSET);
        }

        /**
         * 删除解密后明文的补位字符
         *
         * @param decrypted 解密后的明文
         * @return 删除补位字符后的明文
         */
        public static byte[] decode(byte[] decrypted) {
            int pad = decrypted[decrypted.length - 1];
            if (pad < 1 || pad > 32) {
                pad = 0;
            }
            return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
        }

        /**
         * 将数字转化成ASCII码对应的字符，用于对明文进行补码
         *
         * @param a 需要转化的数字
         * @return 转化得到的字符
         */
        static char chr(int a) {
            byte target = (byte) (a & 0xFF);
            return (char) target;
        }

    }

}
