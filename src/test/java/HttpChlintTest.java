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

        String original = "有基層團體要求劏房租金應每2年按通帳調整";
        String result = ZhConverterUtil.toSimple(original);
        System.out.println(result);
    }




}
