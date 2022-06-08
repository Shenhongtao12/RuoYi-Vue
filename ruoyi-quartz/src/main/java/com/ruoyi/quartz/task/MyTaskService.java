package com.ruoyi.quartz.task;

import org.springframework.stereotype.Service;

/**
 * @author as2i
 * @date 5/27/2022 4:12 PM
 */
@Service()
public class MyTaskService {

    public void run1(String name)
    {
            for (int i = 0; i < 10; i++) {
                System.out.println("xxx" + name + " " + i);
            }
    }
}
