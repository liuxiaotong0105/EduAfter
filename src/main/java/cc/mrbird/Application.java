package cc.mrbird;

import cc.mrbird.common.config.FebsProperties;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;
import java.time.LocalDate;
import java.time.LocalTime;


@EnableScheduling
@Configuration   //加入了最大传送数据  得加这个注解//fastDFS
@Import(FdfsClientConfig.class)//fastDFS
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)//fastDFS
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cc.mrbird.*.dao")
@EnableConfigurationProperties({FebsProperties.class})
@EnableCaching
@EnableAsync
public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("FEBS started up successfully at {} {}", LocalDate.now(), LocalTime.now());
    }

    /**
     * 配置上传文件大小的配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("102400KB");
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
