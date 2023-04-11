package org.example;

import lombok.Builder;

@Builder
public class Info {

    private String projectName;
    private String start_time;
    private String end_time;
    private String peopleSupport;
    private String fundsRaised;
    private String successPercentage;

    public Info(String start_time, String end_time, String projectName, String fundsRaised, String successPercentage, String peopleSupport) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.projectName = projectName;
        this.fundsRaised = fundsRaised;
        this.peopleSupport = peopleSupport;
        this.successPercentage = successPercentage;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDateStart() {
        return start_time;
    }

    public void setDateStart(String start_time) {
        this.start_time = start_time;
    }

    public String getDateEnd() {
        return end_time;
    }

    public void setDateEnd(String end_time) {
        this.end_time = end_time;
    }

    public String getPeopleSupport() {
        return peopleSupport;
    }

    public void setPeopleSupport(String peopleSupport) {
        this.peopleSupport = peopleSupport;
    }

    public String getFundsRaised() {
        return fundsRaised;
    }

    public void setFundsRaised(String fundsRaised) {
        this.fundsRaised = fundsRaised;
    }

    public String getSuccessPercentage() {
        return successPercentage;
    }

    public void setSuccessPercentage(String successPercentage) {
        this.successPercentage = successPercentage;
    }
    @Override
    public String toString() {
        return start_time + end_time + projectName + fundsRaised + successPercentage + peopleSupport;
    }
}
