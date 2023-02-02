package com.emplregsys.ers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Long userId;
    private MultipartFile imageFile;

    public Image toImage() throws IOException {
        Image image = new Image();
        image.setImageData(imageFile.getBytes());
        return image;
    }
}
