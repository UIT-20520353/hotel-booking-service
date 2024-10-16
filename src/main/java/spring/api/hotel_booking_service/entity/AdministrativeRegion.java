package spring.api.hotel_booking_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "administrative_regions")
public class AdministrativeRegion {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_name_en")
    private String codeNameEN;

}
