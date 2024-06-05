package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.District;
import com.service.hotel_booking.entities.Province;
import com.service.hotel_booking.entities.Ward;
import com.service.hotel_booking.entities.response.DetailProvinceResponse;
import com.service.hotel_booking.entities.response.DistrictResponse;
import com.service.hotel_booking.entities.response.SimpleProvinceResponse;
import com.service.hotel_booking.entities.response.WardResponse;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.DistrictMapper;
import com.service.hotel_booking.mappers.ProvinceMapper;
import com.service.hotel_booking.mappers.WardMapper;
import com.service.hotel_booking.repositories.DistrictRepository;
import com.service.hotel_booking.repositories.ProvinceRepository;
import com.service.hotel_booking.repositories.WardRepository;
import com.service.hotel_booking.services.LocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationServiceImpl implements LocationService {

    DistrictRepository districtRepository;
    WardRepository wardRepository;
    ProvinceRepository provinceRepository;

    WardMapper wardMapper;
    DistrictMapper districtMapper;
    ProvinceMapper provinceMapper;

    @Override
    public WardResponse getWardById(Integer id) {
        Ward ward = this.getWardEntityById(id);
        return wardMapper.toWardResponse(ward);
    }

    @Override
    public DistrictResponse getDistrictById(Integer id) {
        District district = this.getDistrictEntityById(id);
        return districtMapper.toDistrictResponse(district);
    }

    @Override
    public List<DistrictResponse> getDistrictsByProvinceId(Integer id) {
        return districtRepository.getDistrictsByProvinceId(id).stream().map(districtMapper::toDistrictResponse).toList();
    }

    @Override
    public List<SimpleProvinceResponse> getSimpleProvinces() {
        return provinceRepository.findAll().stream().map(provinceMapper::toSimpleProvince).toList();
    }

    @Override
    public DetailProvinceResponse getProvinceById(Integer id) {
        Province province = this.getProvinceEntityById(id);
        return provinceMapper.toDetailProvince(province);
    }

    @Override
    public List<DetailProvinceResponse> getAllProvinces() {
        return provinceRepository.findAll().stream().map(provinceMapper::toDetailProvince).toList();
    }

    @Override
    public List<WardResponse> getWardsByDistrictId(Integer id) {
        return wardRepository.getWardByDistrictId(id).stream().map(wardMapper::toWardResponse).toList();
    }

    @Override
    public Province getProvinceEntityById(Integer id) {
        return provinceRepository.findById(id)
                                 .orElseThrow(() -> new BadRequestException(PROVINCE_NOT_EXIST));
    }

    @Override
    public Ward getWardEntityById(Integer id) {
        return wardRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(WARD_NOT_EXIST));
    }

    @Override
    public District getDistrictEntityById(Integer id) {
        return districtRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(DISTRICT_NOT_EXIST));
    }

}
