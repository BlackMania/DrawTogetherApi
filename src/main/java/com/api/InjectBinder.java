package com.api;

import com.api.dalcomponent.DBContext;
import com.api.dalcomponent.IDBContext;
import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.repository.GameRepository;
import com.api.dalcomponent.repository.UserRepository;
import com.api.logic.authentication.AuthHandler;
import com.api.logic.authentication.RegisterHandler;
import com.api.logic.datalogic.jsonmanager.JSONResponseBuilderHandler;
import com.api.logic.datalogic.jsonmanager.builders.GameHistoryBuilder;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class InjectBinder extends AbstractBinder {
    @Override
    protected void configure() {
        PropertyReader reader = new PropertyReader();
        bind(GameRepository.class).to(IGameRepository.class);
        bind(UserRepository.class).to(IUserRepository.class);
        bind(AuthHandler.class).to(AuthHandler.class);
        bind(RegisterHandler.class).to(RegisterHandler.class);
        bind(GameHistoryBuilder.class).to(GameHistoryBuilder.class);
        bind(JSONResponseBuilderHandler.class).to(JSONResponseBuilderHandler.class);
        bind(new DBContext(reader.getPropValue("db.url"), reader.getPropValue("db.user"), reader.getPropValue("db.password"))).to(IDBContext.class);
    }
}