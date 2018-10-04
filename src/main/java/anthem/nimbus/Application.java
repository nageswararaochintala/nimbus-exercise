package anthem.nimbus;

import anthem.nimbus.service.CityService;
import anthem.nimbus.util.JsonUtils;
import com.baidu.unbiz.easymapper.mapping.ServiceLoaderHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nageswara rao
 */
@SpringBootApplication
@MapperScan("anthem.nimbus.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@Slf4j
public class Application implements CommandLineRunner {
	
	
	

    public static void main(String[] args) {
        ServiceLoaderHelper.getInstance();
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        log.info("application is running...");
        log.info(JsonUtils.toJson(cityService.selectByPk(1)));
    }

    @RestController
    public static class WelcomeController {

        @GetMapping("/")
        public ResponseEntity<String> welcome() {
            return ResponseEntity.ok("Welcome!");
        }

    }

}