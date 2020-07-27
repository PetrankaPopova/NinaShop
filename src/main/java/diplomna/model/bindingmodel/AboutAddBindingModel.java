package diplomna.model.bindingmodel;

import javax.persistence.Column;

public class AboutAddBindingModel {

    private String description;

    public AboutAddBindingModel(String description) {
        this.description = description;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
