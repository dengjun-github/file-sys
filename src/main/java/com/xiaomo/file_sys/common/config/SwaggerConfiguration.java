package com.xiaomo.file_sys.common.config;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: Swagger2Config
 * Created by mc on 2020/2/18 11:56
 */
@Configuration
@EnableSwagger2
@Profile({"local","dev","test","ycq","prod"})
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaomo.file_upload"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }
    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件系统 APIs")
                .description("陌男孩")
//                .termsOfServiceUrl("http://10.255.50.124:18824/")
                .version("1.0")
                .build();
    }
}