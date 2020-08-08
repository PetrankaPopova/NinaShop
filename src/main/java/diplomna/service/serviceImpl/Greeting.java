package diplomna.service.serviceImpl;

public enum Greeting {
    HELLO;


    private String id;


    Greeting() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
