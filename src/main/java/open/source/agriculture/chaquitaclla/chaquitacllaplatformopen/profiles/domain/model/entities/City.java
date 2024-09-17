package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.profiles.domain.model.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;

@Getter
@Setter
@Entity
public class City extends AuditableModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "country_id",nullable = false)
        private Country country;

        public City() {
        }
        public City(String name, Country country) {
            this.name = name;
            this.country = country;
        }
}
