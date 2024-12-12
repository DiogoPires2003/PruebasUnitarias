package services.smartfeatures;

public interface QRDecoder {
    VehicleId getVehicleID(BufferedImage QRImg) throws CorruptedImgException;

}
