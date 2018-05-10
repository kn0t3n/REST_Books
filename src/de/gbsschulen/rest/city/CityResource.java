package de.gbsschulen.rest.city;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/citystore")
public class CityResource {

    private CityService cityService = new CityService();

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public City getCity(@PathParam("id")int id){
        City city = cityService.getCity(id);
        return city;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String deleteCity(@PathParam("id") int id){
        City city = cityService.deleteCity(id);
        if(city != null){
            return city.getName() + " wurde aus DB gelöscht";
        }
        return "nicht gelöscht!";
    }

    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.TEXT_PLAIN})
    @Path("{id}")
    public String updateCity(@PathParam("id")int id,String name){
        City city = cityService.updateCity(id, name);
        if(city != null){
            return city.getName() + " wurde geändert auf " + name;
        }
        return "nicht geändert!";
    }

}
