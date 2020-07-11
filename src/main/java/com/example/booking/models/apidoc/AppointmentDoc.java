package com.example.booking.models.apidoc;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

public class AppointmentDoc {

    private static class AppointmentService {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private static class AppointmentClient {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private static class AppointmentProfessional {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @ApiModelProperty(position = 0, example = "2020-12-31")
    private LocalDate date;

    @ApiModelProperty(position = 1, example = "09:00")
    private String startTime;

    @ApiModelProperty(position = 2, example = "10:00")
    private String finishTime;

    @ApiModelProperty(position = 3)
    private AppointmentService service;

    @ApiModelProperty(position = 4)
    private AppointmentClient client;

    @ApiModelProperty(position = 5)
    private AppointmentProfessional professional;

    @ApiModelProperty(position = 6)
    private String comment;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public AppointmentService getService() {
        return service;
    }

    public void setService(AppointmentService service) {
        this.service = service;
    }

    public AppointmentClient getClient() {
        return client;
    }

    public void setClient(AppointmentClient client) {
        this.client = client;
    }

    public AppointmentProfessional getProfessional() {
        return professional;
    }

    public void setProfessional(AppointmentProfessional professional) {
        this.professional = professional;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static abstract class AppointmentPost extends AppointmentDoc {}

    public static abstract class AppointmentPut extends AppointmentDoc {}
}
