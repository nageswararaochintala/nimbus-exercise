package anthem.nimbus.test;

import com.github.pagehelper.PageInfo;
import anthem.nimbus.model.domain.City;
import anthem.nimbus.service.CityService;
import anthem.nimbus.util.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationTests extends SpringBaseTest {

    @Autowired
    private CityService cityService;

    @Test
    public void selectPage() {
        cityService.selectPageAndCount(null, 1, 3).getList().stream()
                .map(JsonUtils::toJson)
                .forEach(log::info);
    }

}