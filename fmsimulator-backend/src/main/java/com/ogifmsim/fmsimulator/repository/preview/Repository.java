package com.ogifmsim.fmsimulator.repository.preview;

import java.sql.ResultSet;
import java.util.List;

public abstract class Repository<T, K> {
    public abstract T loadById(K id);
    public abstract List<T> loadAll();
    
    public abstract T extract(ResultSet rs, String alias);
}
