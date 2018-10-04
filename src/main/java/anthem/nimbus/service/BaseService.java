package anthem.nimbus.service;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author Nageswara rao
 */
public interface BaseService<T, PK extends Serializable> {

    // ------ C ------ //

    /**
     *
     * @param record
     * @return int
     */
    int insert(T record);

    /**
     *
     * @param record
     * @return int
     */
    int insertUnchecked(T record);

    /**
     *
     * @param records
     * @return int
     */
    int insertBatch(List<T> records);

    // ------ U ------ //

    /**
     *
     * @param record
     * @return int
     */
    int update(T record);

    /**
     *
     * @param record
     * @return int
     */
    int updateUnchecked(T record);

    /**
     *
     * @param record
     * @param example where
     * @return int
     */
    int updateByExample(T record, Example example);

    /**
     *
     * @param record
     * @param example
     * @return int
     */
    int updateUncheckedByExample(T record, Example example);

    // ------ D ------ //

    /**
     *
     * @param pk
     * @return int
     */
    int deleteByPk(PK pk);

    /**
     *
     * @param pks
     * @return int
     */
    int deleteByPks(Iterable<? extends PK> pks);

    /**
     *
     * @param param where
     * @return int
     */
    int delete(T param);

    /**
     *
     * @return int
     */
    int deleteAll();

    /**
     *
     * @param example where
     * @return int
     */
    int deleteByExample(Example example);

    // ------ R ------ //

    /**
     *
     * @param pk
     * @return T
     */
    T selectByPk(PK pk);

    /**
     *
     * @param pks
     * @return List<T>
     */
    List<T> selectByPks(Iterable<? extends PK> pks);

    /**
     *
     * @param param where
     * @return List<T>
     */
    List<T> select(T param);

    /**
     *
     * @param param where
     * @return T
     */
    T selectOne(T param);

    /**
     *
     * @return List<T>
     */
    List<T> selectAll();


    /**
     *
     * @param param where
     * @return int
     */
    int selectCount(T param);


    /**
     *
     * @param param    where
     * @param pageNum
     * @param pageSize
     * @return PageInfo<T>
     */
    PageInfo<T> selectPage(T param, int pageNum, int pageSize);

    /**
     *
     * @param param    where 
     * @param pageNum
     * @param pageSize 
     * @return PageInfo<T>
     */
    PageInfo<T> selectPageAndCount(T param, int pageNum, int pageSize);

    /**
     *
     * @param example where
     * @return List<T>
     */
    List<T> selectByExample(Example example);

    /**
     *
     * @param example where
     * @return int
     */
    int selectCountByExample(Example example);

    /**
     *
     * @param example  where
     * @param pageNum
     * @param pageSize
     * @return PageInfo<T>
     */
    PageInfo<T> selectPageByExample(Example example, int pageNum, int pageSize);

    /**
     *
     * @param example  where
     * @param pageNum
     * @param pageSize
     * @return PageInfo<T>
     */
    PageInfo<T> selectPageAndCountByExample(Example example, int pageNum, int pageSize);

}