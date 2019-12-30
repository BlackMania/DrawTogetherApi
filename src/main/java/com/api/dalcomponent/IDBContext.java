package com.api.dalcomponent;

import com.j256.ormlite.support.ConnectionSource;

public interface IDBContext {
    ConnectionSource getConnectionSource();
}
