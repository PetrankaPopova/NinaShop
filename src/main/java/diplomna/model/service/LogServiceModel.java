package diplomna.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogServiceModel extends BaseEntityService {
    private String username;
    private String description;
    private LocalDate localDate;

    public LogServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
