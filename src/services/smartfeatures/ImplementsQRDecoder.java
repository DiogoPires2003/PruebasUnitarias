package services.smartfeatures;

import data.VehicleID;
import exceptions.CorruptedImgException;

import java.awt.image.BufferedImage;

public class ImplementsQRDecoder implements QRDecoder {

    @Override
    public VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException {
        if (QRImg == null) {
            throw new CorruptedImgException("The provided QR image is null.");
        }

        try {
            String decodedText = decodeQR(QRImg);

            if (decodedText == null || decodedText.isEmpty()) {
                throw new CorruptedImgException("Failed to decode QR image: Decoded text is empty.");
            }

            return new VehicleID(decodedText);

        } catch (Exception e) {
            throw new CorruptedImgException("Error while decoding the QR image: " + e.getMessage(), e);
        }
    }

    private String decodeQR(BufferedImage QRImg) {
        return "AS123"; // Replace with actual decoding logic
    }
}
