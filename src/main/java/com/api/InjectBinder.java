package com.api;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.repository.GameRepository;
import com.api.dalcomponent.repository.UserRepository;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class InjectBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(GameRepository.class).to(IGameRepository.class);
        bind(UserRepository.class).to(IUserRepository.class);
    }
}
