//package org.planeswalker.config;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.planeswalker.utils.JacksonUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
///**
// * 文件访问配置类，为了访问服务器上的其他文件
// * @author Planeswalker23
// * @date Created in 2020/2/25
// */
//@Configuration
//public class UploadFilePathConfig extends WebMvcConfigurationSupport {
//
//    @Value("${file.staticAccessPath}")
//    private String staticAccessPath;
//    @Value("${file.uploadFolder}")
//    private String uploadFolder;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 如果是Windows环境的话 file:=改为=》file:///
//        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
//        super.addResourceHandlers(registry);
//    }
//
//    /**
//     * 不覆盖此方式，spring-boot 配置文件中: jackson时间格式化 配置 将会失效
//     * spring.jackson.time-zone=GMT+8
//     * spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
//     * 原因: 会覆盖 @EnableAutoConfiguration 关于 WebMvcAutoConfiguration 的配置
//     * */
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = converter.getObjectMapper();
//        // 生成JSON时,将所有Long转换成String
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        // 时间格式化
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setDateFormat(JacksonUtil.sdf);
//        // 设置格式化内容
//        converter.setObjectMapper(objectMapper);
//        converters.add(0, converter);
//    }
//}
