package rest;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Fish;

@Path("/fishservice")
public class FishService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpafishapp");
	@GET
	@Path("/readfish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Fish> readFish() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Fish> list=em.createQuery("select xyx from Fish xyx").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addfish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Fish> addFish(Fish fish) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(fish);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Fish> list=readFish();		
		return list;
	}	
	@PUT
	@Path("/updatefish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Fish> updateFish(Fish fish) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Fish f=em.find(Fish.class, fish.getId());
		if (f!=null) {
			em.merge(fish);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Fish> list=readFish();		
		return list;
	}	
	@DELETE
	@Path("/deletefish/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Fish> deleteFish(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Fish f=em.find(Fish.class, id);
		if (f!=null) {
			em.remove(f);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Fish> list=readFish();		
		return list;
	}	
	@GET
	@Path("/readtoupdatefish/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fish readToUpdateFish(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Fish f=em.find(Fish.class, id);
		em.getTransaction().commit();
		return f;
	}	
}
