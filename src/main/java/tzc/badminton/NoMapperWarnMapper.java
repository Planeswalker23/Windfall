package tzc.badminton;

import org.apache.ibatis.annotations.Mapper;


/**
 * 使用的tk的开源项目进行mybatis集成，会抛出如下warn警告
 *      No MyBatis mapper was found in '[tzc.badminton]' package. Please check your configuration.
 *      为了消除此警告，建立这个伪mapper，可忽略
 *
 * 原因：在项目启动时，doScan()会扫描启动类同级目录下的mapper接口
 * 但是合理的目录结果绝对不允许所有的mapper都在启动类目录下，所以在启动类目录下添加了一个伪mapper
 * @author Planeswalker23
 * @date Created in 2019-11-02
 */
@Mapper
public interface NoMapperWarnMapper {
}
