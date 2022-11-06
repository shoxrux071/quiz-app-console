package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.utils.BaseUtils;


/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 01:26 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public class AbstractDAO<D extends BaseDAO> {

    protected final D dao;

    protected final Gson gson;
    protected final BaseUtils utils;

    public AbstractDAO(D dao, BaseUtils utils) {
        this.dao = dao;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.utils = utils;
    }
}
