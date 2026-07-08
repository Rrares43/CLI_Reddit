package community;

public class Subreddit {
    private String name;
    private String description;

    public Subreddit(String name, String description){
        if(!name.startsWith("r/")) {
            this.name = "r/" + name;
        }
        else{
            this.name = name;
        }
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
