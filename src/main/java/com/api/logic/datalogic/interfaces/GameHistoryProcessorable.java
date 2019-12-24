package com.api.logic.datalogic.interfaces;

import com.api.dalcomponent.model.Game;

import java.util.List;

public interface GameHistoryProcessorable<T extends Game> extends Processerable<Game> {
    List<Game> getByClientId(String clientid);
}
