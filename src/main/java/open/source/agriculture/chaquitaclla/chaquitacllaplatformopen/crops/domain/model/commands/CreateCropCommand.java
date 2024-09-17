package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import java.util.List;

public record CreateCropCommand(String name, String description, String imageUrl,List<Long> diseases, List<Long> pests,List<Long> cares) {
}
