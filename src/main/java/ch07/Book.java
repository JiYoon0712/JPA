package ch07;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// @DiscriminatorValue("B") : DTYPE 값을 DEFAULT인 Book이 아닌 B로 변경
@Entity
@DiscriminatorValue("B")
public class Book extends Item{

    private String author;
    private String isbn;

}
