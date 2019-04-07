package com.company.host.extracting;

import com.company.host.HostConfiguration;

import java.sql.SQLException;

public interface ExtractAlgorithm {
    int extract(HostConfiguration host, String fileName) throws SQLException;
}
