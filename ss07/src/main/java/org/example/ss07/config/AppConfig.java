package org.example.ss07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.ss07")
// Để câu hình được các đường dẫn tương đối
public class AppConfig implements WebMvcConfigurer {

    // 1. Bean SpringResourceTemplateResolver - đọc cấu hình folder file view
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        // set tiền tố
        resolver.setPrefix("/WEB-INF/views/");
        // set hậu tố
        resolver.setSuffix(".html");
        // set charactor UTF-8 để nhận những ký tiếng việt
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // 2. Bean SpringTemplateEngine - bộ máy trung tâm xử lý và chuyển các cú pháp thymeleaf
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    // 3. Bean ThymeleafViewResolver - giao tiếp Spring MVC với Thymeleaf
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // Cấu hình những đường dẫn tương đối
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "D:\\RIKKEI\\HN-K24-CNTT4\\ss07\\src\\main\\webapp\\images\\";

        // Đường dẫn tương đối chúng ta sẽ sử dụng
        registry.addResourceHandler("/images/**")
                // Đường dẫn tuyệt chỉ đến đích danh thư mục chính xác
                .addResourceLocations("file:" + path);
    }
}
