package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;

public interface TestGradeDao {
      public int updateEmploy(@Param("employItemsId")String employItemsId,@Param("isEmploy")String isEmploy);
}
