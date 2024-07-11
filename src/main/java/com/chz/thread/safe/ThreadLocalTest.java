package com.chz.thread.safe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ThreadLocalTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadLocalTest.class);
    private ThreadLocal<Integer> value = new ThreadLocal<>();

    @RequestMapping("getvalue")
    @ResponseBody
    public String getvalue(){
        if (value.get() == null) {
            value.set(0);
        }
        value.set(value.get().intValue() + 1);
        log.info("{} -> {}", Thread.currentThread().getName(), value.get());
        return value.get().toString();
    }
}
