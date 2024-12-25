package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import spring.api.hotel_booking_service.dto.attraction.AttractionDto;
import spring.api.hotel_booking_service.dto.attraction.CreateAttractionDto;
import spring.api.hotel_booking_service.helper.mapper.AttractionMapper;
import spring.api.hotel_booking_service.helper.util.PaginationUtils;
import spring.api.hotel_booking_service.service.AttractionService;

import java.util.List;

@Tag(name = "Admin attractions resources")
@RequestMapping("/api/admin/attractions")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminAttractionController {

    AttractionService attractionService;
    AttractionMapper attractionMapper;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> createAttraction(@Valid @ModelAttribute CreateAttractionDto requestBody) {
        attractionService.createAttraction(requestBody);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    private ResponseEntity<List<AttractionDto>> getAttractions(@ParameterObject @PageableDefault Pageable pageable) {
        final Page<AttractionDto> page = attractionService.getAttractions(pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @DeleteMapping(path = "/{attractionId}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable("attractionId") Long attractionId) {
        attractionService.deleteAttraction(attractionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{attractionId}")
    public ResponseEntity<AttractionDto> getAttraction(@PathVariable("attractionId") Long attractionId) {
        return ResponseEntity.ok(attractionMapper.toAttractionDto(attractionService.getAttraction(attractionId)));
    }

}
