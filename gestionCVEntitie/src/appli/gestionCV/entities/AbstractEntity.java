package appli.gestionCV.entities;

import java.io.Serializable;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	public AbstractEntity(){
		this.id = UUID.randomUUID().toString();
	}
}
