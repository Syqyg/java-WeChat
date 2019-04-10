/**
 * Copyright (C), 2019-2019, latent
 * FileName: WxService
 * Author:   Yoga910
 * Date:     2019/3/13 21:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package wx;


import javax.print.DocFlavor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 〈验证签名〉<br>
 * 〈〉
 *
 * @author Yoga910
 * @create 2019/3/13
 * @since 1.0.0
 */
public class WxService {

    private static final String TOKEN = "abc";



    public static boolean check(String timestamp,String nonce,String signature){
        //将token timestamp  nonce 三个参数进行字典排序
        String[] strs = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        //将三个字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
            //加密之后的结果
        //        System.out.printf("mysig");
        //        System.out.printf("signature");
        //开发者获得加密信息后的字符串与signature对比,标识请求来源于微信
        return mysig.equalsIgnoreCase(signature);
    }
    private static String sha1(String src){
        //获取一个加密的对象
        try{
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            //处理加密结果
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuffer sb = new StringBuffer();
            for(byte b:digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return null;
    }

}