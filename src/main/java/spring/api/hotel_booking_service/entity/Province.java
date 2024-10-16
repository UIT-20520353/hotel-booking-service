package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "t_users")
@Getter
public class Province {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
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
    @JoinColumn(name = "provinces_administrative_region_id_fkey", nullable = false)
    private AdministrativeRegion administrativeRegion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinces_administrative_unit_id_fkey", nullable = false)
    private AdministrativeUnit administrativeUnit;

}
