package ra.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra"})
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
  // application

  private String uploadPath = "D:\\resources\\java\\rikeisoft -module 4\\ProjectMd4_demo\\src\\main\\webapp\\WEB-INF\\upload\\";
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  // cấu hình thymeleaf
  @Bean
  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(applicationContext);
    templateResolver.setPrefix("/WEB-INF/view/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setContentType("UTF-8");
    return viewResolver;
  }

  // cấu hình xử lí đường dẫn
// khi  mọi người truy cập đường dẫn có dạng /css/style.css => map với đường dẫn resourse/assets/css/style.css
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**"
            , "/js/**"
            , "/fonts/**"
            , "/img/**"
            , "/upload/**")
        .addResourceLocations("classpath:/assets/css/"
            , "classpath:/assets/js/"
            , "classpath:/assets/fonts/"
            , "classpath:/assets/img/"
            , "file:" + uploadPath);

  }

  // cấu hình kích thước upload tối đa
  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new
        CommonsMultipartResolver();
    multipartResolver.setMaxUploadSizePerFile(50 * 1024 * 1024); // 50MB
    return multipartResolver;
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("application");
    messageSource.setDefaultEncoding("utf-8");
    return messageSource;
  }
}
