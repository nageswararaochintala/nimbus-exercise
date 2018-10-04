package anthem.nimbus.service.impl;

import anthem.nimbus.model.domain.City;
import anthem.nimbus.service.CityService;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<City, Integer> implements CityService {
}