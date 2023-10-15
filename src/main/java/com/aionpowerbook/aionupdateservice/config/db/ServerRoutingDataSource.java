package com.aionpowerbook.aionupdateservice.config.db;

import com.aionpowerbook.aionupdateservice.utils.ServerResolverUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ServerRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ServerResolverUtil.resolveServer();
    }
}
