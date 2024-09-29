package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import java.util.List;

    public record CreateCropResource(String name, String description, String imageUrl,List<Long> diseases, List<Long> pests,List<Long> cares) {
}