/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package jaxrs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.tags.Tag;
import io.swagger.oas.annotations.tags.Tags;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import jaxrs.app.JAXRSApp;
import jaxrs.model.Flight;

@Path("/availability")
@Tags(tags = @Tag(name = "availability", description = "all the availibility methods"))
	public class AvailabilityResource {
	
	@GET
	@Operation(
			method = "get",
			summary = "Retrieve all available flights",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "successful operation",
							content = @Content(
									mediaType = "applictaion/json",
									schema = @Schema(
											type = "array",
											implementation = Flight.class
											)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "No available flights found",
							content = @Content(
									mediaType = "n/a")
							)
			})
	@Produces("application/json")
	public Response getFlights(
			@Parameter(
					name = "departureDate",
					required = true,
					description = "Customer departure date",
					schema = @Schema( 
							implementation = String.class)
					)
			@QueryParam("departureDate") String departureDate,
			@Parameter(
					name = "airportFrom",
					required = true,
					description = "Airport the customer departs from",
					schema = @Schema( 
							implementation = String.class)	
					)
			@QueryParam("airportFrom") String airportFrom,
			@Parameter(
					name = "returningDate",
					required = true,
					description = "Customer return date",
					schema = @Schema( 
							implementation = String.class)
					)
			@QueryParam("returningDate") String returningDate,
			@Parameter(
					name = "airportTo",
					required = true,
					description = "Airport the customer returns to",
					schema = @Schema( 
							implementation = String.class)
					)
			@QueryParam("airportTo") String airportTo,
			@Parameter(
					name = "numberOfAdults",
					required = true,
					description = "Number of adults on the flight",
					schema = @Schema( 
							minimum = "0",
							implementation = String.class)
					)
			@QueryParam("numberOfAdults") int numberOfAdults,
			@Parameter(
					name = "numberOfChildren",
					required = true,
					description = "Number of children on the flight",
					schema = @Schema(
							minimum = "0", 
							implementation = String.class)
			)
			@QueryParam("numberOfChildren") int numberOfChildren){
		return Response.ok().entity(findFlights(airportFrom, airportTo, departureDate, returningDate)).build();
	}
	
   private static List<Flight> findFlights (String airportFrom, String airportTo, String departureDate, String returningDate) {
	   
	   List<Flight> flights = new ArrayList<Flight>(6);

	   //Departure flights
	   departureDate = extractDate(departureDate);
	   for (int i = 0; i < 3; i++) {
		   flights.add(new Flight(AirlinesResource.getRandomAirline(), 
				   				  departureDate + getRandomTime(),
				   				  "AC" + JAXRSApp.getRandomNumber(200,10),
				   				  "on schedule",
				   				  airportFrom,
				   				  airportTo,
				   				  getRandomPrice())); 
	   }
	   
	   //Airline airline, String dateTime, String number, String status, String airportFrom, String airportTo, String price
	   
	   //Returning flights
	   returningDate = extractDate(returningDate);
	   for (int i = 0; i < 3; i++) {
		   flights.add(new Flight(AirlinesResource.getRandomAirline(), 
				   				  returningDate + getRandomTime(),
				   				  "AC" + JAXRSApp.getRandomNumber(200,10),
				   				  "on schedule",
				   				  airportFrom,
				   				  airportTo,
				   				  getRandomPrice())); 
	   }
	   
	   return flights;
   }
   
   private static String extractDate(String date) {
	   return date.substring("YYYY-MM-D".length() + 1);
   }
   
   private static String getRandomTime() {
	   return JAXRSApp.getRandomNumber(23,10) + ":" + JAXRSApp.getRandomNumber(59,10); 
   }
   
   private static String getRandomPrice() {
	   return Integer.toString(JAXRSApp.getRandomNumber(600, 300));
   }
      
}
