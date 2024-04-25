package cl.corellana.demouser.adapters.persistence.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long number;

    private int cityCode;

    private String contryCode;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;
}
