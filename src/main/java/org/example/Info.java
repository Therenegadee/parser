package org.example;

public class Info {

    private String projectName;
    private String dateStart;
    private String dateEnd;
    private String peopleSupport;
    private String fundsRaised;
    private String successPercentage;

    public Info(String projectName, String dateStart, String dateEnd, String fundsRaised, String successPercentage, String peopleSupport) {
        this.projectName = projectName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.peopleSupport = peopleSupport;
        this.fundsRaised = fundsRaised;
        this.successPercentage = successPercentage;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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
        return "Info{" +
                "projectName='" + projectName + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", peopleSupport='" + peopleSupport + '\'' +
                ", fundsRaised='" + fundsRaised + '\'' +
                ", successPercentage='" + successPercentage + '\'' +
                '}';
    }
}
