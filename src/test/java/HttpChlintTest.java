import com.github.houbb.opencc4j.util.ZhConverterUtil;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * PACKAGE_NAME
 *
 * @author 庄先生
 * @date 2021/2/21
 */
public class HttpChlintTest {


    public static void main(String[] args) throws UnsupportedEncodingException {

//        String title = "%B9%FA%CE%F1%D4%BA%CF%F2%C8%CB%B4%F3%B3%A3%CE%AF%BB%E1%CC%E1%BD%BB%D3%A1%BB%A8%CB%B0%B7%A8%B2%DD%B0%B8%C9%F3%D2%E9";
//        title = URLDecoder.decode(title, "gb2312");
//        System.out.println(title);

        String original = "<p>警方中午近一時接獲報案，指在赤柱衞奕信徑孖崗山一帶發現懷疑戰時炸彈，涉及4個手榴彈及1個炸彈，警方派出爆炸品處理課到場處理。</p>";
        String result = ZhConverterUtil.toSimple(original);
        System.out.println(result);
    }




}
