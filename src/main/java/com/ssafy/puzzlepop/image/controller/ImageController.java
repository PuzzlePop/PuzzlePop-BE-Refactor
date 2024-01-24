package com.ssafy.puzzlepop.image.controller;

import com.ssafy.puzzlepop.image.domain.ImageCreateDto;
import com.ssafy.puzzlepop.image.domain.ImageDto;
import com.ssafy.puzzlepop.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// TODO: exception catch 시 에러 메세지 그대로 보내는 리턴하는 부분 리팩토링 필요
// TODO: 작업하려는 이미지의 userId와 accessToken 발급받은 userId가 일치하는지 확인 필요
// TODO: 이미지에 대한 update 작업이 뭔지 정의 필요 & 정의에 맞춰 로직 수정 필요

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    private ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> createImage(@RequestParam MultipartFile file, @RequestParam String type, @RequestParam String userId) {
        ImageCreateDto imageCreateDto = new ImageCreateDto(type, userId);

        try {
            int id = imageService.createImage(file, imageCreateDto);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 수정 기능의 역할이 명확하지 않음
    // 이름만 수정? 이미지 파일 자체를 수정?
    @PutMapping
    public ResponseEntity<?> updateImage(@RequestBody ImageDto imageDto) {

        try {
            int id = imageService.updateImage(imageDto);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteImage(@RequestBody int id) {

        try {
            imageService.deleteImage(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findImageById(@PathVariable int id) {
        try {
            ImageDto image = imageService.getImageById(id);
            return ResponseEntity.status(HttpStatus.OK).body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllImages() {

        try {
            List<ImageDto> imageList = imageService.getAllImages();
            return ResponseEntity.status(HttpStatus.OK).body(imageList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<?> findImagesByType(@PathVariable String type) {
        try {
            List<ImageDto> imageList = imageService.getImagesByType(type);
            return ResponseEntity.status(HttpStatus.OK).body(imageList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
