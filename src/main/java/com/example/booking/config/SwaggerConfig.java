package com.example.booking.config;

import com.example.booking.models.apidoc.AppointmentDoc;
import com.example.booking.models.apidoc.ServiceDoc;
import com.example.booking.models.apidoc.PersonDoc;
import com.example.booking.models.apidoc.UserDoc;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.collect.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("booking-backend-api")
                .securitySchemes(Lists.newArrayList(apiKey())).securityContexts(Lists.newArrayList(securityContext()))
                .useDefaultResponseMessages(false)
                .additionalModels(
                        resolver.resolve(ServiceDoc.ServicePost.class),
                        resolver.resolve(ServiceDoc.ServicePut.class),
                        resolver.resolve(PersonDoc.ClientPost.class),
                        resolver.resolve(PersonDoc.ClientPut.class),
                        resolver.resolve(PersonDoc.ProfessionalPost.class),
                        resolver.resolve(PersonDoc.ProfessionalPut.class),
                        resolver.resolve(PersonDoc.ProfessionalServicePost.class),
                        resolver.resolve(PersonDoc.ProfessionalServiceDelete.class),
                        resolver.resolve(AppointmentDoc.AppointmentPost.class),
                        resolver.resolve(AppointmentDoc.AppointmentPut.class),
                        resolver.resolve(UserDoc.UserPost.class))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Booking Backend - API ")
                .description("Backend of an appointment booking system.")
                .contact(contact())
                .version("1.0.0")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/api/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private Contact contact(){
        return new Contact("admin","","joselupalaciosbo@gmail.com");
    }
}
