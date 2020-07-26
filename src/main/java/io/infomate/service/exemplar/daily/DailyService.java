package io.infomate.service.exemplar.daily;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class DailyService {

    public static void main(String [] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        DailyServiceConfig dailyServiceConfig = new DailyServiceConfig(System.getenv());
        MySqlConnectionManager.getInstance(dailyServiceConfig);
        ResourceConfig resourceConfig = new ResourceConfig(DailyResource.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, resourceConfig);
    }

}
