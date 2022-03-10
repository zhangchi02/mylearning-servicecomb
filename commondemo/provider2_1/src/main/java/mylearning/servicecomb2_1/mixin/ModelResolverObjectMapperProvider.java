package mylearning.servicecomb2_1.mixin;

import org.apache.servicecomb.swagger.extend.DefaultModelResolveObjectMapperProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import mylearning.servicecomb.common.Student;

public class ModelResolverObjectMapperProvider extends DefaultModelResolveObjectMapperProvider {
  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public ObjectMapper getMapper() {
    ObjectMapper mapper = super.getMapper();
    mapper.addMixIn(Student.class, StudentMixIn.class);
    mapper.addMixIn(PageInfo.class, PageInfoMixIn.class);
    return mapper;
  }
}
