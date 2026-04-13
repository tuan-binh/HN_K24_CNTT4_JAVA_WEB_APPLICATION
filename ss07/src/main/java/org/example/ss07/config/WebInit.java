package org.example.ss07.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        /**
         * location: địa chỉ lưu tạm thời
         * maxFileSize: file có dung lượng tối đa: ở đây là 25MB
         * maxRequestSize: giới file của request gửi lên: ở đây là 25MB
         * fileSizeThreshold: Muốn lưu file tạm kích cỡ bao nhiều 0
         * */
        registration.setMultipartConfig(
                new MultipartConfigElement(
                        "/",
                        25 * 1024 * 1024L,
                        25 * 1024 * 1024L,
                        0
                )
        );
    }
}
