package diplomna.config;

import diplomna.interseptors.StatsInterceptor;
import diplomna.interseptors.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final StatsInterceptor statsInterceptor;


    @Autowired
    public ApplicationWebConfiguration(TitleInterceptor titleInterceptor, StatsInterceptor statsInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.statsInterceptor = statsInterceptor;
    }


}
