package spring.api.hotel_booking_service.helper.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import spring.api.hotel_booking_service.helper.constant.HeaderConstants;

import java.text.MessageFormat;

public final class PaginationUtils {

    private PaginationUtils() {
    }

    public static <T> HttpHeaders generatePaginationHttpHeaders(UriComponentsBuilder uriBuilder, Page<T> page) {
        int totalPages = page.getTotalPages();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HeaderConstants.X_TOTAL_COUNT_HEADER, Long.toString(page.getTotalElements()));
        headers.add(HeaderConstants.X_TOTAL_PAGES_HEADER, Long.toString(totalPages));

        headers.add(HeaderConstants.ACCESS_CONTROL_EXPOSE_HEADERS_HEADER, "*");
        return headers;
    }

    private static String prepareLink(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize, String relType) {
        return MessageFormat.format(HeaderConstants.LINK_FORMAT_HEADER, preparePageUri(uriBuilder, pageNumber, pageSize), relType);
    }

    private static String preparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder
                .replaceQueryParam("page", Integer.toString(pageNumber))
                .replaceQueryParam("size", Integer.toString(pageSize))
                .toUriString()
                .replace(",", "%2C")
                .replace(";", "%3B");
    }
}
