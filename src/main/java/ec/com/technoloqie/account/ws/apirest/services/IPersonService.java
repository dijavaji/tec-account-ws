package ec.com.technoloqie.account.ws.apirest.services;

import ec.com.technoloqie.account.ws.apirest.models.Person;

public interface IPersonService {
	
	Person createPerson(Person person);
	Person getPersonId(Integer code);
	Person updatePerson(Person person, int id);
	void deletePerson(Integer code);

}
