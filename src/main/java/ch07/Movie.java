package ch07;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// @DiscriminatorValue("M") : DTYPE 값을 DEFAULT인 Movie이 아닌 M로 변경
@Entity
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
