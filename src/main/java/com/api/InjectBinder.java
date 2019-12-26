package com.api;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.repository.GameRepository;
import com.api.dalcomponent.repository.UserRepository;
import com.api.logic.authentication.AuthHandler;
import com.api.logic.authentication.RegisterHandler;
import com.api.logic.datalogic.interfaces.GameHistoryProcessorable;
import com.api.logic.datalogic.jsonmanager.JSONResponseBuilderHandler;
import com.api.logic.datalogic.jsonmanager.builders.GameHistoryBuilder;
import com.api.logic.datalogic.processors.GameHistoryProcessor;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class InjectBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(GameRepository.class).to(IGameRepository.class);
        bind(UserRepository.class).to(IUserRepository.class);
        bind(AuthHandler.class).to(AuthHandler.class);
        bind(RegisterHandler.class).to(RegisterHandler.class);
        bind(GameHistoryProcessor.class).to(GameHistoryProcessorable.class);
        bind(GameHistoryBuilder.class).to(GameHistoryBuilder.class);
        bind(JSONResponseBuilderHandler.class).to(JSONResponseBuilderHandler.class);
    }
}
