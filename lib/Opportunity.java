
package lib;

public class Opportunity {

    private int id;
    private int ownerId;
    private String category;
    private String title;
    private String description;
    private String requiredSkills;
    private int creditReward;
    private int maxMembers;
    private String status;

    public Opportunity(int id, int ownerId, String category,
                       String title, String description,
                       String requiredSkills, int creditReward,
                       int maxMembers, String status) {

        this.id = id;
        this.ownerId = ownerId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.creditReward = creditReward;
        this.maxMembers = maxMembers;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public int getCreditReward() {
        return creditReward;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public String getStatus() {
        return status;
    }
}
