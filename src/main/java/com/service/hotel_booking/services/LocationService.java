package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.District;
import com.service.hotel_booking.entities.Province;
import com.service.hotel_booking.entities.Ward;
import com.service.hotel_booking.entities.response.DetailProvinceResponse;
import com.service.hotel_booking.entities.response.DistrictResponse;
import com.service.hotel_booking.entities.response.SimpleProvinceResponse;
import com.service.hotel_booking.entities.response.WardResponse;

import java.util.List;

public interface LocationService {

    WardResponse getWardById(Integer id);
    List<WardResponse> getWardsByDistrictId(Integer id);
    DistrictResponse getDistrictById(Integer id);
    List<DistrictResponse> getDistrictsByProvinceId(Integer id);
    List<SimpleProvinceResponse> getSimpleProvinces();
    DetailProvinceResponse getProvinceById(Integer id);
    List<DetailProvinceResponse> getAllProvinces();
    Province getProvinceEntityById(Integer id);
    Ward getWardEntityById(Integer id);
    District getDistrictEntityById(Integer id);

}
