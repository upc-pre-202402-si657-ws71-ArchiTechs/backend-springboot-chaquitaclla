package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record CropId(Long cropId) {

    public CropId {
        if (cropId < 0) {
            throw new IllegalArgumentException("Crop cropId cannot be negative");
        }
    }

    public CropId() {
        this(0L);
    }
}