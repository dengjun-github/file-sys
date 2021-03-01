package com.xiaomo.file_sys.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description: WebConfig
 * Created by mc on 2020/2/18 11:56
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Value("${sys.file-upload-path}")
	private String parentPath;
    /**
     * 配置跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3590);
    }

	/**
	 * RequestContextListener注册
	 * @return
	 */
	@Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }


	/**
	 * FastJson数据返回模式
	 * @param converters
	 */
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //针对字段的处理
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				// List字段如果为null,输出为[],而非null
				SerializerFeature.WriteNullListAsEmpty,
				//加上后，字段为null的也会输出
				SerializerFeature.WriteMapNullValue,
				//字符类型字段如果为null,输出为”“,而非null
				SerializerFeature.WriteNullStringAsEmpty,
				//Boolean字段如果为null,输出为false,而非null
				SerializerFeature.WriteNullBooleanAsFalse,
				//引用循环检查 不设置会出现 ref$
				SerializerFeature.DisableCircularReferenceDetect
				//结果是否格式化,默认为false
				// SerializerFeature.PrettyFormat
		);
        //日期格式化
		//fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(0,converter);
    }
	/**
	 * 外部静态资源路径
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:" + parentPath + "/");
	}

}
