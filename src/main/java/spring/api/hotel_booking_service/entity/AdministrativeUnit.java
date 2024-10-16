package spring.api.hotel_booking_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "administrative_units")
public class AdministrativeUnit {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEN;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "short_name_en")
    private String shortNameEN;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_name_en")
    private String codeNameEN;

}
