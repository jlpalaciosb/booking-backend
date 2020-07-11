package com.example.booking.config;

import com.example.booking.models.apidoc.AppointmentDoc;
import com.example.booking.models.apidoc.ServiceDoc;
import com.example.booking.models.apidoc.PersonDoc;
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

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
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
                        resolver.resolve(AppointmentDoc.AppointmentPut.class))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }
}
