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

    public String getDateStart() {
        return start_time;
    }

    public String getDateEnd() {
        return end_time;
    }

    public String getPeopleSupport() {
        return peopleSupport;
    }


    public String getFundsRaised() {
        return fundsRaised;
    }


    public String getSuccessPercentage() {
        return successPercentage;
    }

    @Override
    public String toString() {
        return start_time + end_time + projectName + fundsRaised + successPercentage + peopleSupport;
    }
}
