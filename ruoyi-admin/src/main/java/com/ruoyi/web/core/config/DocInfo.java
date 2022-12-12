package com.ruoyi.web.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO 类描述
 *
 * @author Aaron
 * @date 2022/12/12 16:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "doc-info")
public class DocInfo {
    private String title = "ruoyi";
    private String description = "ruoyi Description";
    private String version = "v0.0.1";
    private String websiteName = "ryiyi Website";
    private String websiteUrl = "http://www.baidu.com";
}
