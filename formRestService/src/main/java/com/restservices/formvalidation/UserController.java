package com.restservices.formvalidation;

import com.pojo.classes.data.EmailAndPhoneData;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

@Component(property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/auth",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Auth.Rest", "auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true" }, service = Application.class)
public class UserController extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/getEmailAndPhoneByIdcs/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmailAndPhoneByIdcs(@PathParam("id") String id) {
		ResponseBuilder responseBuilder = null;
		String idcs = EmailAndPhoneData.getData(id);
		responseBuilder = Response.ok(idcs);
		return responseBuilder.build();

	}

	@POST
	@Path("/authenticateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@QueryParam("id") String id, @QueryParam("email") String email,
			@QueryParam("number") String number) {
		String uniqueID = UUID.randomUUID().toString();
		boolean data = EmailAndPhoneData.getValidatingData(id, email, number);
		ResponseBuilder responseBuilder = null;
		if (data) {
			responseBuilder = Response.ok(data + " " + uniqueID);
		} else {
			responseBuilder = Response.ok(false);
		}

		return responseBuilder.build();
	}

}