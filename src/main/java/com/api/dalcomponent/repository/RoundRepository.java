package com.api.dalcomponent.repository;

import com.api.dalcomponent.IDBContext;
import com.api.dalcomponent.interfaces.IRoundRepository;
import com.api.dalcomponent.model.Round;

public class RoundRepository<T extends Round> extends Repository<Round> implements IRoundRepository<Round> {

    public RoundRepository(IDBContext dbContext) {
        super(Round.class, dbContext);
    }
}
