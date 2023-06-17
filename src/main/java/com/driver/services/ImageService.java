package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
    Image image= new Image(blog,description,dimensions);
    blog.getImageList().add(image);
    blogRepository2.save(blog);
    return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();

        String [] scrarray = screenDimensions.split("X");

        String imageDimensions = image.getDimensions();
        String [] imgarray = imageDimensions.split("X");

        int scrl = Integer.parseInt(scrarray[0]);
        int scrb = Integer.parseInt(scrarray[1]);


        int imgl = Integer.parseInt(imgarray[0]);
        int imgb = Integer.parseInt(imgarray[1]);

        int rowlength = Math.max(scrl,scrb)/Math.max(imgl,imgb);
        int collength = Math.min(scrl,scrb)/Math.min(imgl,imgb);


        int count1=  rowlength*collength;

        int rowlength1 = Math.max(scrl,scrb)/Math.min(imgl,imgb);
        int collength1 = Math.min(scrl,scrb)/Math.max(imgl,imgb);

        int count2= rowlength1*collength1;

        return Math.max(count1,count2);


    }
}
