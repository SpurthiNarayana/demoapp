package demoapp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Articles {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	public String content;
	public 	String author;	
	public	String title;
	
	
}
	