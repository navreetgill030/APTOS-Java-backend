
package lib;

public class Application {

    private int id;
    private int opportunityId;
    private int applicantId;
    private String status;

    public Application(int id, int opportunityId,
                       int applicantId, String status) {

        this.id = id;
        this.opportunityId = opportunityId;
        this.applicantId = applicantId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getOpportunityId() {
        return opportunityId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public String getStatus() {
        return status;
    }
}
