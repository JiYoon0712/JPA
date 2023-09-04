package ch07;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// @DiscriminatorValue("A") : DTYPE 값을 DEFAULT인 ALBUM이 아닌 A로 변경
@Entity
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;


}
