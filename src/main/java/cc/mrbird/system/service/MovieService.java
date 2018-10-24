package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.system.domain.Movie;

import java.util.List;

/**
 * <pre>项目名称：project
 * 类名称：MovieService
 * 类描述：
 * 创建人：赵佳佳
 * 创建时间：2018/10/19  17:29
 * 修改人：赵佳佳
 * 修改时间：2018/10/19  17:29
 * 备注：大白
 */
public interface MovieService {
    List<Movie> findMovie(Movie movie, QueryRequest request);
}
