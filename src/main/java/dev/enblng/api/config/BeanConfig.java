package dev.enblng.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//TODO: delete
@Configuration
public class BeanConfig {
    private static final String SNAPSHOT = "1.0";
    private static final String HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML = "Usage of this code is forbidden without consent";
    private static final String APACHE_2_0 = "Apache 2.0";

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Blog API")
                .description("Example of a blog API")
                .contact(new Contact("Catalin Patrut", "NO URL", "api@cpatrut.ro"))
                .license(APACHE_2_0)
                .licenseUrl(HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML)
                .version(SNAPSHOT)
                .build();
    }

}
