package com.ruoyi.web.core.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * open-api
 *
 * @author Aaron
 * @date 2022/2/11 22:07
 */
@Configuration
@RequiredArgsConstructor
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "Authorization", scheme = "bearer ", in = SecuritySchemeIn.HEADER)
public class SpringDocConfig {
    private final DocInfo docInfo;

    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI().info(new Info()
                .title(docInfo.getTitle())
                .description(docInfo.getDescription())
                .version(docInfo.getVersion()))
                .externalDocs(new ExternalDocumentation().description(docInfo.getWebsiteName()).url(docInfo.getWebsiteUrl()));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group(docInfo.getTitle())
                .pathsToMatch("/**")
                //.packagesToScan("com.ruoyi.web.controller")
                .build();
    }
}