package io.infomate.service.exemplar.daily;

import io.infomate.service.exemplar.daily.dao.DailyOperations;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class DailyServiceBinder extends AbstractBinder {

    DailyOperations dailyOperations;

    public DailyServiceBinder(DailyOperations dailyOperations) {
        this.dailyOperations = dailyOperations;
    }

    @Override
    protected void configure() {

        bind(dailyOperations).to(DailyOperations.class);
    }
}
