package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.exceptions;

public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException(Long aLong){
        super("Crop with id " + aLong + " not found");
    }
}
