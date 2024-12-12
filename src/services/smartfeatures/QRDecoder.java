package services.smartfeatures;

import data.*;
import java.awt.image.BufferedImage;
import exceptions.*;

public interface QRDecoder {
    VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}
