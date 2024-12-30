package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.hotel_booking_service.dto.BusinessOwnerDto;
import spring.api.hotel_booking_service.entity.filter.BusinessOwnerFilter;
import spring.api.hotel_booking_service.helper.util.PaginationUtils;
import spring.api.hotel_booking_service.service.BusinessOwnerService;

import java.util.List;

@Tag(name = "Admin business owner resources")
@RequestMapping("/api/admin/business-owners")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminBusinessOwnerController {

    BusinessOwnerService businessOwnerService;

    @GetMapping
    public ResponseEntity<List<BusinessOwnerDto>> getAllBusinessOwners(@RequestParam(required = false) String name,
                                                                       @RequestParam(required = false) String email,
                                                                       @ParameterObject @PageableDefault Pageable pageable) {
        BusinessOwnerFilter filter = new BusinessOwnerFilter();
        filter.setName(name);
        filter.setEmail(email);
        final Page<BusinessOwnerDto> page = businessOwnerService.getBusinessOwners(filter, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{businessOwnerId}")
    public ResponseEntity<BusinessOwnerDto> getBusinessOwner(@PathVariable Long businessOwnerId) {
        return ResponseEntity.ok(businessOwnerService.getBusinessOwner(businessOwnerId));
    }

    @PutMapping("/{businessOwnerId}")
    public ResponseEntity<Void> approveOrRejectBusinessOwner(@PathVariable Long businessOwnerId, @RequestBody boolean status) {
        businessOwnerService.approveBusinessOwner(businessOwnerId, status);
        return ResponseEntity.noContent().build();
    }

}
