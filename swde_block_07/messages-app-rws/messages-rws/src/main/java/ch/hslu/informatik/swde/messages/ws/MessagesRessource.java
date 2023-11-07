/*
 * Copyright 2022 Jordan Sucur, HSLU Informatik, Switzerland
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
package ch.hslu.informatik.swde.messages.ws;

import ch.hslu.informatik.swde.messages.business.api.MessagesService;
import ch.hslu.informatik.swde.messages.business.impl.MessagesServiceImpl;
import ch.hslu.informatik.swde.messages.domain.Message;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Iterator;
import java.util.List;

/**
 * Diese Klasse bildet eine Ressource-Klasse ab und ermöglicht den Aufruf von
 * diversen Funktionalitäten des Message-Providers im Web
 *
 * @author Jordan Sucur
 * @version 1.0
 */

@Path("messages")
public class MessagesRessource {

	private MessagesService service = new MessagesServiceImpl();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {

		Message msgById = service.findById(id);

		if(msgById != null) {
			return Response.ok(msgById).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("content")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllByContent(@QueryParam("value") String value) {

		List<Message> messages = service.allMessages();

		for (Iterator<Message> it = messages.iterator(); it.hasNext();) {
			Message m = it.next();

			if (!m.getContent().contains(value)) {
				it.remove();
			}
		}
		return Response.ok(messages).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {

		List<Message> messages = service.allMessages();
		return Response.ok(messages).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message) {

		service.addMessage(message);

		return Response.noContent().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateMessage(@PathParam("id") int id, Message message) {

		Message msgById = service.findById(id);

		if(msgById != null) {
			service.updateMessage(message);
			return Response.ok(msgById).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response deleteById(@PathParam("id") int id) {

		Message msgToDelete = service.findById(id);

		if(msgToDelete != null) {
			service.deleteById(id);
			return Response.ok(msgToDelete).build();
			//return Response.noContent().build():
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	/*
	 * Helper-Methode, um eine schnelle Re-Initialisierung des Datenbestandes zu
	 * Demo-Zwecken machen zu können
	 */

	@POST
	@Path("init")
	public Response reInit() {

		for (Message m : service.allMessages()) {
			service.deleteMessage(m);
		}

		service.addMessage(new Message("Anschnallen bitte! Wir haben eine Menge zu tun!"));
		service.addMessage(new Message("Take it easy. There is no reason for rush."));
		service.addMessage(new Message("Es war ein sehr interessantes Spiel. Hat es dir auch gefallen?"));
		service.addMessage(new Message("I don't think so. I was bored."));
		service.addMessage(new Message("Das kann ich wirklich nicht nachvollziehen!"));
		service.addMessage(new Message("Don't worry! Everything is ship-shape."));
		service.addMessage(new Message("Ich denke, dass Fussbalspieller ein wunderschönes Leben haben!"));
		service.addMessage(new Message("Sure. The grass is always greener on the other side!"));

		return Response.noContent().build();
	}
}