package rain;

public class Player {
    private String username;
    private Integer score;

    public Player (String name, Integer score){
        this.username = name;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String toString(){
        return this.username + " " + this.score;
    }
}
