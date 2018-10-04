package anthem.nimbus.util;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;

/**
 * Mapper
 *
 * @param <T>
 * @author Nageswara rao
 */
public interface BaseMapper<T> extends
        tk.mybatis.mapper.common.BaseMapper<T>,
        MySqlMapper<T>,
        IdsMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T> {
}