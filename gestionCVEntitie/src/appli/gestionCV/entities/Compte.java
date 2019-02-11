package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Compte extends AbstractEntity{

	private static final long serialVersionUID = -938846801270594165L;

	private String login;
	
	private String password;
}
