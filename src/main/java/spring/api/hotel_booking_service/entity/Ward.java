package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "wards")
@Getter
public class Ward {

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEN;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", referencedColumnName = "code")
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    AdministrativeUnit administrativeUnit;

}
