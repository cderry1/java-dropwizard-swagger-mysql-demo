package org.kainos.ea;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class trueConfiguration extends Configuration {
    @Valid
    @NotNull
    private  final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger()
    {
        swagger.setResourcePackage("org.kainos.ea.resources");
        String[] schemes = {"hhtp", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
}
