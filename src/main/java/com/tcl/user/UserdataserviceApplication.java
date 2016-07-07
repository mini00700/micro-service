package com.tcl.user;

import com.tcl.user.config.Constants;
import com.tcl.user.config.OneTouchClientSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(OneTouchClientSetting.class)
//@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UserdataserviceApplication {
	private static Class<UserdataserviceApplication> applicationClass = UserdataserviceApplication.class;
	 private static final Logger log = LoggerFactory.getLogger(UserdataserviceApplication.class);
	@Inject
    private Environment env;
	   @PostConstruct
	    public void initApplication() throws IOException {
	        if (env.getActiveProfiles().length == 0) {
	            log.warn("No Spring profile configured, running with default configuration");
	        } else {
	            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
	            Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
	            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
	                log.error("You have misconfigured your application! " +
	                    "It should not run with both the 'dev' and 'prod' profiles at the same time.");
	            }

	        }
	    }

	public static void main(String[] args) {
		 SpringApplication app = new SpringApplication(UserdataserviceApplication.class);
	        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
	        addDefaultProfile(app, source);
	        Environment env = app.run(args).getEnvironment();
	}
	 private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
	        if (!source.containsProperty("spring.profiles.active") &&
	                !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

	            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
	        }
}
//	 @Override
//	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		 return application.profiles(addDefaultProfile())
//		            .sources(applicationClass);
//	    }
//	 private String addDefaultProfile() {
//	        String profile = System.getProperty("spring.profiles.active");
//	        if (profile != null) {
//	            log.info("Running with Spring profile(s) : {}", profile);
//	            return profile;
//	        }
//
//	        log.warn("No Spring profile configured, running with default configuration");
//	        return Constants.SPRING_PROFILE_DEVELOPMENT;
//	    }
}
