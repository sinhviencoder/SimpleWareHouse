package com.company.loading;

import com.company.host.HostConfiguration;

import java.sql.SQLException;
import java.util.List;

public interface HostConfigRetriever {
    List<HostConfiguration> retrieveAll() throws SQLException;
}
