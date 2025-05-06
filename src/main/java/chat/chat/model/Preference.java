package chat.chat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String position;
    private String location;
    private String workType;

    public Preference() {}

    public Preference(String userId, String position, String location, String workType) {
        this.userId = userId;
        this.position = position;
        this.location = location;
        this.workType = workType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getWorkType() { return workType; }
    public void setWorkType(String workType) { this.workType = workType; }
}