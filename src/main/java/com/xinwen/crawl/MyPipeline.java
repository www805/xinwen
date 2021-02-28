package com.xinwen.crawl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Iterator;
import java.util.Map;

/**
 * com.zhuang
 *
 * @author 庄先生
 * @date 2020/12/20
 */
public class MyPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> all = resultItems.getAll();
        Iterator<Map.Entry<String, Object>> it = all.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key+":"+value);
        }
    }
}
