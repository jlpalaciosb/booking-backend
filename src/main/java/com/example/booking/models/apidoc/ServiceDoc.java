package com.example.booking.models.apidoc;

import io.swagger.annotations.ApiModelProperty;

public abstract class ServiceDoc {

    @ApiModelProperty(position = 0, example = "Service Name")
    private String name;

    @ApiModelProperty(position = 1, example = "Service description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static abstract class ServicePost extends ServiceDoc {}

    public static abstract class ServicePut extends ServiceDoc {}
}
