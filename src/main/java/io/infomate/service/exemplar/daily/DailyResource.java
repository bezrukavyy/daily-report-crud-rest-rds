package io.infomate.service.exemplar.daily;

import io.infomate.service.exemplar.daily.dao.DailyEntry;
import io.infomate.service.exemplar.daily.dao.DailyOperations;
import io.infomate.service.exemplar.daily.dao.DailyOperationsImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

/**
 * Root resource.
 */
@Path("/daily")
public class DailyResource {

    DailyOperations dailyOperations;

    public DailyResource() {
        MySqlConnectionManager connectionManager = MySqlConnectionManager.getInstance();
        this.dailyOperations = new DailyOperationsImpl(connectionManager);
    }

    @GET @Path("/dateLocation/{date}/{location}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntryByDateLocation(
            @PathParam("date") String date,
            @PathParam("location") String location
    ) {

        DailyEntry dailyEntry = dailyOperations.getSingleEntry(date, location);

        return Response.status(Response.Status.OK)
                .entity(dailyEntry)
                .build();
    }

    @GET @Path("/location/{location}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListByLocation(@PathParam("location") String location) {

        List<DailyEntry> dailyEntryList = dailyOperations.getDailyEntriesByLocation(location);

        return Response.status(Response.Status.OK)
                .entity(dailyEntryList)
                .build();
    }
}