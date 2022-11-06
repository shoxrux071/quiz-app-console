package org.example.service;

import lombok.NonNull;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 01:30 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public interface GenericCRUDService<
        VO extends GenericVO,
        CVO extends BaseVO,
        UVO extends GenericVO,
        ID extends Serializable> {

    Response<DataVO<ID>> create(@NonNull CVO vo);

    Response<DataVO<Void>> update(@NonNull UVO vo);

    Response<DataVO<Void>> delete(@NonNull ID id);

    Response<DataVO<VO>> get(@NonNull ID id);

    Response<DataVO<List<VO>>> getAll();

}
