package com.thinkerwolf.blog.article.mapper;

import com.thinkerwolf.blog.article.model.Topic;
import com.thinkerwolf.blog.article.model.TopicCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    long countByCondition(TopicCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int deleteByCondition(TopicCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int insert(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int insertSelective(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    List<Topic> selectByCondition(TopicCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    Topic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int updateByConditionSelective(@Param("record") Topic record, @Param("example") TopicCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int updateByCondition(@Param("record") Topic record, @Param("example") TopicCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int updateByPrimaryKeySelective(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Wed Apr 10 19:13:51 CST 2019
     */
    int updateByPrimaryKey(Topic record);
    
    List<Topic> getAriticleTopics(TopicCondition example);
}