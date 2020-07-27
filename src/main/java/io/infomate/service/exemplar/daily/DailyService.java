package io.infomate.service.exemplar.daily;

import com.sun.net.httpserver.HttpServer;
import io.infomate.service.exemplar.daily.dao.DailyOperations;
import io.infomate.service.exemplar.daily.dao.DailyOperationsImpl;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class DailyService {

    public static void main(String [] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        DailyServiceConfig dailyServiceConfig = new DailyServiceConfig(System.getenv());
        MySqlConnectionManager mySqlConnectionManager = MySqlConnectionManager.getInstance(dailyServiceConfig);
        DailyOperations dailyOperations = new DailyOperationsImpl(mySqlConnectionManager);
        DailyServiceBinder dailyServiceBinder = new DailyServiceBinder(dailyOperations);
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(dailyServiceBinder);
        resourceConfig.register(DailyResource.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, resourceConfig);
    }

}
