package com.codeventure.payload;

public class CountUsers {

    private long normalUsers;
    private long mentorUsers;
    private long companyUsers;

    public CountUsers() {
    }

    public CountUsers(long normalUsers, long mentorUsers, long companyUsers) {
        this.normalUsers = normalUsers;
        this.mentorUsers = mentorUsers;
        this.companyUsers = companyUsers;
    }

    public long getNormalUsers() {
        return normalUsers;
    }

    public void setNormalUsers(long normalUsers) {
        this.normalUsers = normalUsers;
    }

    public long getMentorUsers() {
        return mentorUsers;
    }

    public void setMentorUsers(long mentorUsers) {
        this.mentorUsers = mentorUsers;
    }

    public long getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(long companyUsers) {
        this.companyUsers = companyUsers;
    }

    @Override
    public String toString() {
        return "CountUsers{" +
                "normalUsers=" + normalUsers +
                ", mentorUsers=" + mentorUsers +
                ", companyUsers=" + companyUsers +
                '}';
    }
}
